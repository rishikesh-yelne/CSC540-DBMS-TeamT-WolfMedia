package edu.ncsu.csc540.s23.backend.model.relationships;

public class SubscribesTo {

    private Long userId;

    private Long podcastId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Long podcastId) {
        this.podcastId = podcastId;
    }
}
