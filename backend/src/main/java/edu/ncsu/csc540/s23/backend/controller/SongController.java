package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Song;
import edu.ncsu.csc540.s23.backend.model.dto.ArtistSongDTO;
import edu.ncsu.csc540.s23.backend.model.Sponsor;
import edu.ncsu.csc540.s23.backend.model.User;
import edu.ncsu.csc540.s23.backend.service.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    private SongService songService;

    public SongController(SongService songService) { this.songService = songService;}

    @GetMapping("/all")
    public List<Song> getAllSongs() { return songService.getAllSongs(); }

    @GetMapping()
    public Song getSong(@RequestParam(name = "song_id") Long song_id, @RequestParam Long album_id) { return songService.getSong(song_id, album_id); }

    @PostMapping("/add")
    public Long addSong(@RequestBody Song song) { return songService.createNewSong(song); }

    @GetMapping("/artist/{artistId}")
    public List<ArtistSongDTO> getSongsByArtist(@PathVariable Long artistId) { return songService.getSongsByArtist(artistId); }

    @GetMapping("/album/{albumId}")
    public List<Song> getSongsByAlbum(@PathVariable Long albumId) { return songService.getSongsByAlbum(albumId); }

    @PutMapping()
    public boolean updateSong(@RequestBody Song song){return songService.updateSong(song);}

    @DeleteMapping("/{song_id}")
    public boolean deleteSong(@PathVariable Long song_id){return songService.deleteSong(song_id);}

    //increment song listen count by 1
    @PostMapping("/{userId}/listen")
    public boolean incrementListenCount(@RequestParam(name="song_id") Long songId, @RequestParam(name="album_id") Long albumId, @PathVariable Long userId) { return songService.incrementListenCount(songId, albumId, userId); }

    //update listen count by X
    @PostMapping("/update-listen")
    public boolean updateListenCount(@RequestParam(name="song_id") Long songId, @RequestParam(name="album_id") Long albumId, @RequestParam(name="count") Long count) { return songService.updateListenCount(songId, albumId, count); }
}
