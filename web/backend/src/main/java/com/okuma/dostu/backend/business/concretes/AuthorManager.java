package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.AuthorService;
import com.okuma.dostu.backend.business.dtos.requests.authors.CreateAuthorRequest;
import com.okuma.dostu.backend.business.dtos.requests.authors.UpdateAuthorResponse;
import com.okuma.dostu.backend.business.dtos.responses.authors.CreatedAuthorResponse;
import com.okuma.dostu.backend.business.dtos.responses.authors.DeletedAuthorResponse;
import com.okuma.dostu.backend.business.dtos.responses.authors.GetAllAuthorResponse;
import com.okuma.dostu.backend.business.dtos.responses.authors.UpdatedAuthorResponse;
import com.okuma.dostu.backend.core.utilities.mappers.ModelMapperService;
import com.okuma.dostu.backend.dataAccess.abstracts.AuthorRepository;
import com.okuma.dostu.backend.entities.concretes.Author;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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

    @Override
    public UpdatedAuthorResponse update(UpdateAuthorResponse updateAuthorResponse) {
        Author author = modelMapperService.forRequest().map(updateAuthorResponse, Author.class);

        Author updatedAuthor = authorRepository.save(author);

        UpdatedAuthorResponse updatedAuthorResponse = modelMapperService.forResponse().map(updatedAuthor, UpdatedAuthorResponse.class);

        return updatedAuthorResponse;
    }

    @Override
    public DeletedAuthorResponse delete(int id) {
        Author authorToDelete = new Author();
        authorToDelete.setId(id);

        authorRepository.delete(authorToDelete);

        DeletedAuthorResponse deletedAuthorResponse = new DeletedAuthorResponse(id);

        return deletedAuthorResponse;
    }
}
