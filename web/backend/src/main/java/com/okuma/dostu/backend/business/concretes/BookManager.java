package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.BookService;
import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.requests.books.UpdateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.*;
import com.okuma.dostu.backend.business.rules.BookBusinessRules;
import com.okuma.dostu.backend.core.utilities.mappers.ModelMapperService;
import com.okuma.dostu.backend.dataAccess.abstracts.BookRepository;
import com.okuma.dostu.backend.entities.concretes.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookManager implements BookService {
    private BookRepository bookRepository;
    private ModelMapperService modelMapperService;
    private BookBusinessRules bookBusinessRules;

    @Override
    public List<GetAllBookResponse> getAll() {
        List<Book> books = bookRepository.findAll();

        List<GetAllBookResponse> result = books.stream()
                .map(book -> this.modelMapperService.forResponse()
                        .map(book, GetAllBookResponse.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    public Page<GetAllBookResponse> getAllWithPagination(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);

        Page<GetAllBookResponse> responses = books.map(book -> this.modelMapperService.forResponse()
                .map(book, GetAllBookResponse.class));

        return responses;
    }

    @Override
    public GetByIdBookResponse getById(int id) {
        Book book = bookRepository.findById(id).orElseThrow();

        GetByIdBookResponse result = modelMapperService.forResponse().map(book, GetByIdBookResponse.class);

        return result;
    }

    @Override
    public Page<GetByTitleBookResponse> getByTitle(String title, Pageable pageable) {
        Page<Book> books = bookRepository.findByTitle(title, pageable);

        Page<GetByTitleBookResponse> result = books.map(book -> modelMapperService.forResponse()
                .map(book, GetByTitleBookResponse.class));

        return result;
    }

    @Override
    public Page<GetByAuthorNameBookResponse> getByAuthorName(String authorName, Pageable pageable) {
        Page<Book> books = bookRepository.findByAuthorName(authorName, pageable);

        Page<GetByAuthorNameBookResponse> result = books.map(book -> modelMapperService.forResponse()
                .map(book, GetByAuthorNameBookResponse.class));

        return result;
    }

    @Override
    public CreatedBookResponse add(CreateBookRequest createBookRequest) {
        bookBusinessRules.checkIfBookNameExists(createBookRequest.getTitle());

        Book book = this.modelMapperService.forRequest().map(createBookRequest, Book.class);

        Book createdBook = this.bookRepository.save(book);

        CreatedBookResponse createdBookResponse = modelMapperService.forResponse().map(createdBook, CreatedBookResponse.class);

        return createdBookResponse;
    }

    @Override
    public UpdatedBookResponse update(UpdateBookRequest updateBookRequest) {
        Book book = modelMapperService.forRequest().map(updateBookRequest, Book.class);

        Book updatedBook = bookRepository.save(book);

        UpdatedBookResponse updatedBookResponse = modelMapperService.forResponse().map(updatedBook, UpdatedBookResponse.class);

        return updatedBookResponse;
    }

    @Override
    public DeletedBookResponse delete(int id) {
        Book bookToDelete = new Book();
        bookToDelete.setId(id);

        bookRepository.delete(bookToDelete);

        DeletedBookResponse deletedBookResponse = new DeletedBookResponse();
        deletedBookResponse.setId(id);

        return deletedBookResponse;
    }
}
