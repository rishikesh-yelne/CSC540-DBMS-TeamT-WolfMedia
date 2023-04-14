package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Album;
import edu.ncsu.csc540.s23.backend.model.Song;
import edu.ncsu.csc540.s23.backend.model.dto.AlbumMonthlyPlayCount;
import edu.ncsu.csc540.s23.backend.model.dto.AlbumPlayCount;
import edu.ncsu.csc540.s23.backend.service.AlbumService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/all")
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable Long id) {
        return albumService.getAlbum(id);
    }

    @PostMapping("/add")
    public Long addAlbum(@RequestBody Album album) {
        return albumService.createNewAlbum(album);
    }

    @GetMapping("/play-count/{month}/{year}")
    public List<AlbumMonthlyPlayCount> getPlayCount(
            @RequestParam Long albumId,
            @PathVariable int month,
            @PathVariable int year) {
        return this.albumService.getPlayCount(albumId, month, year);
    }

    @GetMapping("/play-count")
    public List<AlbumPlayCount> getPlayCount(@RequestParam Long albumId) {
        return this.albumService.getPlayCount(albumId);
    }
}
