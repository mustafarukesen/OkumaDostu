package com.okuma.dostu.backend.dataAccess.abstracts;

import com.okuma.dostu.backend.entities.concretes.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByTitle(String name);

    Page<Book> findByTitle(String title, Pageable pageable);


    @Query("select b from Book b join fetch b.author where b.author.name = :authorName")
    Page<Book> findByAuthorName(@Param("authorName") String authorName, Pageable pageable);
}
