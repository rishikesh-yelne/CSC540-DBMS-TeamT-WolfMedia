package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.PodcastEpisode;
import edu.ncsu.csc540.s23.backend.service.PodcastEpisodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/podcast-episode")
public class PodcastEpisodeController {
    private PodcastEpisodeService podcastEpisodeService;

    public PodcastEpisodeController(PodcastEpisodeService podcastEpisodeService) { this.podcastEpisodeService = podcastEpisodeService; }

    @GetMapping("/all")
    public List<PodcastEpisode> getAllPodcastEpisodes() { return podcastEpisodeService.getAllPodcastEpisodes(); }

    @PostMapping("/add")
    public Long addPodcastEpisode(@RequestBody PodcastEpisode podcastEpisode) { return podcastEpisodeService.createNewPodcastEpisode(podcastEpisode); }

}