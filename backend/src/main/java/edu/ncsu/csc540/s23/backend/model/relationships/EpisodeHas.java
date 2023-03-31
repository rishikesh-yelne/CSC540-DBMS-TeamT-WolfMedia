package edu.ncsu.csc540.s23.backend.model.relationships;

public class EpisodeHas {

    private Long podcastEpisodeId;

    private Long podcastId;

    private Long guestSpeakerId;

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

    public Long getGuestSpeakerId() {
        return guestSpeakerId;
    }

    public void setGuestSpeakerId(Long guestSpeakerId) {
        this.guestSpeakerId = guestSpeakerId;
    }
}
