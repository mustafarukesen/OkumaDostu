package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.favorite.CreateFavoriteRequest;
import com.okuma.dostu.backend.business.dtos.responses.favorites.CreatedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.DeletedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.FindByUserIdFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.GetAllFavoriteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface FavoriteService {
    List<GetAllFavoriteResponse> getAll(Principal connectedUser);

    CreatedFavoriteResponse add(CreateFavoriteRequest createFavoriteRequest, Principal connectedUser);

    DeletedFavoriteResponse delete(Principal connectedUser, int id);

    FindByUserIdFavoriteResponse findByUserId(Integer id);

    Page<GetAllFavoriteResponse> getFavoriteByUser(Principal connectedUser, Pageable pageable);
}
