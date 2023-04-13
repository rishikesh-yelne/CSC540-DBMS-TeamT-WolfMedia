package edu.ncsu.csc540.s23.backend.model.dto;

public class PodcastRatingDTO {
    private Double rating;
    private Long podcastId;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Long podcastId) {
        this.podcastId = podcastId;
    }
}
