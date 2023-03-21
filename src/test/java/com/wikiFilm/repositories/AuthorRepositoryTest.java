package com.wikiFilm.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wikiFilm.models.Author;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void findById(){
        Author author =  repository.findById(1L).orElseThrow();
       assertThat(author.getId()).isEqualTo(1L);
       assertThat(author.getName()).isEqualTo("Quentin");
    }

    @Test
    public void findByTitle(){
        Author author = repository.findByName("Quentin").orElseThrow();
        assertThat(author.getId()).isEqualTo(1L);
        assertThat(author.getName()).isEqualTo("Quentin");
    }
     
    @Test
    public void saveauthor(){
        Author author = new Author();
        author.setName("author3");
        repository.save(author);
        assertThat(author.getId()).isNotNull();
    }

    @Test
    public void deleteauthor(){
        Author author = repository.findById(1L).orElseThrow();
        repository.delete(author);
        assertThat(repository.findById(1L)).isEmpty();
    }

    @Test
    public void updateauthor(){
        Author author = repository.findById(1L).orElseThrow();
        author.setName("author1Updated");
        repository.save(author);
        assertThat(author.getName()).isEqualTo("author1Updated");
    }

}
