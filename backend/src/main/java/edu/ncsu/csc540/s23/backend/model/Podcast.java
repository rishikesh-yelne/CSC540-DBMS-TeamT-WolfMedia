package edu.ncsu.csc540.s23.backend.model;

public class Podcast {

    private Long podcastId;

    private String podcastName;

    private String podcastLanguage;

    private String country;

    public Long getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Long podcastId) {
        this.podcastId = podcastId;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public String getPodcastLanguage() {
        return podcastLanguage;
    }

    public void setPodcastLanguage(String podcastLanguage) {
        this.podcastLanguage = podcastLanguage;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
