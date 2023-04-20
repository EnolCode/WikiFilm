package com.wikiFilm.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wikiFilm.models.Film;
import com.wikiFilm.models.Show;
import com.wikiFilm.models.User;
import com.wikiFilm.services.FilmService;
import com.wikiFilm.services.ShowService;
import com.wikiFilm.services.UserService;
import com.wikiFilm.services.Storage.StorageService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("media")
@AllArgsConstructor
public class MediaController {
    private final StorageService storageService;
    private final HttpServletRequest request;  
    private final UserService userService; 
    private final FilmService filmService;
    private final ShowService showService;

    @PostMapping("upload")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile, User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        user = userService.findByUsername(currentUsername);

        user.setAvatar(multipartFile.getOriginalFilename());
        userService.save(user);
        
        String path = storageService.store(multipartFile); // Almacena usando el servicio de almacenamiento
        String host = request.getRequestURL().toString().replace(request.getRequestURI(),""); // Obtiene la URL del archivo que podra ser consultada por otro metodo
        
        String url = ServletUriComponentsBuilder    
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();

        return Map.of("url", url); // Retorna un mapa con un solo elemento para la URL
    }

    @PostMapping("upload/film")
    public Map<String, String> uploadFilm(@RequestParam("file") MultipartFile multipartFile, Film film) {

        film.setImage(multipartFile.getOriginalFilename());
        filmService.save(film);
        
        String path = storageService.store(multipartFile); // Almacena usando el servicio de almacenamiento
        String host = request.getRequestURL().toString().replace(request.getRequestURI(),""); // Obtiene la URL del archivo que podra ser consultada por otro metodo
        
        String url = ServletUriComponentsBuilder    
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();

        return Map.of("url", url); // Retorna un mapa con un solo elemento para la URL
    }

    @PostMapping("upload/show")
    public Map<String, String> uploadShow(@RequestParam("file") MultipartFile multipartFile, Show show) {

        show.setImage(multipartFile.getOriginalFilename());
        showService.save(show);
        
        String path = storageService.store(multipartFile); // Almacena usando el servicio de almacenamiento
        String host = request.getRequestURL().toString().replace(request.getRequestURI(),""); // Obtiene la URL del archivo que podra ser consultada por otro metodo
        
        String url = ServletUriComponentsBuilder    
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();

        return Map.of("url", url); // Retorna un mapa con un solo elemento para la URL
    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException { // Recupera un archivo por un resoruce a traves de un nombre
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }

}
