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
import com.wikiFilm.models.Film;
import com.wikiFilm.services.FilmService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable Long id) {
        return filmService.findById(id);
    }

    @GetMapping("/title/{title}")
    public Film getFilmByTitle(@PathVariable String title) {
        return filmService.findByTitle(title);
    }

    @GetMapping("/genre/{genreName}")
    public List<Film> getMoviesByGenres(@PathVariable String genreName) {
        return filmService.findByGenre(genreName);
    }

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        Film savedFilm = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm);
    }


    @PutMapping("/update/{id}")
    public Film updateFilm(@PathVariable Long id, @RequestBody Film filmDetails) {
        return filmService.updateFilm(id, filmDetails);
    }

    @PostMapping("/like/{id}")
    public Film likeFilm(@PathVariable Long id) {
        return filmService.likedFilm(filmService.findById(id));
    }

    @PostMapping("/dislike/{id}")
    public Film dislikeFilm(@PathVariable Long id) {
        return filmService.dislikedFilm(filmService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable Long id) {
        filmService.deleteById(id);
        return ResponseEntity.ok("Film deleted successfully");
    }
}