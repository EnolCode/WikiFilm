package com.wikiFilm.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wikiFilm.models.Author;
import com.wikiFilm.services.AuthorService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService AuthorService;

    @GetMapping
    public List<Author> getAllAuthor() {
        return AuthorService.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return AuthorService.findById(id);
    }

    @GetMapping("/author/{name}")
    public Author getAuthorByName(@PathVariable String name) {
        return AuthorService.findByName(name);
    }

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<Author> createFilm(@RequestBody Author author) {
        Author savedAuthor = AuthorService.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }

    @PutMapping("/update/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        return AuthorService.updateAuthor(id, authorDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        AuthorService.deleteById(id);
        return ResponseEntity.ok("Author deleted successfully");
    }
}