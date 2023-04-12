package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Podcast;
import edu.ncsu.csc540.s23.backend.model.Sponsor;
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

    @GetMapping("/{id}")
    public Podcast getPodcast(@PathVariable Long id) { return podcastService.getPodcast(id); }
    @PostMapping("/add")
    public Long addPodcast(@RequestBody Podcast podcast) { return podcastService.createNewPodcast(podcast); }

    @PutMapping()
    public boolean updatePodcast(@RequestBody Podcast podcast) { return podcastService.updatePodcast(podcast); }

    @GetMapping("/podcast-host/{podcastHostId}")
    public List<Podcast> getPodcastsByPodcastHost(@PathVariable Long podcastHostId) { return podcastService.getPodcastsByPodcastHost(podcastHostId); }

    @PostMapping("/add-sponsor")
    public boolean addSponsorToPodcast(@RequestParam Long podcastId, @RequestParam Long sponsorId) { return podcastService.addSponsorToPodcast(podcastId, sponsorId); }

    @GetMapping("/sponsors/{podcastId}")
    public List<Sponsor> getSponsorsOfPodcast(@PathVariable Long podcastId) { return podcastService.getSponsorsOfPodcast(podcastId); }
}