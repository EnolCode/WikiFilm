package com.wikiFilm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wikiFilm.exception.GenreNotFoundException;
import com.wikiFilm.models.Genre;
import com.wikiFilm.repositories.GenreRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GenreService {
    
    private final GenreRepository repository;

    @Transactional
    public Genre findByTitle(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found with id: " + name));
    }

    @Transactional
    public Genre findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found with id: " + id));
    }

    @Transactional
    public List<Genre> findAll(){
        return repository.findAll();
    }

}
