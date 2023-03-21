package com.wikiFilm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wikiFilm.exception.AuthorNotFoundException;
import com.wikiFilm.models.Author;
import com.wikiFilm.repositories.AuthorRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthorService implements BaseService<Author> {

    private final AuthorRepository AuthorRepository;

    @Override
    public List<Author> findAll() {
        return AuthorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return AuthorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));
    }

    public Author findByName(String author) {
        return AuthorRepository.findByName(author)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with title: " + author));
    }

    @Override
    public Author save(Author author) {
        return AuthorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = findById(id);
        author.setName(authorDetails.getName());
        author.setSurname(authorDetails.getSurname());
        author.setAge(authorDetails.getAge());
        author.setCountry(authorDetails.getCountry());
        author.setRating(authorDetails.getRating());
        return save(author);
    }

    @Override
    public void deleteById(Long id) {
        Author author = findById(id);
        AuthorRepository.deleteById(author.getId());
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return AuthorRepository.findAll(pageable);
    }
}