package com.wikiFilm.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.hibernate.FetchNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wikiFilm.models.Film;
import com.wikiFilm.repositories.FilmRepository;

@ExtendWith(MockitoExtension.class) 
public class FilmServiceTest {

    @InjectMocks
    FilmService service;

    @Mock
    FilmRepository repository;

    private Film film;

    @BeforeEach
    public void init() {
        film = new Film(1L, "name", LocalDate.of(2023, 1, 1), 5, "ya");
    }

    @Test
    public void findById_ShouldReturnFilm_WhenFilmExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(film));
        Film currentFilm = service.findById(1L);

        assertThat(currentFilm.getId()).isEqualTo(1L);
        assertThat(currentFilm.getName()).isEqualTo("name");
       }

       @Test
       public void findById_ShouldThrowException_WhenFilmDoesNotExist() {
           when(repository.findById(1L)).thenReturn(Optional.empty());
    
           assertThrows(FetchNotFoundException.class, () -> service.findById(1L));
       }

}
