package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.RecordLabel;
import edu.ncsu.csc540.s23.backend.model.Song;
import edu.ncsu.csc540.s23.backend.model.dto.ArtistPaymentDTO;
import edu.ncsu.csc540.s23.backend.model.dto.PayRecordDTO;
import edu.ncsu.csc540.s23.backend.model.dto.PodcastHostPaymentDTO;
import edu.ncsu.csc540.s23.backend.model.dto.RecordLabelPaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SongService songService;
    private RecordLabelService recordLabelService;

    public PaymentService(
            SongService songService,
            RecordLabelService recordLabelService) {
        this.songService = songService;
        this.recordLabelService = recordLabelService;
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

    public String payRecordLabel(PayRecordDTO payment, Optional<Integer> month, Optional<Integer> year) {
        int paymentMonth, paymentYear;
        if (month.isEmpty() || year.isEmpty()) {
            // month and year is empty, make payment for current month and year
            paymentMonth = LocalDate.now().getMonth().getValue();
            paymentYear = LocalDate.now().getYear();
        } else {
            paymentMonth = month.get();
            paymentYear = year.get();
        }
        // first check if there exists a payment for the given song, month and year
        boolean paymentExists = checkIfRecordPaymentExists(
                payment.getRecordLabelId(),
                payment.getSongId(),
                payment.getAlbumId(),
                paymentMonth,
                paymentYear);

        if (paymentExists) return "Payment already done for the month & year : " + Month.of(paymentMonth).name() + " " + paymentYear;

        // payment does not exist, calculate payment for the Song
        Song song = this.songService.getSong(payment.getSongId(), payment.getAlbumId());
        Long playCount = this.songService.getPlayCount(payment.getSongId(), payment.getAlbumId(), paymentMonth, paymentYear);
        //RecordLabel recordLabel = this.recordLabelService.getRecordLabel(payment.getRecordLabelId());

        payment.setPayer("WolfMedia");
        //payment.setPayee(recordLabel.getRecordLabelName());
        payment.setAmount(song.getRoyaltyRate()*playCount);
        payment.setPaymentDate(Date.valueOf(LocalDate.of(paymentYear, paymentMonth, getLastDateOfMonth(paymentMonth, paymentYear))));
        try {
            makePaymentToRecordLabel(payment);
            return "Payment successful";
        } catch (SQLException sqlEx) {
            return "Payment failed. Error : " + sqlEx.getMessage();
        }
    }

    private int getLastDateOfMonth(int month, int year) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> year % 4 == 0 ? 29 : 28;
            default -> 28; // 28th would be present in all the months, safe case
        };
    }

    private void makePaymentToRecordLabel(PayRecordDTO payment) throws SQLException {
        String query_RecordLabel = OperationQuery.PAY_RECORD_LABEL_ACCOUNTS;
        String[] generatedColumns = { "transac_id" };
        String query_pays_record = OperationQuery.PAY_RECORD_LABEL;

        Connection connection = ConnectionService.getConnection();

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
}
