package com.wikiFilm.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wikiFilm.models.Show;
import com.wikiFilm.services.ShowService;

@WebMvcTest(controllers = ShowController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ShowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShowService service;

    private Show show;

    @BeforeEach
    void setUp() {
        show = new Show(1L, "show1", "description", "image1", "2022", 80, null,null ,null);
    }

    @Test
    void getAllShow_shouldReturnAllShows() throws Exception {
        List<Show> shows = new ArrayList<>();
        shows.add(show);
            
        when(service.findAll()).thenReturn(shows);
        MockHttpServletResponse response = mockMvc.perform(get("/api/shows")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("show1");
        assertThat(response.getStatus()).isEqualTo(200);
                
    }

    @Test
    void getShowById_shouldReturnShow() throws Exception {
        when(service.findById(1L)).thenReturn(show);
        MockHttpServletResponse response = mockMvc.perform(get("/api/shows/1")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("show1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void getShowByTitle_shouldReturnShow() throws Exception {
        when(service.findByTitle("show1")).thenReturn(show);
        MockHttpServletResponse response = mockMvc.perform(get("/api/shows/title/show1")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("show1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testCreateShow() throws Exception {
        when(service.save(any(Show.class))).thenReturn(show);

        MockHttpServletResponse response = mockMvc.perform(post("/api/shows/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(show)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("show1");
        assertThat(response.getContentAsString()).contains("description");
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void testUpdateShow() throws Exception {
        Show updatedFilm = new Show(1L, "showUpdated", "description", "image2", "film update", 80,null, null,null);

        when(service.updateShow(any(Long.class), any(Show.class))).thenReturn(updatedFilm);

        MockHttpServletResponse response = mockMvc.perform(put("/api/shows/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedFilm)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("showUpdated");
        assertThat(response.getContentAsString()).contains("description");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testDeleteShow() throws Exception {
        ResultActions result = mockMvc.perform(delete("/api/shows/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(result.andReturn().getResponse().getStatus()).isEqualTo(200);
    }
}
