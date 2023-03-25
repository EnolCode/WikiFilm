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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wikiFilm.services.AuthorService;
import com.wikiFilm.models.Author;


@WebMvcTest(controllers = AuthorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService service;

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author(1L, "Author1", "surname", "France", 22 ,  (float) 8 ,null);
    }

    @Test
    void getAllAuthors_shouldReturnAllAuthors() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(author);

        when(service.findAll()).thenReturn(authors);
        MockHttpServletResponse response = mockMvc.perform(get("/api/authors")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("Author1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void getAuthorById_shouldReturnAuthor() throws Exception {
        when(service.findById(1L)).thenReturn(author);
        MockHttpServletResponse response = mockMvc.perform(get("/api/authors/1")
                                            .contentType("application/json"))
                                            .andExpect(status().isOk())
                                            .andReturn()
                                            .getResponse();

        assertThat(response.getContentAsString()).contains("Author1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void getAuthorByTitle_shouldReturnAuthor() throws Exception {
        when(service.findByName("Author1")).thenReturn(author);
        MockHttpServletResponse response = mockMvc.perform(get("/api/authors/name/Author1")
                                            .contentType("application/json"))
                                            .andExpect(status().isOk())
                                            .andReturn()
                                            .getResponse();

        assertThat(response.getContentAsString()).contains("Author1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testCreateAuthor() throws Exception {
        when(service.save(any(Author.class))).thenReturn(author);

        MockHttpServletResponse response = mockMvc.perform(post("/api/authors/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(author)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("Author1");
        assertThat(response.getContentAsString()).contains("surname");
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        Author updatedAuthor = new Author(1L, "Author1", "Author update", "Madagascar", 18, null, null);
        when(service.updateAuthor(any(Long.class), any(Author.class))).thenReturn(updatedAuthor);

        MockHttpServletResponse response = mockMvc.perform(put("/api/authors/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedAuthor)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("Author1");
        assertThat(response.getContentAsString()).contains("Author update");
        assertThat(response.getContentAsString()).contains("Madagascar");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        Long authorId = 1L;

        doNothing().when(service).deleteById(authorId);

        MockHttpServletResponse response = mockMvc.perform(delete("/api/authors/" + authorId))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).isEqualTo("Author deleted successfully");
        assertThat(service.findById(1L)).isEqualTo(null);
    }

}
