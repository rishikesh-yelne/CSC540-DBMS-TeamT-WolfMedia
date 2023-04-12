package edu.ncsu.csc540.s23.backend.model.dto;

public class PayPodcastHostDTO extends PaymentDTO {
    private Long podcastId;
    private Long podcastEpisodeId;
    private Long podcastHostId;

    public Long getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Long podcastId) {
        this.podcastId = podcastId;
    }

    public Long getPodcastEpisodeId() {
        return podcastEpisodeId;
    }

    public void setPodcastEpisodeId(Long podcastEpisodeId) {
        this.podcastEpisodeId = podcastEpisodeId;
    }

    public Long getPodcastHostId() {
        return podcastHostId;
    }

    public void setPodcastHostId(Long podcastHostId) {
        this.podcastHostId = podcastHostId;
    }
}
