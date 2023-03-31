package edu.ncsu.csc540.s23.backend.model.relationships;

public class HostedBy {

    private Long podcastId;

    private Long userId;

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
}
