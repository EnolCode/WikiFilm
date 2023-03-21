package com.wikiFilm.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wikiFilm.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

    Optional<Author> findByName(String name);
}
