package com.wikiFilm.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wikiFilm.models.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {


    @Autowired
    UserRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void findById(){
        User user =  repository.findById(1L).orElseThrow();
       assertThat(user.getId()).isEqualTo(1L);
       assertThat(user.getUsername()).isEqualTo("enol");
    }

    @Test
    public void findByName(){
        User user = repository.findByUsername("enol").orElseThrow();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("enol");
    }
     
    @Test
    public void saveuser(){
        User user = new User();
        user.setUsername("user3");
        repository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void deleteuser(){
        User user = repository.findById(1L).orElseThrow();
        repository.delete(user);
        assertThat(repository.findById(1L)).isEmpty();
    }

    @Test
    public void updateuser(){
        User user = repository.findById(1L).orElseThrow();
        user.setUsername("user1Updated");
        repository.save(user);
        assertThat(user.getUsername()).isEqualTo("user1Updated");
    }

}


