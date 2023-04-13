package edu.ncsu.csc540.s23.backend.model.dto;

import java.sql.Date;

public class PayRecordDTO extends PaymentDTO {
    private Long recordLabelId;
    private Long songId;
    private Long albumId;

    public Long getRecordLabelId() {
        return recordLabelId;
    }

    public void setRecordLabelId(Long recordLabelId) {
        this.recordLabelId = recordLabelId;
    }

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
}
