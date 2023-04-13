package edu.ncsu.csc540.s23.backend.model;

public class Podcast {

    private Long podcastId;

    private Long podcastHostId;

    private String podcastName;

    private String podcastLanguage;

    private String country;

    public Long getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Long podcastId) {
        this.podcastId = podcastId;
    }
    public Long getPodcastHostId() {
        return podcastHostId;
    }

    public void setPodcastHostId(Long podcastHostId) {
        this.podcastHostId = podcastHostId;
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
