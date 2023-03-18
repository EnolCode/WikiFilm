package com.wikiFilm.controllers;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
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

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/films")
@AllArgsConstructor
public class FilmController {
    
    private FilmService filmService;

    @PostMapping(value="add", consumes = "application/json")
    public ResponseEntity<Film> addFilm(@RequestBody Film film) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmService.save(film));
    }

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        return ResponseEntity.ok(filmService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        try {
            Film film = filmService.findById(id);
            return ResponseEntity.ok(film);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@RequestBody Film filmDetails, @PathVariable Long id) {
        try {
            Film updatedFilm = filmService.updateFilm(id, filmDetails);
            return ResponseEntity.ok(updatedFilm);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        try {
            filmService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}