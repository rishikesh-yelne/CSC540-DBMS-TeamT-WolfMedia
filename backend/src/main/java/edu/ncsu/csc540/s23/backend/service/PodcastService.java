package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.Podcast;
import edu.ncsu.csc540.s23.backend.model.User;
import edu.ncsu.csc540.s23.backend.model.Sponsor;
import edu.ncsu.csc540.s23.backend.model.dto.PodcastRatingDTO;
import edu.ncsu.csc540.s23.backend.model.relationships.Rates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Random;

@Service
public class PodcastService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserService userService;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public PodcastService(UserService userService) {
        this.userService = userService;
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
            statement.setDouble(5, podcast.getFlatFee());

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
            podcast.setFlatFee(rs.getDouble(6));
            return podcast;
        }, id);
    }

    //update podcast
    public boolean updatePodcast(Podcast podcast) {
        return jdbcTemplate.update(OperationQuery.UPDATE_PODCAST, podcast.getPodcastHostId(), podcast.getPodcastName(), podcast.getPodcastLanguage(), podcast.getCountry(), podcast.getPodcastId()) >0 ;
    }

    public List<Podcast> getPodcastsByPodcastHost(Long podcastHostId) {
        return jdbcTemplate.query(OperationQuery.GET_PODCAST_BY_PODCAST_HOST_ID, BeanPropertyRowMapper.newInstance(Podcast.class), podcastHostId);
    }

    public boolean addSponsorToPodcast(Long podcastId, Long sponsorId) {
        return jdbcTemplate.update(OperationQuery.ADD_SPONSOR_TO_PODCAST, podcastId, sponsorId) > 0;
    }

    public List<Sponsor> getSponsorsOfPodcast(Long podcastId) {
        return jdbcTemplate.query(OperationQuery.GET_SPONSORS_OF_PODCAST, BeanPropertyRowMapper.newInstance(Sponsor.class), podcastId);
    }
    public boolean assignPodcastHost(Long podcastId, Long podcastHostId){
        return jdbcTemplate.update(OperationQuery.ASSIGN_PODCAST_HOST, podcastHostId, podcastId)>0;
    }

    public boolean ratePodcast(Long podcastId, Long userId, Double rating) {
        return jdbcTemplate.update(OperationQuery.RATE_PODCAST, podcastId, userId, rating) > 0;
    }

    public Double getPodcastRating(Long podcastId) {
        return jdbcTemplate.queryForObject(OperationQuery.GET_PODCAST_RATING_FOR_PODCAST, Double.class, podcastId);
    }

    public List<Rates> getPodcastRatings(Long podcastId) {
        return jdbcTemplate.query(OperationQuery.GET_PODCAST_RATINGS_FOR_PODCAST, BeanPropertyRowMapper.newInstance(Rates.class), podcastId);
    }

    public List<PodcastRatingDTO> getPodcastRatings() {
        return jdbcTemplate.query(OperationQuery.GET_PODCAST_RATINGS, BeanPropertyRowMapper.newInstance(PodcastRatingDTO.class));
    }
    //increment subscriber count by 1
    public boolean incrementSubscriberCount(Long userId, Long podcastId) {
        return jdbcTemplate.update(OperationQuery.INSERT_SUBSCRIBES_TO, userId, podcastId) > 0;
    }

    public String updateSubscriberCount(Long podcastId, int count) {
        StringBuffer result = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setFirstName("Dummy");
            user.setLastName("User" + System.currentTimeMillis());
            user.setEmailId(user.getFirstName() + "." + user.getLastName() + "@ncsu.edu");
            user.setPhoneNum("+1("
                    + (100 + random.nextInt(999 - 100) + 1)
                    + ")"
                    + (100 + random.nextInt(999 - 100) + 1)
                    + "-"
                    + (1000 + random.nextInt(9999 - 1000) + 1));
            user.setRegDate(new Date(System.currentTimeMillis()));
            Long userId = this.userService.createNewUser(user);

            boolean success = incrementSubscriberCount(userId, podcastId);
            result.append(success
                    ? "User " + user.getFirstName() + " " + user.getLastName() + " subscribed to the podcast.\n"
                    : "User subscription failed");
        }
        return result.toString();
    }
}