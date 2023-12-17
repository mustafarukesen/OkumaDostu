package com.okuma.dostu.backend.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "book_description")
    private String bookDescription;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "book_isbn_13")
    private String bookIsbn13;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_page_count")
    private int bookPageCount;

    @Column(name = "published_date")
    private LocalDate bookPublishedDate;

    @ManyToOne()
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "book_thumbnail")
    private String bookThumbnail;



}
