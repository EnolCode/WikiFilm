package com.wikiFilm.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.wikiFilm.models.Film;
import com.wikiFilm.services.FilmService;

@WebMvcTest(controllers = FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService service;

    @Test
    void getAllFilms_shouldReturnAllFilms() throws Exception {
        List<Film> films = new ArrayList<>();
        Film film = new Film();
        film.setId(1L);
        film.setTitle("film1");
        films.add(film);
        
        when(service.findAll()).thenReturn(films);
        MockHttpServletResponse response = mockMvc.perform(get("/api/films")
                                            .contentType("application/json"))
                                            .andExpect(status().isOk())
                                            .andReturn()
                                            .getResponse();

        assertThat(response.getContentAsString()).contains("film1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

}
