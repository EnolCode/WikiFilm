package com.wikiFilm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.wikiFilm.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{
    Optional<Genre> findByName(String name);
}
