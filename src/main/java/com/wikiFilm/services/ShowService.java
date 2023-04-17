package com.wikiFilm.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wikiFilm.exception.ShowNotFoundException;
import com.wikiFilm.models.Show;
import com.wikiFilm.models.User;
import com.wikiFilm.repositories.ShowRepository;
import com.wikiFilm.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ShowService implements BaseService<Show>{

    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public List<Show> findAll() {
        return showRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Show> findAll(Pageable pageable) {
        return showRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Show findById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(()-> new ShowNotFoundException("Show not found with id: " + id));
    }

    @Transactional
    public Show findByTitle(String title) {
        return showRepository.findByTitle(title)
                .orElseThrow(()-> new ShowNotFoundException("Show not found with title: " + title));
    }

    @Override
    @Transactional
    public Show save(Show show) {
        return showRepository.save(show);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Show show = findById(id);

        List<User> users = userRepository.findAllByShowsContaining(show);
        for (User user : users) {
            user.getShows().remove(show);
        }

        userRepository.saveAll(users);

        show.setGenres(new ArrayList<>());
        show.setAuthor(null);

        showRepository.save(show);
        showRepository.deleteById(id);
    }

    @Transactional
    public Show likedShow(Show show) {
        show.setRating(show.getRating() + 1);
        return save(show);
    }

    @Transactional
    public List<Show> findByGenre(String genre) {
        return showRepository.findByGenres_Name(genre);
    }

    @Transactional
    public Show dislikedShow(Show show) {
        show.setRating(show.getRating() - 1);
        return save(show);
    }
    
    @Transactional
    public Show updateShow(Long id, Show showDetails) {
        Show show = findById(id);
        show.setTitle(showDetails.getTitle());
        show.setReleaseYear(showDetails.getReleaseYear());
        show.setRating(showDetails.getRating());
        show.setDescription(showDetails.getDescription());
        return save(show);
    }

}
