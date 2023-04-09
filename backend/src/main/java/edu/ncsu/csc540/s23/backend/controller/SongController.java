package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Song;
import edu.ncsu.csc540.s23.backend.model.Sponsor;
import edu.ncsu.csc540.s23.backend.service.SongService;
import edu.ncsu.csc540.s23.backend.service.SponsorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    private SongService songService;

    public SongController(SongService songService) { this.songService = songService;}

    @GetMapping("/all")
    public List<Song> getAllSongs() { return songService.getAllSongs(); }

    @PostMapping("/add")
    public Long addSong(@RequestBody Song song) { return songService.createNewSong(song); }
}
