package edu.ncsu.csc540.s23.backend.model.relationships;

public class HasGenre {

    private Long genreId;

    private Long podcastId;

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Long getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Long podcastId) {
        this.podcastId = podcastId;
    }
}
