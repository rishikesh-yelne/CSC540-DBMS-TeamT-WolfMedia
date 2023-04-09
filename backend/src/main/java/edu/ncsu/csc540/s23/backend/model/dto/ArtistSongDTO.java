package edu.ncsu.csc540.s23.backend.model.dto;

import edu.ncsu.csc540.s23.backend.model.Song;

public class ArtistSongDTO extends Song {
    private boolean isMainArtist;

    public boolean isMainArtist() {
        return isMainArtist;
    }

    public void setMainArtist(boolean mainArtist) {
        isMainArtist = mainArtist;
    }
}
