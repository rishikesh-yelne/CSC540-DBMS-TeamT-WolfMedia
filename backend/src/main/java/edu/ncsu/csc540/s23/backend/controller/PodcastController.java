package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Podcast;
import edu.ncsu.csc540.s23.backend.model.Sponsor;
import edu.ncsu.csc540.s23.backend.model.dto.PodcastRatingDTO;
import edu.ncsu.csc540.s23.backend.model.relationships.Rates;
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

    @PatchMapping("/{podcast_id}/{podcast_host_id}")
    public boolean assignPodcastHost(@PathVariable Long podcast_id, @PathVariable Long podcast_host_id){return podcastService.assignPodcastHost(podcast_id, podcast_host_id);}

    @PostMapping("/rate/{podcastId}")
    public boolean ratePodcast(@PathVariable Long podcastId, @RequestParam Long userId, @RequestParam Double rating) { return this.podcastService.ratePodcast(podcastId, userId, rating); }

    @GetMapping("/rating/{podcastId}")
    public Double getPodcastRating(@PathVariable Long podcastId) { return this.podcastService.getPodcastRating(podcastId); }

    @GetMapping("/ratings/{podcastId}")
    public List<Rates> getPodcastRatings(@PathVariable Long podcastId) { return this.podcastService.getPodcastRatings(podcastId); }

    @GetMapping("/ratings")
    public List<PodcastRatingDTO> getPodcastRatings() { return this.podcastService.getPodcastRatings(); }

    //increment subscriber count by 1
    @PostMapping("/subscribe")
    public boolean incrementSubscriberCount(@RequestParam(name="user_id") Long userId, @RequestParam(name="podcast_id") Long podcastId) { return podcastService.incrementSubscriberCount(userId, podcastId); }

    //update subscriber count by X
//    @PostMapping("/update-subscribe")
//    public boolean updateSubscribeCount(@RequestParam(name="podcast_id") Long podcastId, @RequestParam(name="count") Long count) { return podcastService.updateSubscriberCount(podcastId, count); }
}
