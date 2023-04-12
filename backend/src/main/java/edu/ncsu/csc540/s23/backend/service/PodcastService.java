package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.Podcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

            statement.setLong(1, podcast.getPodcastHostId());
            statement.setString(2, podcast.getPodcastName());
            statement.setString(3, podcast.getPodcastLanguage());
            statement.setString(4,podcast.getCountry());

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
        return jdbcTemplate.queryForObject(OperationQuery.GET_PODCAST_BY_ID, (rs, rowNum) -> {
            Podcast podcast = new Podcast();
            podcast.setPodcastId(rs.getLong(1));
            podcast.setPodcastHostId(rs.getLong(2));
            podcast.setPodcastName(rs.getString(3));
            podcast.setPodcastLanguage(rs.getString(4));
            podcast.setCountry(rs.getString(5));
            return podcast;
        }, id);
    }

    //update podcast
    public boolean updatePodcast(Podcast podcast) {
        return jdbcTemplate.update(OperationQuery.UPDATE_PODCAST, podcast.getPodcastHostId(), podcast.getPodcastName(), podcast.getPodcastLanguage(), podcast.getCountry(), podcast.getPodcastId()) >0 ;
    }

}