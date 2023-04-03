package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Artist;
import edu.ncsu.csc540.s23.backend.model.PodcastHost;
import edu.ncsu.csc540.s23.backend.model.User;
import edu.ncsu.csc540.s23.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/all-artists")
    public List<Artist> getAllArtists() { return userService.getAllArtists(); }

    @GetMapping("/all-podcast-hosts")
    public List<PodcastHost> getAllPodcastHosts() { return userService.getAllPodcastHosts(); }

    @PostMapping("/add-user")
    public Long addUser(@RequestBody User user) { return userService.createNewUser(user); }

    @PostMapping("/add-artist")
    public boolean addArtist(@RequestBody Artist artist) { return userService.createNewArtist(artist); }

    @PostMapping("/add-podcast-host")
    public boolean addPodcastHost(@RequestBody PodcastHost podcastHost) { return userService.createNewPodcastHost(podcastHost); }


}
