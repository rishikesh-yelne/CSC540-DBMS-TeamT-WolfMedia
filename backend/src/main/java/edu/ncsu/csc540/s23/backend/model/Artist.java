package edu.ncsu.csc540.s23.backend.model;

public class Artist extends User {
    private Long recordLabelId;

    private Long primaryGenreId;

    private String status;

    private String type;

    private String artistCountry;

    public Long getRecordLabelId() {
        return recordLabelId;
    }

    public void setRecordLabelId(Long recordLabelId) {
        this.recordLabelId = recordLabelId;
    }

    public Long getPrimaryGenreId() {
        return primaryGenreId;
    }

    public void setPrimaryGenreId(Long primaryGenreId) {
        this.primaryGenreId = primaryGenreId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArtistCountry() {
        return artistCountry;
    }

    public void setArtistCountry(String artistCountry) {
        this.artistCountry = artistCountry;
    }
}
