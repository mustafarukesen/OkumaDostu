package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.favorite.CreateFavoriteRequest;
import com.okuma.dostu.backend.business.dtos.responses.favorites.CreatedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.DeletedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.GetAllFavoriteResponse;

import java.security.Principal;
import java.util.List;

public interface FavoriteService {
    List<GetAllFavoriteResponse> getAll();

    CreatedFavoriteResponse add(CreateFavoriteRequest createFavoriteRequest, Principal connectedUser);

    DeletedFavoriteResponse delete(int id);
}
