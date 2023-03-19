package com.wikiFilm.services;

import java.util.List;

import com.wikiFilm.exception.GenreNotFoundException;
import com.wikiFilm.models.Genre;
import com.wikiFilm.repositories.GenreRepository;

public class GenreService {
    
    private GenreRepository repository;

    public Genre findByTitle(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found with id: " + name));
    }

    public Genre findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found with id: " + id));
    }

    public List<Genre> findAll(){
        return repository.findAll();
    }

}
