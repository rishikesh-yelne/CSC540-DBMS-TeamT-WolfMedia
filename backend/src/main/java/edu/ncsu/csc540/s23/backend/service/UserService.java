package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.Artist;
import edu.ncsu.csc540.s23.backend.model.PodcastHost;
import edu.ncsu.csc540.s23.backend.model.User;
import edu.ncsu.csc540.s23.backend.model.dto.AlbumMonthlyPlayCount;
import edu.ncsu.csc540.s23.backend.model.dto.AlbumPlayCount;
import edu.ncsu.csc540.s23.backend.model.dto.ArtistMonthlyPlayCount;
import edu.ncsu.csc540.s23.backend.model.dto.ArtistPlayCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
    public List<User> getAllUsers() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_USERS, BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getUser(Long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(OperationQuery.GET_USER_BY_ID);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            User user = new User();
            while (result.next()) {
                user.setUserId(result.getLong(1));
                user.setFirstName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setEmailId(result.getString(4));
                user.setPhoneNum(result.getString(5));
                user.setRegDate(result.getDate(6));
            }
            result.close();
            statement.close();

            return user;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public List<Artist> getAllArtists() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_ARTISTS, BeanPropertyRowMapper.newInstance(Artist.class));
    }

    public Artist getArtist(Long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(OperationQuery.GET_ARTIST_BY_ID);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Artist artist = new Artist();
            while (result.next()) {
                artist.setUserId(result.getLong(1));
                artist.setFirstName(result.getString(2));
                artist.setLastName(result.getString(3));
                artist.setEmailId(result.getString(4));
                artist.setPhoneNum(result.getString(5));
                artist.setRegDate(result.getDate(6));
                artist.setRecordLabelId(result.getLong(7));
                artist.setPrimaryGenreId(result.getLong(8));
                artist.setStatus(result.getString(9));
                artist.setType(result.getString(10));
                artist.setArtistCountry(result.getString(11));
            }
            result.close();
            statement.close();

            return artist;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    // delete artist
    public boolean deleteArtist(Long id) {
        return jdbcTemplate.update(OperationQuery.DELETE_ARTIST, id) >0;
    }

    public List<PodcastHost> getAllPodcastHosts() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_PODCAST_HOSTS, BeanPropertyRowMapper.newInstance(PodcastHost.class));
    }

    public PodcastHost getPodcastHost(Long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(OperationQuery.GET_PODCAST_HOST_BY_ID);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            PodcastHost podcastHost = new PodcastHost();
            while (result.next()) {
                podcastHost.setUserId(result.getLong(1));
                podcastHost.setFirstName(result.getString(2));
                podcastHost.setLastName(result.getString(3));
                podcastHost.setEmailId(result.getString(4));
                podcastHost.setPhoneNum(result.getString(5));
                podcastHost.setRegDate(result.getDate(6));
                podcastHost.setCity(result.getString(7));
            }
            result.close();
            statement.close();

            return podcastHost;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public Long createNewUser(User user) {
        try {
            Connection connection = getConnection();
            String[] generatedColumns = { "user_id" };
            PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_USER, generatedColumns);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmailId());
            statement.setString(4, user.getPhoneNum());
            statement.setDate(5, user.getRegDate());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected <= 0) throw new SQLException("User creation failed");

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getLong(1);
            }
            result.close();
            statement.close();
            return -1L;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public boolean createNewArtist(Artist artist) {
        try {
            Connection connection = getConnection();
            Long userId = createNewUser(artist);
            if (userId != -1L) {
                PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_ARTIST);
                statement.setLong(1, userId);
                statement.setLong(2, artist.getRecordLabelId());
                statement.setLong(3, artist.getPrimaryGenreId());
                statement.setString(4, artist.getStatus());
                statement.setString(5, artist.getType());
                statement.setString(6, artist.getArtistCountry());

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } else return false;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public boolean createNewPodcastHost(PodcastHost podcastHost) {
        try {
            Connection connection = getConnection();
            Long userId = createNewUser(podcastHost);
            if (userId != -1L) {
                PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_PODCAST_HOST);
                statement.setLong(1, userId);
                statement.setString(2, podcastHost.getCity());

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } else return false;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public boolean updateArtist(Artist artist){
        int rowsAffected = jdbcTemplate.update(OperationQuery.UPDATE_ARTIST, artist.getFirstName(), artist.getLastName(), artist.getEmailId(), artist.getPhoneNum(), artist.getRegDate(), artist.getRecordLabelId(), artist.getPrimaryGenreId(), artist.getStatus(), artist.getType(), artist.getArtistCountry(), artist.getUserId());
        return rowsAffected>0;
    }

    public boolean updatePodcastHost(PodcastHost podcastHost){
        int rowsAffected = jdbcTemplate.update(OperationQuery.UPDATE_PODCAST_HOST, podcastHost.getFirstName(), podcastHost.getLastName(), podcastHost.getEmailId(), podcastHost.getPhoneNum(), podcastHost.getRegDate(), podcastHost.getCity(), podcastHost.getUserId());
        return rowsAffected>0;
    }

    //delete podcast host
    public boolean deletePodcastHost(Long id) {
        return jdbcTemplate.update(OperationQuery.DELETE_PODCAST_HOST, id) >0;
    }

    public boolean assignGenreToArtist(Long genreId, Long artistId){
        return jdbcTemplate.update(OperationQuery.ASSIGN_PRIMARY_GENRE_TO_ARTIST, genreId, artistId)>0;
    }


    public List<ArtistMonthlyPlayCount> getPlayCount(Long artistId, int month, int year) {
        String query;
        if (month < LocalDate.now().getMonth().getValue()
                && year < LocalDate.now().getYear()) {
            query = OperationQuery.GET_ARTIST_HISTORICAL_PLAY_COUNT_FOR_MONTH;
        } else {
            query = OperationQuery.GET_ARTIST_PLAY_COUNT_FOR_MONTH;
        }
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtistMonthlyPlayCount.class), artistId, month, year);
    }

    public List<ArtistPlayCount> getPlayCount(Long artistId) {
        return jdbcTemplate.query(OperationQuery.GET_ARTIST_PLAY_COUNT, BeanPropertyRowMapper.newInstance(ArtistPlayCount.class), artistId, artistId);
    }
}
