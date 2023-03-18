package com.wikiFilm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wikiFilm.models.Film;

public interface FilmRepository extends JpaRepository<Film,Long>{
    
}
