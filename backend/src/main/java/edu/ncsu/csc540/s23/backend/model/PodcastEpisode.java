package edu.ncsu.csc540.s23.backend.model;

import java.sql.Date;
import java.sql.Time;

public class PodcastEpisode {

    private Long podcastEpisodeId;

    private Long podcastId;

    private String episodeTitle;

    private Date podcastReleaseDate;

    private Time podcastDuration;

    private Long advertisementCount;

    private Long episodeNo;

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

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public Date getPodcastReleaseDate() {
        return podcastReleaseDate;
    }

    public void setPodcastReleaseDate(Date podcastReleaseDate) { this.podcastReleaseDate = podcastReleaseDate; }

    public Time getPodcastDuration() {
        return podcastDuration;
    }

    public void setPodcastDuration(Time podcastDuration) {
        this.podcastDuration = podcastDuration;
    }

    public Long getAdvertisementCount() {
        return advertisementCount;
    }

    public void setAdvertisementCount(Long advertisementCount) {
        this.advertisementCount = advertisementCount;
    }

    public Long getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(Long episodeNo) {
        this.episodeNo = episodeNo;
    }
}
