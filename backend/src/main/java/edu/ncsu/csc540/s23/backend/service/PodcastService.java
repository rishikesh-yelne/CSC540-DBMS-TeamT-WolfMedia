package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.Podcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class PodcastService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public List<Podcast> getAllPodcasts() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_PODCASTS, BeanPropertyRowMapper.newInstance(Podcast.class));
    }

    public Long createNewPodcast(Podcast podcast) {
        try {
            Connection connection = getConnection();
            String[] generatedColumns = {"podcast_id"};
            PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_PODCAST, generatedColumns);
            statement.setString(1, podcast.getPodcastName());
            statement.setString(2, podcast.getPodcastLanguage());
            statement.setString(3,podcast.getCountry());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected <= 0) throw new SQLException("Podcast creation failed");

            ResultSet result = statement.getGeneratedKeys();
            if(result.next()) {
                return result.getLong(1);
            }

            return -1L;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public Podcast getPodcast(Long id) {
        try {
            Connection connectoin = getConnection();
            String[] generatedColumns = { "podcast_id" };
            PreparedStatement statement = connectoin.prepareStatement(OperationQuery.GET_PODCAST_BY_ID, generatedColumns);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Podcast podcast = new Podcast();
            while( result.next()) {
                podcast.setPodcastId(result.getLong(1));
                podcast.setPodcastName(result.getString(2));
                podcast.setPodcastLanguage(result.getString(3));
                podcast.setCountry(result.getString(4));

            }
            result.close();
            statement.close();

            return podcast;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}