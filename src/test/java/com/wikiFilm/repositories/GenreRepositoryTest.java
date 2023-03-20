package com.wikiFilm.repositories;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wikiFilm.models.Genre;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GenreRepositoryTest {

    @Autowired
    GenreRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void findById(){
       Genre genre =  repository.findById(1L).orElseThrow();
       assertThat(genre.getId()).isEqualTo(1L);
       assertThat(genre.getName()).isEqualTo("Comedy");
    }

    @Test
    public void findByName(){
        Genre genre = repository.findByName("Comedy").orElseThrow();
        assertThat(genre.getId()).isEqualTo(1L);
        assertThat(genre.getName()).isEqualTo("Comedy");
    }
     
    @Test
    public void saveGenre(){
        Genre genre = new Genre();
        genre.setName("Genre3");
        repository.save(genre);
        assertThat(genre.getId()).isNotNull();
    }

    @Test
    public void deleteGenre(){
        Genre genre = repository.findById(1L).orElseThrow();
        repository.delete(genre);
        assertThat(repository.findById(1L)).isEmpty();
    }

    @Test
    public void updateGenre(){
        Genre genre = repository.findById(1L).orElseThrow();
        genre.setName("Genre1Updated");
        repository.save(genre);
        assertThat(genre.getName()).isEqualTo("Genre1Updated");
    }

}


