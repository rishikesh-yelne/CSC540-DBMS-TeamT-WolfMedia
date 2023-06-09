package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.PodcastEpisode;
import edu.ncsu.csc540.s23.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Random;

@Service
public class PodcastEpisodeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserService userService;

    public PodcastEpisodeService(UserService userService) {
        this.userService = userService;
    }

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

    public List<PodcastEpisode> getPodcastEpisodesByPodcast(Long podcastId, int month, int year) {
        return jdbcTemplate.query(OperationQuery.GET_PODCAST_EPISODES_BY_PODCAST_TILL_MONTH_YEAR, BeanPropertyRowMapper.newInstance(PodcastEpisode.class), podcastId, month, year);
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

    public boolean assignGuestSpeaker(Long gspeaker_id ,Long pepi_id, Long podcast_id){
        return jdbcTemplate.update(OperationQuery.ASSIGN_GUEST_SPEAKER_TO_PODCAST_EPISODE, gspeaker_id ,pepi_id, podcast_id)>0;
    }

    //increment podcast episode listen count by 1
    public boolean incrementListenCount(Long podcastEpisodeId, Long podcastId, Long userId) {
        return jdbcTemplate.update(OperationQuery.INSERT_LISTENS_TO_P, userId, podcastEpisodeId, podcastId, new Timestamp(System.currentTimeMillis())) > 0;
    }

    //increment podcast episode listen count by X
    public boolean updateListenCount(Long podcastEpisodeId, Long podcastId, Long count) {

        List<User> users = this.userService.getAllUsers();

        int[] rowsAffected = jdbcTemplate.batchUpdate(OperationQuery.INSERT_LISTENS_TO_P, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Random rand = new Random();
                ps.setLong(1, users.get(rand.nextInt(users.size())).getUserId());
                ps.setLong(2, podcastEpisodeId);
                ps.setLong(3,podcastId);
                ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()+i*1000));
            }

            @Override
            public int getBatchSize() {
                return Math.toIntExact(count);
            }
        });
        return rowsAffected.length>0;

    }

}