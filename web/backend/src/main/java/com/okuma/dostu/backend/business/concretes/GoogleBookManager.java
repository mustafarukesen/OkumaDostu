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
import com.okuma.dostu.backend.entities.concretes.Author;
import com.okuma.dostu.backend.entities.concretes.Book;
import com.okuma.dostu.backend.entities.concretes.Category;
import com.okuma.dostu.backend.entities.concretes.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class GoogleBookManager implements GoogleBookService {


    private final BookService bookService;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final ModelMapperService modelMapperService;

    @Value("${google.books.api.url}")
    private String googleBooksApiUrl;

    @Override
    public void searchAndSaveBooks(String authorName) {
        String[] words = authorName.split(" ");
        String apiUrl = String.format(googleBooksApiUrl, words[0], words[1]);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(new URL(apiUrl));
            JsonNode itemsArray = jsonNode.path("items");

            for (JsonNode item : itemsArray) {
                Book book = createBookFromJsonNode(item.path("volumeInfo"));

                if ((book.getCategory() == null) || (book.getPublisher() == null) || (book.getAuthor() == null) || (book.getIsbn13() == null || book.getIsbn13().isEmpty()) || (book.getDescription() == null || book.getDescription().isEmpty()) || (book.getTitle() == null || book.getTitle().isEmpty()) || (book.getPublishedDate() == null) || (book.getPageCount() < 0) || (book.getThumbnail() == null || book.getThumbnail().isEmpty()))
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
        book.setDescription(volumeInfo.path("description").asText());
        book.setThumbnail(volumeInfo.path("imageLinks").path("thumbnail").asText());

        String publishedDate = volumeInfo.path("publishedDate").asText();
        if (!publishedDate.isEmpty()) {
            book.setPublishedDate(convertToPublishedDate(publishedDate));
        }
        if ((book.getIsbn13() == null || book.getIsbn13().isEmpty()) || (book.getDescription() == null || book.getDescription().isEmpty()) || (book.getTitle() == null || book.getTitle().isEmpty()) || (book.getPublishedDate() == null) || (book.getPageCount() < 0) || (book.getThumbnail() == null || book.getThumbnail().isEmpty()))
            return book;
        book.setAuthor(getAuthor(volumeInfo.path("authors")));
        book.setCategory(getCategory(volumeInfo.path("categories")));
        book.setPublisher(getPublisher(volumeInfo.path("publisher")));
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
            if (authorName != null && !authorName.isEmpty()) {
                authorName = formatName(authorName);
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
            if (categoryName != null && !categoryName.isEmpty()) {
                categoryName = formatName(categoryName);
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
        if (publisherName != null && !publisherName.isEmpty()) {
            publisherName = formatName(publisherName);
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

    private LocalDate convertToPublishedDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ISO_DATE;

        try {
            return LocalDate.parse(date, inputFormatter);
        } catch (DateTimeParseException e) {
            String year = date.substring(0, 4);
            String formattedDate = String.format("%s-01-01", year);
            return LocalDate.parse(formattedDate, outputFormatter);
        }
    }

    private String formatName(String name) {

        String cleanedName = removeNonLatinCharacters(name);

        String[] nameParts = cleanedName.split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (String part : nameParts) {
            if (!part.isEmpty()) {
                if (formattedName.length() > 0) {
                    formattedName.append(" ");
                }
                formattedName.append(part.substring(0, 1).toUpperCase());
                formattedName.append(part.substring(1).toLowerCase());
            }
        }

        return formattedName.toString();
    }

    private String removeNonLatinCharacters(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
