package com.okuma.dostu.backend;

import com.okuma.dostu.backend.business.abstracts.GoogleBookService;
import com.okuma.dostu.backend.entities.concretes.Author;
import com.okuma.dostu.backend.entities.concretes.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private GoogleBookService googleBookService;

    @Test
    void contextLoads() {
        googleBookService.searchAndSaveBooks("Emile Zola");
        assert(true);
    }
}
