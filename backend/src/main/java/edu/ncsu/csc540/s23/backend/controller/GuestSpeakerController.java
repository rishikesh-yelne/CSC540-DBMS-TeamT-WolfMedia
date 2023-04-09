package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Genre;
import edu.ncsu.csc540.s23.backend.model.GuestSpeaker;
import edu.ncsu.csc540.s23.backend.model.Sponsor;
import edu.ncsu.csc540.s23.backend.service.GenreService;
import edu.ncsu.csc540.s23.backend.service.GuestSpeakerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest-speaker")
public class GuestSpeakerController {

    private GuestSpeakerService guestSpeakerService;

    public GuestSpeakerController(GuestSpeakerService guestSpeakerService) { this.guestSpeakerService = guestSpeakerService;}

    @GetMapping("/all")
    public List<GuestSpeaker> getAllGuestSpeakers() { return guestSpeakerService.getAllGuestSpeakers(); }

    @GetMapping("/{id}")
    public GuestSpeaker getGuestSpeaker(@PathVariable Long id) { return guestSpeakerService.getGuestSpeaker(id); }

    @PostMapping("/add")
    public Long addGuestSpeaker(@RequestBody GuestSpeaker guestSpeaker) { return guestSpeakerService.createNewGuestSpeaker(guestSpeaker); }
}
