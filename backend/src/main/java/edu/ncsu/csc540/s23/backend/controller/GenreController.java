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

    @GetMapping("/all")
    public List<Genre> getAllGenres() { return genreService.getAllGenres(); }

    @PostMapping("/add-genre")
    public Long addGenre(@RequestBody Genre genre) { return genreService.createNewGenre(genre); }
}
