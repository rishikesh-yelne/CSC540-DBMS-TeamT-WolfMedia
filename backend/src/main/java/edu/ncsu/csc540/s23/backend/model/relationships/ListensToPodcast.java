package edu.ncsu.csc540.s23.backend.model.relationships;

import java.sql.Timestamp;

public class ListensToPodcast {

    private Long podcastEpisodeId;

    private Long podcastId;

    private Long userId;

    private Timestamp podcastTimestamp;

    public Long getPodcastEpisodeId() {
        return podcastEpisodeId;
    }

    public void setPodcastEpisodeId(Long podcastEpisodeId) {
        this.podcastEpisodeId = podcastEpisodeId;
    }

    public Long getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Long podcastId) {
        this.podcastId = podcastId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getPodcastTimestamp() {
        return podcastTimestamp;
    }

    public void setPodcastTimestamp(Timestamp podcastTimestamp) {
        this.podcastTimestamp = podcastTimestamp;
    }
}
