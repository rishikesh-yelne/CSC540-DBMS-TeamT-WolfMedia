package edu.ncsu.csc540.s23.backend.model;

import java.sql.Time;
import java.time.Instant;

public class Song {

    private Long songId;

    private Long albumId;

    private String title;

    private Time duration;

    private Long trackNo;

    private Instant releaseDate;

    private String releaseCountry;

    private String language;

    private Double royaltyRate;

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Long getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(Long trackNo) {
        this.trackNo = trackNo;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseCountry() {
        return releaseCountry;
    }

    public void setReleaseCountry(String releaseCountry) {
        this.releaseCountry = releaseCountry;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getRoyaltyRate() {
        return royaltyRate;
    }

    public void setRoyaltyRate(Double royaltyRate) {
        this.royaltyRate = royaltyRate;
    }
}
