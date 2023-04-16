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

import com.wikiFilm.models.Show;
import com.wikiFilm.services.ShowService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/shows")
public class ShowController {
    
    private final ShowService showService;

    @GetMapping
    public List<Show> getAllShows() {
        return showService.findAll();
    }

    @GetMapping("/{id}")
    public Show getShowById(@PathVariable Long id) {
        return showService.findById(id);
    }

    @GetMapping("/title/{title}")
    public Show getShowByTitle(@PathVariable String title) {
        return showService.findByTitle(title);
    }

    @GetMapping("/genre/{genreName}")
    public List<Show> getShowsByGenres(@PathVariable String genreName) {
        return showService.findByGenre(genreName);
    }

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        Show savedShow = showService.save(show);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShow);
    }

    @PostMapping("/like/{id}")
    public Show likeShow(@PathVariable Long id) {
        return showService.likedShow(showService.findById(id));
    }

    @PostMapping("/dislike/{id}")
    public Show dislikeShow(@PathVariable Long id) {
        return showService.dislikedShow(showService.findById(id));
    }

    @PutMapping("/update/{id}")
    public Show updateShow(@PathVariable Long id, @RequestBody Show showDetails) {
        return showService.updateShow(id, showDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShow(@PathVariable Long id) {
      showService.deleteById(id);
        return ResponseEntity.ok("Show deleted successfully");  
    }
}
