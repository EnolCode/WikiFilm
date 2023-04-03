package com.wikiFilm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wikiFilm.exception.UserNotFoundException;
import com.wikiFilm.models.User;
import com.wikiFilm.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService implements BaseService<User> {

    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                         .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    @Override
    @Transactional
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);

    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);

    }

  

    @Transactional
    public User store(User user) {

        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            throw new UserNotFoundException("User already exists with username: " + user.getUsername());
        }

        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new UserNotFoundException("Dont save user: " + user.getUsername());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User userDeleted = findById(id);
        userRepository.deleteById(userDeleted.getId());
    }

    @Transactional
    public User updateUser(Long id, User UserDetails) {
        User user = findById(id);
        user.setUsername(UserDetails.getUsername());
        user.setPassword(UserDetails.getPassword());
        return save(user);
    }

}
