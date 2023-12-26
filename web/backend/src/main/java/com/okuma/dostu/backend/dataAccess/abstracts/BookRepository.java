package com.okuma.dostu.backend.dataAccess.abstracts;

import com.okuma.dostu.backend.entities.concretes.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByTitle(String name);
}
