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

import com.wikiFilm.exception.UserNotFoundException;
import com.wikiFilm.models.User;
// import com.wikiuser.models.Genre;
import com.wikiFilm.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    private User user;
    private User user2;

    @BeforeEach
    public void init() {
        user = new User(1L, "user1", "pass1", null,null,null,null);
        user2 = new User(2L, "user2", "pass2", null,null,null, null);
    }

    @Test
    public void findById_ShouldReturnuser_WhenuserExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        User currentuser = service.findById(1L);

        assertThat(currentuser.getId()).isEqualTo(1L);
        assertThat(currentuser.getUsername()).isEqualTo("user1");
       }

       @Test
       public void findByUsername_ShouldReturnuser_WhenuserExists() {
           when(repository.findByUsername("user1")).thenReturn(Optional.of(user));
           User currentuser = service.findByUsername("user1");
   
           assertThat(currentuser.getId()).isEqualTo(1L);
           assertThat(currentuser.getUsername()).isEqualTo("user1");
          }

    @Test
       public void findById_ShouldThrowException_WhenuserDoesNotExist() {
           when(repository.findById(1L)).thenReturn(Optional.empty());
    
           assertThrows(UserNotFoundException.class, () -> service.findById(1L));
       }

    @Test 
       public void findAll_shouldReturnAllusers() {
              when(repository.findAll()).thenReturn(List.of(user, user2));
              List<User> users = service.findAll();
     
              assertThat(users).hasSize(2);
              assertThat(users.get(0).getId()).isEqualTo(1L);
              assertThat(users.get(1).getId()).isEqualTo(2L);
              assertThat(users.get(0).getUsername()).isEqualTo("user1");
              assertThat(users.get(1).getUsername()).isEqualTo("user2");
       }

    @Test
       public void save_shouldSaveuser() {
           when(repository.save(user)).thenReturn(user);
           User saveduser = service.save(user);
    
           assertThat(saveduser.getId()).isEqualTo(1L);
           assertThat(saveduser.getUsername()).isEqualTo("user1");
       }

    @Test
        public void deleteById_shouldDeleteuser() {
              when(repository.findById(1L)).thenReturn(Optional.of(user));
              service.deleteById(1L);
        }

    @Test 
        public void deleteById_shouldThrowException_WhenuserDoesNotExist() {
              when(repository.findById(1L)).thenReturn(Optional.empty());
              assertThrows(UserNotFoundException.class, () -> service.deleteById(1L));
        }

    @Test
        public void updateuser_shouldUpdateuser() {
                when(repository.findById(1L)).thenReturn(Optional.of(user));
                when(repository.save(user)).thenReturn(user);
                User updateduser = service.updateUser(1L, user);
        
                assertThat(updateduser.getId()).isEqualTo(1L);
                assertThat(updateduser.getUsername()).isEqualTo("user1");
            }

    @Test       
        public void updateuser_shouldThrowException_WhenuserDoesNotExist() {
                when(repository.findById(1L)).thenReturn(Optional.empty());
        
                assertThrows(UserNotFoundException.class, () -> service.updateUser(1L, user));
            }
}
