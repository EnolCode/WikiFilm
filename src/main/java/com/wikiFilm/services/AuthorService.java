package com.wikiFilm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wikiFilm.exception.AuthorNotFoundException;
import com.wikiFilm.models.Author;
import com.wikiFilm.repositories.AuthorRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthorService implements BaseService<Author> {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));
    }

    @Transactional
    public Author findByName(String author) {
        return authorRepository.findByName(author)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with title: " + author));
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
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
    @Transactional
    public void deleteById(Long id) {
        Author authorDeleted = findById(id);
        authorRepository.deleteById(authorDeleted.getId());
    }

    @Override
    @Transactional
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}