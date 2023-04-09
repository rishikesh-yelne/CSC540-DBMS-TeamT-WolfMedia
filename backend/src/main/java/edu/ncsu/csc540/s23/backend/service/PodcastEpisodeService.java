package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.PodcastEpisode;
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
public class PodcastEpisodeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<PodcastEpisode> getAllPodcastEpisodes() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_PODCAST_EPISODES, BeanPropertyRowMapper.newInstance(PodcastEpisode.class));
    }

    public Long createNewPodcastEpisode(PodcastEpisode podcastEpisode) {
        try {
            Connection connection = getConnection();
            String[] generatedColumns = { "pepi_id" };
            PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_PODCAST_EPISODE, generatedColumns);
            statement.setLong(1,podcastEpisode.getPodcastId());
            statement.setString(2, podcastEpisode.getEpisodeTitle());
            statement.setDate(3, podcastEpisode.getPodcastReleaseDate());
            statement.setTime(4, podcastEpisode.getPodcastDuration());
            statement.setLong(5, podcastEpisode.getAdvertisementCount());
            statement.setLong(6, podcastEpisode.getEpisodeNo());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected <= 0) throw new SQLException("Podcast Episode creation failed");

            ResultSet result = statement.getGeneratedKeys();
            if(result.next()) {
                return result.getLong(1);
            }

            return -1L;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}