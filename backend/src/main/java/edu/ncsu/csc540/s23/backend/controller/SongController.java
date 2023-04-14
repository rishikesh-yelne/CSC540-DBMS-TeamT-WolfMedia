package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Genre;
import edu.ncsu.csc540.s23.backend.model.Song;
import edu.ncsu.csc540.s23.backend.model.dto.ArtistSongDTO;
import edu.ncsu.csc540.s23.backend.model.Sponsor;
import edu.ncsu.csc540.s23.backend.model.User;
import edu.ncsu.csc540.s23.backend.model.dto.SongPlayCount;
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

    @GetMapping("/{song_id}/{album_id}")
    public Song getSong(@PathVariable Long song_id, @PathVariable Long album_id) { return songService.getSong(song_id, album_id); }

    @PostMapping("/add")
    public Long addSong(@RequestBody Song song) { return songService.createNewSong(song); }

    @GetMapping("/artist/{artistId}")
    public List<ArtistSongDTO> getSongsByArtist(@PathVariable Long artistId) { return songService.getSongsByArtist(artistId); }

    @GetMapping("/album/{albumId}")
    public List<Song> getSongsByAlbum(@PathVariable Long albumId) { return songService.getSongsByAlbum(albumId); }

    @PutMapping("/update")
    public boolean updateSong(@RequestBody Song song){return songService.updateSong(song);}

    @DeleteMapping("/{song_id}")
    public boolean deleteSong(@PathVariable Long song_id){return songService.deleteSong(song_id);}

    @PostMapping("/add-genre")
    public boolean assignGenreToSong(@RequestParam Long id, @RequestParam Long song_id, @RequestParam Long album_id) { return songService.assignGenreToSong(id, song_id, album_id); }

    @GetMapping("/genre")
    public List<Genre> getGenreOfSong(@RequestParam Long songId, @RequestParam Long albumId) { return songService.getGenreOfSong(songId, albumId); }

    //increment song listen count by 1
    @PostMapping("/listen")
    public boolean incrementListenCount(@RequestParam(name="song_id") Long songId, @RequestParam(name="album_id") Long albumId, @RequestParam(name="user_id") Long userId) { return songService.incrementListenCount(songId, albumId, userId); }

    //update listen count by X
    @PostMapping("/update-listen")
    public boolean updateListenCount(@RequestParam(name="song_id") Long songId, @RequestParam(name="album_id") Long albumId, @RequestParam(name="count") Long count) { return songService.updateListenCount(songId, albumId, count); }

    @PatchMapping("/assign-artist")
    public boolean assignArtistToSong(@RequestParam(name = "song_id") Long song_id, @RequestParam Long album_id, @RequestParam() Long user_id, @RequestParam Long is_main){return songService.assignArtistToSong(song_id, album_id, user_id, is_main);}

    @GetMapping("/play-count/{month}/{year}")
    public Long getPlayCount(
            @RequestParam Long songId,
            @RequestParam Long albumId,
            @PathVariable int month,
            @PathVariable int year) {
        return this.songService.getPlayCount(songId, albumId, month, year);
    }

    @GetMapping("/play-count")
    public List<SongPlayCount> getPlayCount(
            @RequestParam Long songId,
            @RequestParam Long albumId) {
        return this.songService.getPlayCount(songId, albumId);
    }
}