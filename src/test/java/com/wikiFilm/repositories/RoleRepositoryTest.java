
package com.wikiFilm.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wikiFilm.models.Role;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {


    @Autowired
    RoleRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void findById(){
        Role role =  repository.findById(1L).orElseThrow();
       assertThat(role.getId()).isEqualTo(1L);
       assertThat(role.getRoleName()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    public void findByName(){
        Role role = repository.findById(1L).orElseThrow();
        assertThat(role.getId()).isEqualTo(1L);
        assertThat(role.getRoleName()).isEqualTo("ROLE_ADMIN");
    }
     
    @Test
    public void saverole(){
        Role role = new Role();
        role.setRoleName("role3");
        repository.save(role);
        assertThat(role.getId()).isNotNull();
    }

    @Test
    public void deleterole(){
        Role role = repository.findById(1L).orElseThrow();
        repository.delete(role);
        assertThat(repository.findById(1L)).isEmpty();
    }

    @Test
    public void updaterole(){
        Role role = repository.findById(1L).orElseThrow();
        role.setRoleName("role1Updated");
        repository.save(role);
        assertThat(role.getRoleName()).isEqualTo("role1Updated");
    }

}


