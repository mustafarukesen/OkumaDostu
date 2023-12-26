package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.AuthorService;
import com.okuma.dostu.backend.business.dtos.requests.authors.CreateAuthorRequest;
import com.okuma.dostu.backend.business.dtos.responses.authors.CreatedAuthorResponse;
import com.okuma.dostu.backend.business.dtos.responses.authors.GetAllAuthorResponse;
import com.okuma.dostu.backend.core.utilities.mappers.ModelMapperService;
import com.okuma.dostu.backend.dataAccess.abstracts.AuthorRepository;
import com.okuma.dostu.backend.entities.concretes.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorManager implements AuthorService {

    AuthorRepository authorRepository;
    ModelMapperService modelMapperService;

    @Override
    public List<GetAllAuthorResponse> getAll() {
        List<Author> authors = authorRepository.findAll();

        List<GetAllAuthorResponse> result = authors.stream()
                .map(author -> this.modelMapperService.forResponse()
                        .map(author, GetAllAuthorResponse.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    public CreatedAuthorResponse add(CreateAuthorRequest createAuthorRequest) {
        Author author = this.modelMapperService.forRequest().map(createAuthorRequest, Author.class);

        Author createdAuthor = authorRepository.save(author);

        CreatedAuthorResponse createdAuthorResponse = this.modelMapperService.forResponse().map(createdAuthor, CreatedAuthorResponse.class);

        return createdAuthorResponse;
    }
}
