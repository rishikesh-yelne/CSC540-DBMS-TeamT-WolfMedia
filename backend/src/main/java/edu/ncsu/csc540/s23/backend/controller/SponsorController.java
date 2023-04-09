package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.GuestSpeaker;
import edu.ncsu.csc540.s23.backend.model.Song;
import edu.ncsu.csc540.s23.backend.model.Sponsor;
import edu.ncsu.csc540.s23.backend.service.GuestSpeakerService;
import edu.ncsu.csc540.s23.backend.service.SponsorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sponsor")
public class SponsorController {

    private SponsorService sponsorService;

    public SponsorController(SponsorService sponsorService) { this.sponsorService = sponsorService;}

    @GetMapping("/all")
    public List<Sponsor> getAllSponsors() { return sponsorService.getAllSponsors(); }

    @GetMapping("/{id}")
    public Sponsor getSponsor(@PathVariable Long id) { return sponsorService.getSponsor(id); }

    @PostMapping("/add")
    public Long addSponsor(@RequestBody Sponsor sponsor) { return sponsorService.createNewSponsor(sponsor); }
}
