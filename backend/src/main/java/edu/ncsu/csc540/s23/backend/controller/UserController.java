package edu.ncsu.csc540.s23.backend.controller;


import edu.ncsu.csc540.s23.backend.dto.UserDTO;
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
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create-user")
    public String createUser(@RequestBody UserDTO user) {
        return userService.createNewUser(user);
    }
}
