package com.wikiFilm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wikiFilm.models.Film;
import com.wikiFilm.services.FilmService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/films")
@AllArgsConstructor
public class FilmController {

    // @Autowired
    private final FilmService filmService;

    @Transactional
    @PostMapping(value = "add", consumes = "application/*")
    public ResponseEntity<Film> addFilm(@RequestBody Film film) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmService.save(film));
    }

    @Transactional
    @GetMapping
    public ResponseEntity<?> getAllFilms() {
        return ResponseEntity.ok(filmService.findAll());
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Long id) {
        return filmService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
