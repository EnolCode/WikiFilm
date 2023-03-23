package com.wikiFilm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wikiFilm.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
