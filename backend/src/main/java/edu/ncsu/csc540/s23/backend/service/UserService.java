package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.Artist;
import edu.ncsu.csc540.s23.backend.model.PodcastHost;
import edu.ncsu.csc540.s23.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
