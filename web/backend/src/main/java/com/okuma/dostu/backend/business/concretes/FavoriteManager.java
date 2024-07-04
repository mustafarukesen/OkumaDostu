package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.FavoriteService;
import com.okuma.dostu.backend.business.dtos.requests.favorite.CreateFavoriteRequest;
import com.okuma.dostu.backend.business.dtos.responses.favorites.CreatedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.DeletedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.FindByUserIdFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.GetAllFavoriteResponse;
import com.okuma.dostu.backend.business.rules.FavoriteBusinessRules;
import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.core.utilities.mappers.ModelMapperService;
import com.okuma.dostu.backend.dataAccess.abstracts.BookRepository;
import com.okuma.dostu.backend.dataAccess.abstracts.FavoriteRepository;
import com.okuma.dostu.backend.entities.concretes.Book;
import com.okuma.dostu.backend.entities.concretes.Favorite;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavoriteManager implements FavoriteService {

    private FavoriteRepository favoriteRepository;
    private BookRepository bookRepository;
    private ModelMapperService modelMapperService;
    private FavoriteBusinessRules favoriteBusinessRules;

    @Override
    public List<GetAllFavoriteResponse> getAll(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        List<Favorite> favorites = favoriteRepository.findByUser(user);

        List<GetAllFavoriteResponse> getAllFavoriteResponse = favorites.stream()
                .map(favorite -> modelMapperService.forResponse()
                        .map(favorite, GetAllFavoriteResponse.class)).collect(Collectors.toList());

        return getAllFavoriteResponse;
    }

    @Override
    public CreatedFavoriteResponse add(CreateFavoriteRequest createFavoriteRequest, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        Book book = bookRepository.findById(createFavoriteRequest.getBookId()).orElseThrow();

        favoriteBusinessRules.checkExistByUserAndBook(user, book);

        Favorite favorite = new Favorite();
        favorite.setBook(book);
        favorite.setUser(user);

        Favorite savedFavorite = favoriteRepository.save(favorite);

        CreatedFavoriteResponse response = modelMapperService.forResponse().map(savedFavorite, CreatedFavoriteResponse.class);

        return response;
    }

    @Override
    public DeletedFavoriteResponse delete(Principal connectedUser, int id) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        Favorite favoriteToDelete = favoriteRepository.findByBookId(user.getId(), id);

        favoriteRepository.delete(favoriteToDelete);

        DeletedFavoriteResponse response = new DeletedFavoriteResponse(id);

        return response;
    }

    @Override
    public FindByUserIdFavoriteResponse findByUserId(Integer id) {
        List<Integer> bookIds = favoriteRepository.findByUserId(id);

        return FindByUserIdFavoriteResponse.builder()
                .bookId(bookIds)
                .build();
    }

    @Override
    public Page<GetAllFavoriteResponse> getFavoriteByUser(Principal connectedUser, Pageable pageable) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        List<Integer> bookIds = favoriteRepository.findByUserId(user.getId());

        Page<Book> books = bookRepository.findAllByIdIn(bookIds, pageable);

        return books.map(book -> modelMapperService.forResponse()
                .map(book, GetAllFavoriteResponse.class));
    }
}
