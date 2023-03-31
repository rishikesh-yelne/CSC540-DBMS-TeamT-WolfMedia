package edu.ncsu.csc540.s23.backend.model.relationships;

public class Sponsors {

    private Long sponsorId;

    private Long podcastId;

    public Long getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Long sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Long getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Long podcastId) {
        this.podcastId = podcastId;
    }
}
