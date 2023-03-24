package com.wikiFilm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.wikiFilm.services.SecurityUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;


@ComponentScan
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    private SecurityUserDetailsService service;

    public WebSecurityConfig(SecurityUserDetailsService service) {
        this.service = service;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .headers(header -> header.frameOptions().sameOrigin())
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .logout(logout -> logout
                        .logoutUrl("/api/logout")
                        .deleteCookies("JSESSIONID"))
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/register", "/api/login", "/api/films", "/*").permitAll()
                        .requestMatchers("/api/logout").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/films/add").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .userDetailsService(service)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .httpBasic(basic -> basic.authenticationEntryPoint(authenticationEntryPoint));
        return http.build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
