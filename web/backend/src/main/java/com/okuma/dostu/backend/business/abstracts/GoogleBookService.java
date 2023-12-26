package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.entities.concretes.Book;

import java.util.List;

public interface GoogleBookService {
    void searchAndSaveBooks(String authorName);
}
