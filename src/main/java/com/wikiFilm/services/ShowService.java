package com.wikiFilm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wikiFilm.exception.ShowNotFoundException;
import com.wikiFilm.models.Show;
import com.wikiFilm.repositories.ShowRepository;
import com.wikiFilm.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ShowService implements BaseService<Show>{

    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    @Override
    public List<Show> findAll() {
        return showRepository.findAll();
    }

    @Override
    public Page<Show> findAll(Pageable pageable) {
        return showRepository.findAll(pageable);
    }

    @Override
    public Show findById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(()-> new ShowNotFoundException("Show not found with id: " + id));
    }

    @Override
    public Show save(Show entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    
}
