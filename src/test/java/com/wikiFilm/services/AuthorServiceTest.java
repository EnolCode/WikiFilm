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

import com.wikiFilm.exception.AuthorNotFoundException;
import com.wikiFilm.models.Author;
// import com.wikiauthor.models.Genre;
import com.wikiFilm.repositories.AuthorRepository;
import com.wikiFilm.repositories.GenreRepository;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    AuthorService service;

    @Mock
    AuthorRepository repository;

    @Mock
    GenreRepository genreRepository;

    private Author author;
    private Author author2;

    @BeforeEach
    public void init() {
        author = new Author(1L, "Author1", "surname", "France", 22 ,  (float) 8 ,null);
        author2 = new Author(2L, "Author2", "surname", "France", 22 ,  (float) 8 ,null);
    }

    @Test
    public void findById_ShouldReturnauthor_WhenauthorExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(author));
        Author currentauthor = service.findById(1L);

        assertThat(currentauthor.getId()).isEqualTo(1L);
        assertThat(currentauthor.getName()).isEqualTo("Author1");
       }

    @Test
       public void findById_ShouldThrowException_WhenauthorDoesNotExist() {
           when(repository.findById(1L)).thenReturn(Optional.empty());
    
           assertThrows(AuthorNotFoundException.class, () -> service.findById(1L));
       }

    @Test 
       public void findAll_shouldReturnAllauthors() {
              when(repository.findAll()).thenReturn(List.of(author, author2));
              List<Author> authors = service.findAll();
     
              assertThat(authors).hasSize(2);
              assertThat(authors.get(0).getId()).isEqualTo(1L);
              assertThat(authors.get(1).getId()).isEqualTo(2L);
              assertThat(authors.get(0).getName()).isEqualTo("Author1");
              assertThat(authors.get(1).getName()).isEqualTo("Author2");
       }

    @Test
       public void save_shouldSaveauthor() {
           when(repository.save(author)).thenReturn(author);
           Author savedauthor = service.save(author);
    
           assertThat(savedauthor.getId()).isEqualTo(1L);
           assertThat(savedauthor.getName()).isEqualTo("Author1");
       }

    @Test
        public void deleteById_shouldDeleteauthor() {
              when(repository.findById(1L)).thenReturn(Optional.of(author));
              service.deleteById(1L);
        }

    @Test 
        public void deleteById_shouldThrowException_WhenauthorDoesNotExist() {
              when(repository.findById(1L)).thenReturn(Optional.empty());
              assertThrows(AuthorNotFoundException.class, () -> service.deleteById(1L));
        }

    @Test
        public void updateauthor_shouldUpdateauthor() {
                when(repository.findById(1L)).thenReturn(Optional.of(author));
                when(repository.save(author)).thenReturn(author);
                Author updatedauthor = service.updateAuthor(1L, author);
        
                assertThat(updatedauthor.getId()).isEqualTo(1L);
                assertThat(updatedauthor.getName()).isEqualTo("Author1");
            }

    @Test       
        public void updateauthor_shouldThrowException_WhenauthorDoesNotExist() {
                when(repository.findById(1L)).thenReturn(Optional.empty());
        
                assertThrows(AuthorNotFoundException.class, () -> service.updateAuthor(1L, author));
            }
}
