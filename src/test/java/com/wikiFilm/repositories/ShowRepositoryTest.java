package com.wikiFilm.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wikiFilm.models.Show;

import jakarta.persistence.EntityManager;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ShowRepositoryTest {

    @Autowired
    ShowRepository repository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void setup(){
        Show show1 = new Show(1L,"show1","Description","image",2000,80,null,null,null);
        repository.save(show1);
    }
    
    @Test
    public void findById_shouldReturnShowWithMatchingId(){
        Show foundShow = repository.findById(1L).orElseThrow();
        assertThat(foundShow.getId()).isEqualTo(1L);
        assertThat(foundShow.getTitle()).isEqualTo("show1");
    }

    @Test
    public void findByName_shouldReturnShowWithMatchingName(){
        Show foundShow = repository.findByTitle("show1").orElseThrow(null);
        assertThat(foundShow.getId()).isEqualTo(1L);
        assertThat(foundShow.getDescription()).isEqualTo("Description");
    }

}
