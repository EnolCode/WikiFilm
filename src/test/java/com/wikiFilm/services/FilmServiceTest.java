package com.wikiFilm.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wikiFilm.exception.FilmNotFoundException;
import com.wikiFilm.models.Film;
// import com.wikiFilm.models.Genre;
import com.wikiFilm.repositories.FilmRepository;
import com.wikiFilm.repositories.GenreRepository;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {

    @InjectMocks
    FilmService service;

    @Mock
    FilmRepository repository;

    @Mock
    GenreRepository genreRepository;

    private Film film;
    private Film film2;

    @BeforeEach
    public void init() {
        film = new Film(1L, "name", 2023, 5, "ya",null,null,null);
        film2 = new Film(2L, "name2", 2023, 5, "ya", null, null,null);
    }

    @Test
    public void findById_ShouldReturnFilm_WhenFilmExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(film));
        Film currentFilm = service.findById(1L);

        assertThat(currentFilm.getId()).isEqualTo(1L);
        assertThat(currentFilm.getTitle()).isEqualTo("name");
       }

    @Test
       public void findById_ShouldThrowException_WhenFilmDoesNotExist() {
           when(repository.findById(1L)).thenReturn(Optional.empty());
    
           assertThrows(FilmNotFoundException.class, () -> service.findById(1L));
       }

    @Test 
       public void findAll_shouldReturnAllFilms() {
              when(repository.findAll()).thenReturn(List.of(film, film2));
              List<Film> films = service.findAll();
     
              assertThat(films).hasSize(2);
              assertThat(films.get(0).getId()).isEqualTo(1L);
              assertThat(films.get(1).getId()).isEqualTo(2L);
              assertThat(films.get(0).getTitle()).isEqualTo("name");
              assertThat(films.get(1).getTitle()).isEqualTo("name2");
       }

    @Test
       public void save_shouldSaveFilm() {
           when(repository.save(film)).thenReturn(film);
           Film savedFilm = service.save(film);
    
           assertThat(savedFilm.getId()).isEqualTo(1L);
           assertThat(savedFilm.getTitle()).isEqualTo("name");
       }

    @Test
        public void deleteById_shouldDeleteFilm() {
              when(repository.findById(1L)).thenReturn(Optional.of(film));
              service.deleteById(1L);
        }

    @Test 
        public void deleteById_shouldThrowException_WhenFilmDoesNotExist() {
              when(repository.findById(1L)).thenReturn(Optional.empty());
              assertThrows(FilmNotFoundException.class, () -> service.deleteById(1L));
        }

    @Test
        public void updateFilm_shouldUpdateFilm() {
                when(repository.findById(1L)).thenReturn(Optional.of(film));
                when(repository.save(film)).thenReturn(film);
                Film updatedFilm = service.updateFilm(1L, film);
        
                assertThat(updatedFilm.getId()).isEqualTo(1L);
                assertThat(updatedFilm.getTitle()).isEqualTo("name");
            }

    @Test       
        public void updateFilm_shouldThrowException_WhenFilmDoesNotExist() {
                when(repository.findById(1L)).thenReturn(Optional.empty());
        
                assertThrows(FilmNotFoundException.class, () -> service.updateFilm(1L, film));
            }


//             @Test
// public void testFindByGenre() {
    
//     Genre genre = new Genre(1L,"comedy", null);
//     genreRepository.save(genre);

//     film.getGenres().add(genre);
//     repository.save(film);

//     film2.getGenres().add(genre);
//     repository.save(film2);

//     List<Film> result = service.findByGenre("comedy");
    
//     assertEquals(2, result.size());
//     assertTrue(result.contains(film));
//     assertTrue(result.contains(film2));
// }
}
