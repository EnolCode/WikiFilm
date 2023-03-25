package com.wikiFilm.controllers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import com.wikiFilm.models.Genre;
import com.wikiFilm.services.GenreService;

@WebMvcTest(controllers = GenreController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService service;

    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = new Genre(1L,"genre1",null);
    }

    @Test
    void getAllGenres_shouldReturnAllGenres() throws Exception {
        List<Genre> genres = new ArrayList<>();
        genres.add(genre);

        when(service.findAll()).thenReturn(genres);
        MockHttpServletResponse response = mockMvc.perform(get("/api/genres")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("genre1");
        assertThat(response.getStatus()).isEqualTo(200);
    }
}


