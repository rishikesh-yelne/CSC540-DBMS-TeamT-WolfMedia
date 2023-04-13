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

    public AlbumController(AlbumService albumService) { this.albumService = albumService;}

    @GetMapping("/all")
    public List<Album> getAllAlbums() { return albumService.getAllAlbums(); }

    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable Long id) { return albumService.getAlbum(id); }

    @PostMapping("/add")
    public Long addAlbum(@RequestBody Album album) { return albumService.createNewAlbum(album); }

    @PatchMapping("/{artistId}/{albumId}")
    public boolean assignArtistToAlbum(@PathVariable Long artistId, @PathVariable Long albumId){return albumService.assignArtistToAlbum(albumId, artistId);}
}
