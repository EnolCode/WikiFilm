package com.wikiFilm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wikiFilm.models.Show;

public interface ShowRepository extends JpaRepository<Long,Show> {
    
    Show findByTitle(String title);
    List<Show> findByGenres_Name(String genre);
}
