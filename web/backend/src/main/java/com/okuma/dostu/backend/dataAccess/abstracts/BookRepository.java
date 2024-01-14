package com.okuma.dostu.backend.dataAccess.abstracts;

import com.okuma.dostu.backend.entities.concretes.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByTitle(String name);

    List<Book> findByTitle(String title);

    @Query("select b from Book b join fetch b.author where b.author.name = :authorName")
    List<Book> findAllByAuthorName(@Param("authorName") String authorName);
}
