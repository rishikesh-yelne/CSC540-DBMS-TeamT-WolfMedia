package edu.ncsu.csc540.s23.backend.model.relationships;

public class ContributedTo {

    private Long albumId;

    private Long userId;

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
