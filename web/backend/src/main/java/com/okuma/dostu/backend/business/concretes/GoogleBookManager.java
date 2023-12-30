package com.okuma.dostu.backend.business.concretes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okuma.dostu.backend.business.abstracts.BookService;
import com.okuma.dostu.backend.business.abstracts.GoogleBookService;
import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.core.utilities.mappers.ModelMapperService;
import com.okuma.dostu.backend.dataAccess.abstracts.AuthorRepository;
import com.okuma.dostu.backend.dataAccess.abstracts.CategoryRepository;
import com.okuma.dostu.backend.dataAccess.abstracts.PublisherRepository;
import com.okuma.dostu.backend.entities.concretes.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class GoogleBookManager implements GoogleBookService {


    private BookService bookService;
    private CategoryRepository categoryRepository;
    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;
    private ModelMapperService modelMapperService;

    @Override
    public void searchAndSaveBooks(String authorName) {
        String[] words = authorName.split(" ");
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=inauthor:" + words[0] + "%20" + words[1]
                + "&key=AIzaSyDZNDUsyQqVLeompmkIQ4TAkEvRICoidAE&lang=tr";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(new URL(apiUrl));
            JsonNode itemsArray = jsonNode.path("items");

            for (JsonNode item : itemsArray) {
                Book book = createBookFromJsonNode(item.path("volumeInfo"));

                if (book.getCategory() == null || book.getPublisher() == null || book.getAuthor() == null)
                    continue;

                CreateBookRequest createBookRequest = modelMapperService.forRequest().map(book, CreateBookRequest.class);
                bookService.add(createBookRequest);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Book createBookFromJsonNode(JsonNode volumeInfo) {
        Book book = new Book();

        book.setTitle(volumeInfo.path("title").asText());
        book.setPageCount(volumeInfo.path("pageCount").asInt());
        book.setIsbn13(getIsbn13(volumeInfo.path("industryIdentifiers")));
        book.setAuthor(getAuthor(volumeInfo.path("authors")));
        book.setCategory(getCategory(volumeInfo.path("categories")));
        book.setPublisher(getPublisher(volumeInfo.path("publisher")));
        book.setDescription(volumeInfo.path("description").asText());
        book.setThumbnail(volumeInfo.path("imageLinks").path("thumbnail").asText());

        String publishedDate = volumeInfo.path("publishedDate").asText();
        if (!publishedDate.isEmpty()) {
            book.setPublishedDate(LocalDate.parse(publishedDate));
        }

        return book;
    }

    private String getIsbn13(JsonNode identifiersArray) {
        for (JsonNode identifier : identifiersArray) {
            if (identifier.has("type") && identifier.get("type").asText().equals("ISBN_13")) {
                return identifier.get("identifier").asText();
            }
        }
        return null;
    }

    private Author getAuthor(JsonNode authorsArray) {
        if (authorsArray.isArray() && !authorsArray.isEmpty()) {
            String authorName = authorsArray.get(0).asText();
            if (authorName != null) {
                Author author = authorRepository.findByName(authorName);
                if (author == null) {
                    author = new Author();
                    author.setName(authorName);
                    authorRepository.save(author);
                }
                return author;
            }
        }
        return null;
    }

    private Category getCategory(JsonNode categoriesArray) {
        if (categoriesArray.isArray() && !categoriesArray.isEmpty()) {
            String categoryName = categoriesArray.get(0).asText();
            if (categoryName != null) {
                Category category = categoryRepository.findByName(categoryName);
                if (category == null) {
                    category = new Category();
                    category.setName(categoryName);
                    categoryRepository.save(category);
                }
                return category;
            }
        }
        return null;
    }

    private Publisher getPublisher(JsonNode publisherNode) {
        String publisherName = publisherNode.asText();
        if (publisherName != null) {
            Publisher publisher = publisherRepository.findByName(publisherName);
            if (publisher == null) {
                publisher = new Publisher();
                publisher.setName(publisherName);
                publisherRepository.save(publisher);
            }
            return publisher;
        }
        return null;
    }
}
