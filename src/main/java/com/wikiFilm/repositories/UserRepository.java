package com.wikiFilm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wikiFilm.models.Film;
import com.wikiFilm.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    List<User> findAllByFilmsContaining(Film film);
}
