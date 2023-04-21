package com.wikiFilm.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wikiFilm.models.User;
import com.wikiFilm.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getFindById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/addFilm/{id}")
    public ResponseEntity<String> addFilmWatchList(@PathVariable Long id) {
        userService.addFilmWatchList(id);
        return ResponseEntity.ok("Film added successfully");
    }

    @PostMapping("/addShow/{id}")
    public ResponseEntity<String> addShowWatchList(@PathVariable Long id) {
        userService.addShowWatchList(id);
        return ResponseEntity.ok("Show added successfully");
    }

    @DeleteMapping("/deleteFilm/{id}")
    public ResponseEntity<String> deleteFilmWatchList(@PathVariable Long id) {
        userService.deleteFilmWatchList(id);
        return ResponseEntity.ok("Film deleted successfully");
    }

    @DeleteMapping("/deleteShow/{id}")
    public ResponseEntity<String> deleteShowWatchList(@PathVariable Long id) {
        userService.deleteShowWatchList(id);
        return ResponseEntity.ok("Show deleted successfully");
    }
}
