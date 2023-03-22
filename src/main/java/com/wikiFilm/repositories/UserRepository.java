package com.wikiFilm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wikiFilm.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    
}
