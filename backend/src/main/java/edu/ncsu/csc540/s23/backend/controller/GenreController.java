package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Genre;
import edu.ncsu.csc540.s23.backend.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private GenreService genreService;

    public GenreController(GenreService genreService) { this.genreService = genreService;}

//    gets all genres
    @GetMapping("/all")
    public List<Genre> getAllGenres() { return genreService.getAllGenres(); }

    //    gets genre by id
    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable Long id) { return genreService.getGenre(id); }

//    adds a new genre
    @PostMapping("/add")
    public Long addGenre(@RequestBody Genre genre) { return genreService.createNewGenre(genre); }

    //    updates a genre given its id
    @PutMapping()
    public boolean updateGenre(@RequestBody Genre genre) { return genreService.updateGenre(genre); }

    //    updates a genre's name given its id
    @PatchMapping("/{id}")
    public boolean updateGenreName(@PathVariable Long id, @RequestBody String name) { return genreService.updateGenreName(id, name); }
}
