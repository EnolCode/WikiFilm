package com.wikiFilm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wikiFilm.models.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{

    Optional<Film> findByTitle(String string);

    List<Film> findByGenres_Name(String genre);
    
}
