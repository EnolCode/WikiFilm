package com.wikiFilm.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wikiFilm.models.Genre;
import com.wikiFilm.services.GenreService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

    private GenreService genreService;

    @GetMapping()
    public List<Genre> getAllGenres() {
        return genreService.findAll();
    }

}
