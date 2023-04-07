package edu.ncsu.csc540.s23.backend.model.dto;

import edu.ncsu.csc540.s23.backend.model.Artist;
import edu.ncsu.csc540.s23.backend.model.Genre;
import edu.ncsu.csc540.s23.backend.model.RecordLabel;

public class ArtistDTO extends Artist {
    private RecordLabel recordLabel;

    private Genre primaryGenre;

    public RecordLabel getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(RecordLabel recordLabel) {
        this.recordLabel = recordLabel;
    }

    public Genre getPrimaryGenre() {
        return primaryGenre;
    }

    public void setPrimaryGenre(Genre primaryGenre) {
        this.primaryGenre = primaryGenre;
    }
}
