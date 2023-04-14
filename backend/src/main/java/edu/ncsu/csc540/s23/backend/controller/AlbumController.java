package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Album;
import edu.ncsu.csc540.s23.backend.model.Song;
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

//    gets all albums
    @GetMapping("/all")
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

//    gets album by id
    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable Long id) {
        return albumService.getAlbum(id);
    }

//    adds a new album
    @PostMapping("/add")
    public Long addAlbum(@RequestBody Album album) {
        return albumService.createNewAlbum(album);
    }

}
