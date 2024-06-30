package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.dataAccess.abstracts.BookRepository;
import com.okuma.dostu.backend.entities.concretes.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findByTitle() {
        Page<Book> books = bookRepository.findByTitle("Dava", Pageable.ofSize(1));
        assertEquals(books.stream().count(), 1);
    }
}
