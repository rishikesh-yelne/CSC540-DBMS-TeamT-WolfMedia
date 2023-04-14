package edu.ncsu.csc540.s23.backend.model.dto;

public class AlbumPlayCount extends SongPlayCount {
    private Long songId;

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }
}
