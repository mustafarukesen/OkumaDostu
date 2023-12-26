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

                CreateBookRequest createBookRequest = modelMapperService.forRequest().map(book, CreateBookRequest.class);
                bookService.add(createBookRequest);

                // Diğer işlemler
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Book createBookFromJsonNode(JsonNode volumeInfo) {
        Book book = new Book();

        String title = volumeInfo.path("title").asText();
        String publisherName = volumeInfo.path("publisher").asText();
        String publishedDate = volumeInfo.path("publishedDate").asText();
        String description = volumeInfo.path("description").asText();
        String thumbnail = volumeInfo.path("imageLinks").path("thumbnail").asText();
        int pageCount = volumeInfo.path("pageCount").asInt();

        // ISBN-13 kontrolü
        JsonNode identifiersArray = volumeInfo.path("industryIdentifiers");
        for (JsonNode identifier : identifiersArray) {
            if (identifier.has("type") && identifier.get("type").asText().equals("ISBN_13")) {
                String isbn13 = identifier.get("identifier").asText();
                book.setIsbn13(isbn13);
            }
        }

        //Yazar kontrolü
         JsonNode authorsArray = volumeInfo.path("authors");
        if (authorsArray.isArray() && !authorsArray.isEmpty()) {
            String authorName = authorsArray.get(0).asText();
            Author author = authorRepository.findByName(authorName);
            if (author == null) {
                // Yazar yoksa, yeni bir yazar oluştur ve veritabanına ekle
                //System.out.println("----Yazar yok----");
                author = new Author();
                author.setName(authorName);
                //System.out.println("AuthorName 2 \n" + authorName);
                authorRepository.save(author);
            } else {
                book.setAuthor(author);
            }
        }

        // Kategori kontrolü
        JsonNode categoriesArray = volumeInfo.path("categories");
        if (categoriesArray.isArray() && !categoriesArray.isEmpty()) {
            String categoryName = categoriesArray.get(0).asText();
            System.out.println("-------- CategoryName: " + categoryName + " --------");
            Category category = categoryRepository.findByName(categoryName);
            if (category == null) {
                // Kategori yoksa, yeni bir kategori oluştur ve veritabanına ekle
                System.out.println("-------- Kategori yok --------");
                category = new Category();
                category.setName(categoryName);
                System.out.println("-------- CategoryName 2: " + categoryName + " ------\n");
                categoryRepository.save(category);
            } else {
                System.out.println("-------- Kategori var --------");
                System.out.println("-------- CategoryName 3: " + category.getName() + " --------\n");
                book.setCategory(category);
            }

        }

        // Yayıncı kontrolü
        Publisher publisher = publisherRepository.findByName(publisherName);
        //System.out.println("-------- " + publisher + " ------------");
        if (publisher == null) {
            // Yayıncı yoksa, yeni bir yayıncı oluştur ve veritabanına ekle
            //System.out.println("----Yayınevi yok----");
            publisher = new Publisher();
            publisher.setName(publisherName);
            //System.out.println("PublisherName 2 \n" + publisherName);
            publisherRepository.save(publisher);
        } else {
            //System.out.println("-----Yayınevi var-------\n");
            book.setPublisher(publisher);
        }

        // Diğer bilgiler
        book.setDescription(description);
        book.setTitle(title);
        book.setThumbnail(thumbnail);

        // LocalDate olarak publishedDate kontrolü
        if (!publishedDate.isEmpty()) {
            book.setPublishedDate(LocalDate.parse(publishedDate));
        }

        book.setPageCount(pageCount);

        return book;
    }
}
