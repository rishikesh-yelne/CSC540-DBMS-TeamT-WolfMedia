package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Podcast;
import edu.ncsu.csc540.s23.backend.service.PodcastService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/podcast")
public class PodcastController {

    private PodcastService podcastService;

    public PodcastController(PodcastService podcastService) { this.podcastService = podcastService; }

    @GetMapping("/all")
    public List<Podcast> getAllPodcasts() { return podcastService.getAllPodcasts(); }

    @PostMapping("/add")
    public Long addPodcast(@RequestBody Podcast podcast) { return podcastService.createNewPodcast(podcast); }
}