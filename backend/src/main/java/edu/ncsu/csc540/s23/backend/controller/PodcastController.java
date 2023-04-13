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

    @GetMapping("/{id}")
    public Podcast getPodcast(@PathVariable Long id) { return podcastService.getPodcast(id); }
    @PostMapping("/add")
    public Long addPodcast(@RequestBody Podcast podcast) { return podcastService.createNewPodcast(podcast); }

    @PutMapping()
    public boolean updatePodcast(@RequestBody Podcast podcast) { return podcastService.updatePodcast(podcast); }

    @PatchMapping("/{podcast_id}/{podcast_host_id}")
    public boolean assignPodcastHost(@PathVariable Long podcast_id, @PathVariable Long podcast_host_id){return podcastService.assignPodcastHost(podcast_id, podcast_host_id);}

    //increment subscriber count by 1
    @PostMapping("/subscribe")
    public boolean incrementSubscriberCount(@RequestParam(name="user_id") Long userId, @RequestParam(name="podcast_id") Long podcastId) { return podcastService.incrementSubscriberCount(userId, podcastId); }

    //update subscriber count by X
    @PostMapping("/update-subscribe")
    public boolean updateSubscribeCount(@RequestParam(name="podcast_id") Long podcastId, @RequestParam(name="count") Long count) { return podcastService.updateSubscriberCount(podcastId, count); }
}
