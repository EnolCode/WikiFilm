package com.wikiFilm.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.wikiFilm.exception.FilmNotFoundException;
import com.wikiFilm.models.Film;
import com.wikiFilm.models.User;
import com.wikiFilm.repositories.FilmRepository;
import com.wikiFilm.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FilmService implements BaseService<Film> {
    private final FilmRepository filmRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    @Transactional
    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with id: " + id));
    }

    @Transactional
    public Film findByTitle(String title) {
        return filmRepository.findByTitle(title)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with title: " + title));
    }

    @Transactional
    public List<Film> findByGenre(String genre) {
        return filmRepository.findByGenres_Name(genre);
    }

    @Override
    @Transactional
    public Film save(Film film) {
        return filmRepository.save(film);
    }

    @Transactional
    public Film likedFilm(Film film) {
        film.setRating(film.getRating() + 1);
        return save(film);
    }

    @Transactional
    public Film dislikedFilm(Film film) {
        film.setRating(film.getRating() - 1);
        return save(film);
    }

    @Transactional
    public Film updateFilm(Long id, Film filmDetails) {
        Film film = findById(id);
        film.setTitle(filmDetails.getTitle());
        film.setReleaseYear(filmDetails.getReleaseYear());
        film.setRating(filmDetails.getRating());
        film.setDescription(filmDetails.getDescription());
        return save(film);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
          // Obtener la película por su ID
    Film film = findById(id);

    // Obtener todos los usuarios que tienen esta película en su colección de películas
    List<User> users = userRepository.findAllByFilmsContaining(film);

    // Eliminar la película de la colección de películas de cada usuario
    for (User user : users) {
        user.getFilms().remove(film);
    }

    // Guardar los cambios en los usuarios
    userRepository.saveAll(users);

    // Establecer los géneros y autor de la película en null
    film.setGenres(new ArrayList<>());
    film.setAuthor(null);

    // Guardar los cambios en la película
    filmRepository.save(film);

    // Eliminar la película
    filmRepository.deleteById(film.getId());
    }

    @Override
    @Transactional
    public Page<Film> findAll(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }
}