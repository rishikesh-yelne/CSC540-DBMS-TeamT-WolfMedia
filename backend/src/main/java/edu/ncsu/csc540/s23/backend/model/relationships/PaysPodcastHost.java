package edu.ncsu.csc540.s23.backend.model.relationships;

public class PaysPodcastHost {

    private Long transactionId;

    private Long podcastEpisodeId;

    private Long podcastId;

    private Long userId;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

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
}
