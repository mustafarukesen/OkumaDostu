package com.okuma.dostu.backend.dataAccess.abstracts;

import com.okuma.dostu.backend.entities.concretes.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    boolean existsByName(String authorName);
    Author findByName(String name);
}
