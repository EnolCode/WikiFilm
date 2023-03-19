package com.wikiFilm.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.wikiFilm.exception.FilmNotFoundException;
import com.wikiFilm.models.Film;
import com.wikiFilm.repositories.FilmRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with id: " + id));
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film filmDetails) {
        Film film = findById(id);
        film.setName(filmDetails.getName());
        film.setReleaseDate(filmDetails.getReleaseDate());
        film.setRating(filmDetails.getRating());
        film.setDescription(filmDetails.getDescription());
        return save(film);
    }

    public void deleteById(Long id) {
        Film film = findById(id);
        filmRepository.deleteById(film.getId());
    }
}