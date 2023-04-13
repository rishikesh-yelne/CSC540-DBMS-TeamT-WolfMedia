package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.*;
import edu.ncsu.csc540.s23.backend.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private SongService songService;
    private RecordLabelService recordLabelService;
    private UserService userService;
    private PodcastService podcastService;
    private PodcastEpisodeService podcastEpisodeService;

    public PaymentService(
            SongService songService,
            RecordLabelService recordLabelService,
            UserService userService,
            PodcastService podcastService,
            PodcastEpisodeService podcastEpisodeService) {
        this.songService = songService;
        this.recordLabelService = recordLabelService;
        this.userService = userService;
        this.podcastService = podcastService;
        this.podcastEpisodeService = podcastEpisodeService;
    }

    public Double getPaymentToRecordLabel(Long recordLabelId, int month, int year) {
        return jdbcTemplate.queryForObject(OperationQuery.GET_PAYMENT_TO_RL_BY_ID, Double.class, recordLabelId, month, year);
    }

    public Double getPaymentToArtist(Long artistId, int month, int year) {
        return jdbcTemplate.queryForObject(OperationQuery.GET_PAYMENT_TO_ARTIST_BY_ID, Double.class, artistId, month, year);
    }

    public Double getPaymentToPodcastHost(Long podcastHostId, int month, int year) {
        return jdbcTemplate.queryForObject(OperationQuery.GET_PAYMENT_TO_PH_BY_ID, Double.class, podcastHostId, month, year);
    }

    public List<RecordLabelPaymentDTO> getPaymentToRecordLabels() {
        return jdbcTemplate.query(OperationQuery.GET_PAYMENTS_TO_RECORD_LABELS, BeanPropertyRowMapper.newInstance(RecordLabelPaymentDTO.class));
    }

    public List<ArtistPaymentDTO> getPaymentToArtists() {
        return jdbcTemplate.query(OperationQuery.GET_PAYMENTS_TO_ARTISTS, BeanPropertyRowMapper.newInstance(ArtistPaymentDTO.class));
    }

    public List<PodcastHostPaymentDTO> getPaymentToPodcastHosts() {
        return jdbcTemplate.query(OperationQuery.GET_PAYMENTS_TO_PODCAST_HOSTS, BeanPropertyRowMapper.newInstance(PodcastHostPaymentDTO.class));
    }

    public String payRecordLabel(Long recordLabelId, Optional<Integer> month, Optional<Integer> year) {
        int paymentMonth, paymentYear;
        List<String> response = new ArrayList<>();
        if (month.isEmpty() || year.isEmpty()) {
            // month and year is empty, make payment for current month and year
            paymentMonth = LocalDate.now().getMonth().getValue();
            paymentYear = LocalDate.now().getYear();
        } else {
            paymentMonth = month.get();
            paymentYear = year.get();
        }

        RecordLabel recordLabel = this.recordLabelService.getRecordLabel(recordLabelId);

        List<Song> songs = this.songService.getSongsByRecordLabel(recordLabelId);
        for (Song song : songs) {
            response.add(payRecordLabel(recordLabel, song, paymentMonth, paymentYear));
        }
        response = response.stream().filter(Objects::nonNull).toList();
        return String.join("\n", response);
    }

    public String payRecordLabel(RecordLabel recordLabel, Song song, int paymentMonth, int paymentYear) {
        // first check if there exists a payment for the given song, month and year
        boolean paymentExists = checkIfRecordPaymentExists(
                recordLabel.getRecordLabelId(),
                song.getSongId(),
                song.getAlbumId(),
                paymentMonth,
                paymentYear);

        if (paymentExists) return "Payment already done for the month & year : " + Month.of(paymentMonth).name() + " " + paymentYear;

        // payment does not exist, calculate payment for the Song
        Long playCount = this.songService.getPlayCount(song.getSongId(), song.getAlbumId(), paymentMonth, paymentYear);
        if (playCount == 0) return null;

        if (song.getRoyaltyRate()*playCount == 0D) return "Payment amount is 0. No payment needed";

        PayRecordDTO payment = new PayRecordDTO();

        payment.setAmount(song.getRoyaltyRate()*playCount);
        payment.setPayer("WolfMedia");
        payment.setPayee(recordLabel.getRecordLabelName());
        payment.setPaymentDate(Date.valueOf(LocalDate.of(paymentYear, paymentMonth, getLastDateOfMonth(paymentMonth, paymentYear))));

        payment.setRecordLabelId(recordLabel.getRecordLabelId());
        payment.setSongId(song.getSongId());
        payment.setAlbumId(song.getAlbumId());

        try {
            makePaymentToRecordLabel(payment);
            return "Payment successful";
        } catch (SQLException sqlEx) {
            return "Payment failed. Error : " + sqlEx.getMessage();
        }
    }

    private void makePaymentToRecordLabel(PayRecordDTO payment) throws SQLException {
        String query_RecordLabel = OperationQuery.INSERT_ACCOUNTS;
        String[] generatedColumns = { "transac_id" };
        String query_pays_record = OperationQuery.PAY_RECORD_LABEL;

        Connection connection = getConnection();

        try (
                PreparedStatement statement1 = connection.prepareStatement(query_RecordLabel, generatedColumns);
                PreparedStatement statement2 = connection.prepareStatement(query_pays_record);
                ) {
            connection.setAutoCommit(false);

            statement1.setDouble(1, payment.getAmount());
            statement1.setString(2, payment.getPayer());
            statement1.setString(3, payment.getPayee());
            statement1.setDate(4, payment.getPaymentDate());

            int rowsAffected1 = statement1.executeUpdate();
            if (rowsAffected1 <= 0) throw new SQLException("Transaction failed");

            Long transacId = -1L;
            ResultSet result = statement1.getGeneratedKeys();
            if (result.next()) {
                 transacId = result.getLong(1);
            }
            result.close();

            if (transacId == -1L) throw new SQLException("Transaction not created");

            statement2.setLong(1, transacId);
            statement2.setLong(2, payment.getRecordLabelId());
            statement2.setLong(3, payment.getSongId());
            statement2.setLong(4, payment.getAlbumId());

            int rowsAffected2 = statement2.executeUpdate();
            if (rowsAffected2 <= 0) throw new SQLException("Transaction failed");

            connection.commit();
        } catch (SQLException sqlEx) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
    }

    private boolean checkIfRecordPaymentExists(Long recordLabelId, Long songId, Long albumId, int month, int year) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(OperationQuery.CHECK_IF_RECORD_PAYMENT_EXISTS, Boolean.class, recordLabelId, songId, albumId, month, year));
    }

    public String payArtist(Long artistId, Optional<Integer> month, Optional<Integer> year) {
        int paymentMonth, paymentYear;
        List<String> response = new ArrayList<>();
        if (month.isEmpty() || year.isEmpty()) {
            // month and year is empty, make payment for current month and year
            paymentMonth = LocalDate.now().getMonth().getValue();
            paymentYear = LocalDate.now().getYear();
        } else {
            paymentMonth = month.get();
            paymentYear = year.get();
        }

        Artist artist = this.userService.getArtist(artistId);

        List<ArtistSongDTO> songs = this.songService.getSongsByArtist(artistId);
        for (ArtistSongDTO song : songs) {
            response.add(payArtist(artist, song, paymentMonth, paymentYear));
        }
        response = response.stream().filter(Objects::nonNull).toList();
        return String.join("\n", response);
    }

    private String payArtist(Artist artist, ArtistSongDTO song, int paymentMonth, int paymentYear) {
        boolean paymentExists = checkIfArtistPaymentExists(
                artist.getUserId(),
                song.getSongId(),
                song.getAlbumId(),
                paymentMonth,
                paymentYear);

        if (paymentExists) return "Payment already done for the month & year : " + Month.of(paymentMonth).name() + " " + paymentYear + " to the artist";

        Long playCount = this.songService.getPlayCount(song.getSongId(), song.getAlbumId(), paymentMonth, paymentYear);
        Long artistCount = this.songService.getArtistCount(song.getSongId(), song.getAlbumId());

        if (artistCount == 0) return "Could not find artist for the song (" + song.getSongId() + ", " + song.getAlbumId() + ")";
        if (playCount == 0) return null;

        double amount = song.getRoyaltyRate()*0.7*playCount/artistCount;
        if (amount == 0D) return "Payment amount is 0. No payment needed for the song (" + song.getSongId() + ", " + song.getAlbumId() + ")";

        PayArtistDTO payment = new PayArtistDTO();

        payment.setAmount(amount);
        payment.setPayer("WolfMedia");
        payment.setPayee(artist.getFirstName() + " " + artist.getLastName());
        payment.setPaymentDate(Date.valueOf(LocalDate.of(paymentYear, paymentMonth, getLastDateOfMonth(paymentMonth, paymentYear))));

        payment.setRecordLabelId(artist.getRecordLabelId());
        payment.setSongId(song.getSongId());
        payment.setAlbumId(song.getAlbumId());
        payment.setArtistId(artist.getUserId());
        try {
            makePaymentToArtist(payment);
            return "Payment successful for the song (" + song.getSongId() + ", " + song.getAlbumId() + ")";
        } catch (SQLException sqlEx) {
            return "Payment failed  for the song (" + song.getSongId() + ", " + song.getAlbumId() + "). Error : " + sqlEx.getMessage();
        }
    }

    private void makePaymentToArtist(PayArtistDTO payment) throws SQLException {
        String query_Artist = OperationQuery.INSERT_ACCOUNTS;
        String[] generatedColumns = { "transac_id" };
        String query_pays_artist = OperationQuery.PAY_ARTIST;

        Connection connection = getConnection();

        try (
                PreparedStatement statement1 = connection.prepareStatement(query_Artist, generatedColumns);
                PreparedStatement statement2 = connection.prepareStatement(query_pays_artist);
        ) {
            connection.setAutoCommit(false);

            statement1.setDouble(1, payment.getAmount());
            statement1.setString(2, payment.getPayer());
            statement1.setString(3, payment.getPayee());
            statement1.setDate(4, payment.getPaymentDate());

            int rowsAffected1 = statement1.executeUpdate();
            if (rowsAffected1 <= 0) throw new SQLException("Transaction failed");

            long transacId = -1L;
            ResultSet result = statement1.getGeneratedKeys();
            if (result.next()) {
                transacId = result.getLong(1);
            }
            result.close();

            if (transacId == -1L) throw new SQLException("Transaction not created");

            statement2.setLong(1, transacId);
            statement2.setLong(2, payment.getRecordLabelId());
            statement2.setLong(3, payment.getArtistId());
            statement2.setLong(4, payment.getSongId());
            statement2.setLong(5, payment.getAlbumId());

            int rowsAffected2 = statement2.executeUpdate();
            if (rowsAffected2 <= 0) throw new SQLException("Transaction failed");

            connection.commit();
        } catch (SQLException sqlEx) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
    }

    private boolean checkIfArtistPaymentExists(Long artistId, Long songId, Long albumId, int month, int year) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(OperationQuery.CHECK_IF_ARTIST_PAYMENT_EXISTS, Boolean.class, artistId, songId, albumId, month, year));
    }

    public String payPodcastHost(Long podcastHostId, Optional<Integer> month, Optional<Integer> year) {
        int paymentMonth, paymentYear;
        List<String> response = new ArrayList<>();
        if (month.isEmpty() || year.isEmpty()) {
            // month and year is empty, make payment for current month and year
            paymentMonth = LocalDate.now().getMonth().getValue();
            paymentYear = LocalDate.now().getYear();
        } else {
            paymentMonth = month.get();
            paymentYear = year.get();
        }

        List<Podcast> podcasts = this.podcastService.getPodcastsByPodcastHost(podcastHostId);
        for (Podcast podcast: podcasts) {
            PodcastHost podcastHost = this.userService.getPodcastHost(podcast.getPodcastHostId());
            List<PodcastEpisode> episodes = this.podcastEpisodeService.getPodcastEpisodesByPodcast(podcast.getPodcastId(), paymentMonth, paymentYear);
            for (PodcastEpisode episode: episodes) {
                response.add(payPodcastHost(podcast, podcastHost, episode, paymentMonth, paymentYear));
            }

        }
        response = response.stream().filter(Objects::nonNull).toList();
        return String.join("\n", response);
    }

    private String payPodcastHost(Podcast podcast, PodcastHost podcastHost, PodcastEpisode episode, int paymentMonth, int paymentYear) {
        boolean paymentExists = checkIfPodcastHostPaymentExists(
                podcastHost.getUserId(),
                podcast.getPodcastId(),
                episode.getPodcastEpisodeId(),
                paymentMonth,
                paymentYear);

        if (paymentExists) return "Payment already done for the month & year : " + Month.of(paymentMonth).name() + " " + paymentYear + " to the podcast host for the podcast episode (" + episode.getPodcastEpisodeId() + ", " + podcast.getPodcastId() + ")";

        double amount = podcast.getFlatFee() + episode.getAdvertisementCount()*episode.getBonusRate();

        // if 0 amount, no need to make payment
        if (amount == 0D) return "Payment amount is 0. No payment needed for the podcast episode (" + episode.getPodcastEpisodeId() + ", " + podcast.getPodcastId() + ")";

        PayPodcastHostDTO payment = new PayPodcastHostDTO();

        payment.setAmount(amount);
        payment.setPayer("WolfMedia");
        payment.setPayee(podcastHost.getFirstName() + " " + podcastHost.getLastName());
        payment.setPaymentDate(Date.valueOf(LocalDate.of(paymentYear, paymentMonth, getLastDateOfMonth(paymentMonth, paymentYear))));

        payment.setPodcastId(podcast.getPodcastId());
        payment.setPodcastEpisodeId(episode.getPodcastEpisodeId());
        payment.setPodcastHostId(podcast.getPodcastHostId());
        try {
            makePaymentToPodcastHost(payment);
            return "Payment successful for the podcast episode (" + episode.getPodcastEpisodeId() + ", " + podcast.getPodcastId() + ")";
        } catch (SQLException sqlEx) {
            return "Payment failed  for the podcast episode (" + episode.getPodcastEpisodeId() + ", " + podcast.getPodcastId() + "). Error : " + sqlEx.getMessage();
        }
    }

    private void makePaymentToPodcastHost(PayPodcastHostDTO payment) throws SQLException {
        String query_PodcastHost = OperationQuery.INSERT_ACCOUNTS;
        String[] generatedColumns = { "transac_id" };
        String query_pays_ph = OperationQuery.PAY_PODCAST_HOST;

        Connection connection = getConnection();

        try (
                PreparedStatement statement1 = connection.prepareStatement(query_PodcastHost, generatedColumns);
                PreparedStatement statement2 = connection.prepareStatement(query_pays_ph);
        ) {
            connection.setAutoCommit(false);

            statement1.setDouble(1, payment.getAmount());
            statement1.setString(2, payment.getPayer());
            statement1.setString(3, payment.getPayee());
            statement1.setDate(4, payment.getPaymentDate());

            int rowsAffected1 = statement1.executeUpdate();
            if (rowsAffected1 <= 0) throw new SQLException("Transaction failed");

            long transacId = -1L;
            ResultSet result = statement1.getGeneratedKeys();
            if (result.next()) {
                transacId = result.getLong(1);
            }
            result.close();

            if (transacId == -1L) throw new SQLException("Transaction not created");

            statement2.setLong(1, transacId);
            statement2.setLong(2, payment.getPodcastId());
            statement2.setLong(3, payment.getPodcastEpisodeId());
            statement2.setLong(4, payment.getPodcastHostId());

            int rowsAffected2 = statement2.executeUpdate();
            if (rowsAffected2 <= 0) throw new SQLException("Transaction failed");

            connection.commit();
        } catch (SQLException sqlEx) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
    }

    private boolean checkIfPodcastHostPaymentExists(Long podcastHostId, Long podcastId, Long podcastEpisodeId, int month, int year) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(OperationQuery.CHECK_IF_PODCAST_HOST_PAYMENT_EXISTS, Boolean.class, podcastHostId, podcastId, podcastEpisodeId, month, year));
    }

    private int getLastDateOfMonth(int month, int year) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> year % 4 == 0 ? 29 : 28;
            default -> 28; // 28th would be present in all the months, safe case
        };
    }

}
