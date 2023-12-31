package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.GoogleBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoogleBookManagerTest {

    @Autowired
    private GoogleBookService googleBookService;

    @Test
    void saveBookFromGoogleBookManager() {
        googleBookService.searchAndSaveBooks("Emile Zola");
        assert(true);
    }
}
