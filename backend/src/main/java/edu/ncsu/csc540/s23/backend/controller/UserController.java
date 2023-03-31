package edu.ncsu.csc540.s23.backend.controller;

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

    @PostMapping("/add-user")
    public boolean addUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    //TODO: to be implemented
//    @PostMapping("/add-artist")
//    public boolean addArtist(@RequestBody User artist) { return userService.createNewUser(artist); }
//
//
//    @PostMapping("/add-podcast-host")
//    public boolean addPodcastHost(@RequestBody User podcastHost) { return userService.createNewUser(podcastHost); }
}
