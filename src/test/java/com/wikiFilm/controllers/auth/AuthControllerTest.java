package com.wikiFilm.controllers.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAccess() throws Exception {
        mockMvc.perform(get("/api/login")
                .with(user("user").roles("USER")))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Logged"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"))
                .andExpect(jsonPath("$.username").value("user"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/api/logout")
                .with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Logged out"));
    }
}
