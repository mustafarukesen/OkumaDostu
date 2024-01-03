package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.BookService;
import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.requests.books.UpdateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.CreatedBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.DeletedBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.GetAllBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.UpdatedBookResponse;
import com.okuma.dostu.backend.core.utilities.mappers.ModelMapperService;
import com.okuma.dostu.backend.dataAccess.abstracts.BookRepository;
import com.okuma.dostu.backend.entities.concretes.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookManager implements BookService {
    BookRepository bookRepository;
    ModelMapperService modelMapperService;

    @Override
    public List<GetAllBookResponse> getAll() {
        List<Book> books = bookRepository.findAll();

        List<GetAllBookResponse> result = books.stream()
                .map(book -> this.modelMapperService.forResponse()
                        .map(book, GetAllBookResponse.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    public CreatedBookResponse add(CreateBookRequest createBookRequest) {
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

    //private BookBusinessRules bookBusinessRules;
}
