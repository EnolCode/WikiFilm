package com.wikiFilm.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wikiFilm.models.Film;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FilmRepositoryTest {

    @Autowired
    FilmRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void findById(){
       Film film =  repository.findById(1L).orElseThrow();
       assertThat(film.getId()).isEqualTo(1L);
       assertThat(film.getTitle()).isEqualTo("Film1");
    }

    @Test
    public void findByTitle(){
        Film film = repository.findByTitle("Film1").orElseThrow();
        assertThat(film.getId()).isEqualTo(1L);
        assertThat(film.getTitle()).isEqualTo("Film1");
    }
     
    @Test
    public void saveFilm(){
        Film film = new Film();
        film.setTitle("Film3");
        film.setRating(8);
        repository.save(film);
        assertThat(film.getId()).isNotNull();
    }

    @Test
    public void deleteFilm(){
        Film film = repository.findById(1L).orElseThrow();
        repository.delete(film);
        assertThat(repository.findById(1L)).isEmpty();
    }

    @Test
    public void updateFilm(){
        Film film = repository.findById(1L).orElseThrow();
        film.setTitle("Film1Updated");
        repository.save(film);
        assertThat(film.getTitle()).isEqualTo("Film1Updated");
    }

}
