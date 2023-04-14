package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.Artist;
import edu.ncsu.csc540.s23.backend.model.PodcastHost;
import edu.ncsu.csc540.s23.backend.model.User;
import edu.ncsu.csc540.s23.backend.model.dto.ArtistMonthlyPlayCount;
import edu.ncsu.csc540.s23.backend.model.dto.ArtistPlayCount;
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

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) { return userService.getUser(id); }

    @GetMapping("/{id}/artist")
    public Artist getArtist(@PathVariable Long id) { return userService.getArtist(id); }

    @GetMapping("/{id}/podcast-host")
    public PodcastHost getPodcastHost(@PathVariable Long id) { return userService.getPodcastHost(id); }

    @PutMapping("update-artist")
    public boolean updateArtist(@RequestBody Artist artist){return userService.updateArtist(artist);}

    @PutMapping("update-podcast-host")
    public boolean updatePodcastHost(@RequestBody PodcastHost podcastHost){return userService.updatePodcastHost(podcastHost);}

    //delete artist
    @DeleteMapping("/{id}/artist")
    public boolean deleteArtist(@PathVariable Long id) { return userService.deleteArtist(id); }

    //delete podcast host
    @DeleteMapping("/{id}/podcast-host")
    public boolean deletePodcastHost(@PathVariable Long id) { return userService.deletePodcastHost(id); }

    @PatchMapping("/{genreId}/{artistId}")
    public boolean assignGenreToArtist(@PathVariable Long genreId, @PathVariable Long artistId){return userService.assignGenreToArtist(genreId, artistId);}

    @GetMapping("/song-play-count/{month}/{year}")
    public List<ArtistMonthlyPlayCount> getPlayCount(
            @RequestParam Long artistId,
            @PathVariable int month,
            @PathVariable int year) {
        return this.userService.getPlayCount(artistId, month, year);
    }

    @GetMapping("/song-play-count")
    public List<ArtistPlayCount> getPlayCount(@RequestParam Long artistId) {
        return this.userService.getPlayCount(artistId);
    }
}
