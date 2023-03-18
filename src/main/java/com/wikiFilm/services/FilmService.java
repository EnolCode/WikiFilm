package com.wikiFilm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wikiFilm.models.Film;
import com.wikiFilm.repositories.FilmRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilmService implements BaseService<Film>{

    private final FilmRepository filmRepository;

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Film> findAll(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Film> findById(Long id) {
        return filmRepository.findById(id);
    }

    @Override
    @Transactional
    public Film save(Film film) {
        return filmRepository.save(film);

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }
    
}
