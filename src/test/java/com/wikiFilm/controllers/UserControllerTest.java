
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
import com.wikiFilm.models.User;
import com.wikiFilm.services.UserService;


@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "user1", "pass1", null);
    }

    @Test
    void getAllUsers_shouldReturnAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(user);

        when(service.findAll()).thenReturn(users);
        MockHttpServletResponse response = mockMvc.perform(get("/api/users")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("user1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void getUserById_shouldReturnUser() throws Exception {
        when(service.findById(1L)).thenReturn(user);
        MockHttpServletResponse response = mockMvc.perform(get("/api/users/1")
                                            .contentType("application/json"))
                                            .andExpect(status().isOk())
                                            .andReturn()
                                            .getResponse();

        assertThat(response.getContentAsString()).contains("user1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void getUserByUsername_shouldReturnUser() throws Exception {
        when(service.findByUsername("user1")).thenReturn(user);
        MockHttpServletResponse response = mockMvc.perform(get("/api/users/username/user1")
                                            .contentType("application/json"))
                                            .andExpect(status().isOk())
                                            .andReturn()
                                            .getResponse();

        assertThat(response.getContentAsString()).contains("user1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testCreateUser() throws Exception {
        when(service.save(any(User.class))).thenReturn(user);

        MockHttpServletResponse response = mockMvc.perform(post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("user1");
        assertThat(response.getContentAsString()).contains("pass1");
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User updatedUser = new User(1L, "userUpdate", "passUpdate", null);
        when(service.updateUser(any(Long.class), any(User.class))).thenReturn(updatedUser);

        MockHttpServletResponse response = mockMvc.perform(put("/api/users/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("userUpdate");
        assertThat(response.getContentAsString()).contains("passUpdate");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long userId = 1L;

        doNothing().when(service).deleteById(userId);

        MockHttpServletResponse response = mockMvc.perform(delete("/api/users/" + userId))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).isEqualTo("User deleted successfully");
        assertThat(service.findById(1L)).isEqualTo(null);
    }

}
