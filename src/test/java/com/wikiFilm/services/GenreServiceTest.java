package com.wikiFilm.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wikiFilm.exception.GenreNotFoundException;
import com.wikiFilm.models.Genre;
import com.wikiFilm.repositories.GenreRepository;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @InjectMocks
    GenreService service;

    @Mock
    GenreRepository repository;
    
    private Genre genre1;
    private Genre genre2;

    @BeforeEach
    public void init() {
        genre1 = new Genre(1L, "name", null);
        genre2 = new Genre(2L, "name2", null);
    }

    @Test
    void findById_ShouldReturnGenre_WhenGenreExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(genre1));
        Genre currentGenre = service.findById(1L);

        assertThat(currentGenre.getId()).isEqualTo(1L);
        assertThat(currentGenre.getName()).isEqualTo("name");

    }

    @Test
    void findByName_ShouldReturnGenre_WhenNameExists() {
        when(repository.findByName("name")).thenReturn(Optional.of(genre1));
        Genre currentGenre = service.findByTitle("name");

        assertThat(currentGenre.getId()).isEqualTo(1L);
        assertThat(currentGenre.getName()).isEqualTo("name");
    }

    @Test
    public void findById_ShouldThrowException_WhenGenreDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
 
        assertThrows(GenreNotFoundException.class, () -> service.findById(1L));
    }

    @Test 
    public void findAll_shouldReturnAllGenres() {
           when(repository.findAll()).thenReturn(List.of(genre1, genre2));
           List<Genre> films = service.findAll();
  
           assertThat(films).hasSize(2);
           assertThat(films.get(0).getId()).isEqualTo(1L);
           assertThat(films.get(1).getId()).isEqualTo(2L);
           assertThat(films.get(0).getName()).isEqualTo("name");
           assertThat(films.get(1).getName()).isEqualTo("name2");
    }
}
