package com.wikiFilm.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wikiFilm.exception.ShowNotFoundException;
import com.wikiFilm.models.Show;
import com.wikiFilm.repositories.ShowRepository;

@ExtendWith(MockitoExtension.class)
public class ShowServiceTest {
    
    @InjectMocks
    ShowService service;

    @Mock
    ShowRepository repository;

    private Show show;
    private Show show2;
    
    @BeforeEach
    public void init() {
         show = new Show(1L,"show1","Description1","image1",2000,80,null,null,null);
         show2 = new Show(2L,"show2","Description2","image2",2002,80,null,null,null);
    }


    @Test
    public void findById_shouldReturnShow_WhenShowExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(show));
        Show currentShow = service.findById(1L);

        assertThat(currentShow.getId()).isEqualTo(1L);
        assertThat(currentShow.getTitle()).isEqualTo("show1");
    }

    @Test
    public void findByTitle_shouldReturnShow_WhenShowExists() {
        when(repository.findByTitle("show1")).thenReturn(Optional.of(show));
        Show currentShow = service.findByTitle("show1");

        assertThat(currentShow.getId()).isEqualTo(1L);
        assertThat(currentShow.getTitle()).isEqualTo("show1");
    }

    @Test
    public void findByTitle_shouldReturnNull_WhenShowDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ShowNotFoundException.class, () -> service.findById(1L));

    }
    
}
