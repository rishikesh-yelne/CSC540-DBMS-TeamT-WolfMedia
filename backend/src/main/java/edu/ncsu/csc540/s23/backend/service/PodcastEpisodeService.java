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

    public PodcastEpisode getPodcastEpisode(Long podcastEpisodeId, Long podcastId) {
        return jdbcTemplate.queryForObject(OperationQuery.GET_PODCAST_EPISODE_BY_ID, (rs, rowNum) -> {
            PodcastEpisode episode = new PodcastEpisode();
            episode.setPodcastEpisodeId(rs.getLong(1));
            episode.setPodcastId(rs.getLong(2));
            episode.setEpisodeTitle(rs.getString(3));
            episode.setPodcastReleaseDate(rs.getDate(4));
            episode.setPodcastDuration(rs.getTime(5));
            episode.setAdvertisementCount(rs.getLong(6));
            episode.setEpisodeNo(rs.getLong(7));

            return episode;
        }, podcastEpisodeId, podcastId);
    }
    public List<PodcastEpisode> getPodcastEpisodesByPodcast(Long podcastId) {
        return jdbcTemplate.query(OperationQuery.GET_PODCAST_EPISODES_BY_PODCAST, BeanPropertyRowMapper.newInstance(PodcastEpisode.class), podcastId);
    }

    public boolean updatePodcastEpisode(PodcastEpisode podcastEpisode) {
        return jdbcTemplate.update(OperationQuery.UPDATE_PODCAST_EPISODE, podcastEpisode.getPodcastId(), podcastEpisode.getEpisodeTitle(), podcastEpisode.getPodcastReleaseDate(), podcastEpisode.getPodcastDuration(), podcastEpisode.getAdvertisementCount(), podcastEpisode.getEpisodeNo(), podcastEpisode.getPodcastEpisodeId())>0 ;
    }

    public boolean updatePodcastEpisodePodcastId(Long podcastEpisodeId, Long podcastId){
        return jdbcTemplate.update(OperationQuery.UPDATE_PODCAST_EPISODE_PODCAST_ID, podcastId, podcastEpisodeId) >0;
    }

    //delete podcast episode
    public boolean deletePodcastEpisode(Long id) {
        return jdbcTemplate.update(OperationQuery.DELETE_PODCAST_EPISODE, id) >0;
    }
}