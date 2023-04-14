package edu.ncsu.csc540.s23.backend.model.dto;

public class AlbumMonthlyPlayCount {
    private Long playCount;
    private Long songId;

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }
}
