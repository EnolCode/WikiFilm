package com.wikiFilm.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wikiFilm.models.Film;
import com.wikiFilm.services.FilmService;

@WebMvcTest(controllers = FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService service;

    private Film film;

    @BeforeEach
    void setUp() {
        film = new Film(1L, "film1", null, 5, "description", null,null);
    }

    @Test
    void getAllFilms_shouldReturnAllFilms() throws Exception {
        List<Film> films = new ArrayList<>();
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

    @Test
    void getFilmById_shouldReturnFilm() throws Exception {
        when(service.findById(1L)).thenReturn(film);
        MockHttpServletResponse response = mockMvc.perform(get("/api/films/1")
                                            .contentType("application/json"))
                                            .andExpect(status().isOk())
                                            .andReturn()
                                            .getResponse();

        assertThat(response.getContentAsString()).contains("film1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void getFilmByTitle_shouldReturnFilm() throws Exception {
        when(service.findByTitle("film1")).thenReturn(film);
        MockHttpServletResponse response = mockMvc.perform(get("/api/films/title/film1")
                                            .contentType("application/json"))
                                            .andExpect(status().isOk())
                                            .andReturn()
                                            .getResponse();

        assertThat(response.getContentAsString()).contains("film1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testCreateFilm() throws Exception {
        when(service.save(any(Film.class))).thenReturn(film);

        MockHttpServletResponse response = mockMvc.perform(post("/api/films/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(film)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("film1");
        assertThat(response.getContentAsString()).contains("description");
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void testUpdateFilm() throws Exception {
        Film updatedFilm = new Film(1L, "film1", null, 5, "film update", null, null);
        when(service.updateFilm(any(Long.class), any(Film.class))).thenReturn(updatedFilm);

        MockHttpServletResponse response = mockMvc.perform(put("/api/films/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedFilm)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("film1");
        assertThat(response.getContentAsString()).contains("film update");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testDeleteFilm() throws Exception {
        Long filmId = 1L;

        doNothing().when(service).deleteById(filmId);

        MockHttpServletResponse response = mockMvc.perform(delete("/api/films/" + filmId))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).isEqualTo("Film deleted successfully");
        assertThat(service.findById(1L)).isEqualTo(null);
    }

}
