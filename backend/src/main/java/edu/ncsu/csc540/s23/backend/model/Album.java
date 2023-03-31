package edu.ncsu.csc540.s23.backend.model;

import java.time.Instant;

public class Album {

    private Long albumId;

    private String albumName;

    private Instant releaseYear;

    private String edition;

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Instant getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Instant releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
