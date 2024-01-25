package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.FavoriteService;
import com.okuma.dostu.backend.business.dtos.requests.favorite.CreateFavoriteRequest;
import com.okuma.dostu.backend.business.dtos.responses.favorites.CreatedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.DeletedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.GetAllFavoriteResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
@AllArgsConstructor
public class FavoritesController {
    private FavoriteService favoriteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllFavoriteResponse>> getAll(Principal connectedUser) {
        return ResponseEntity.ok(favoriteService.getAll(connectedUser));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFavoriteResponse add(@RequestBody @Valid CreateFavoriteRequest createFavoriteRequest, Principal connectedUser) {
        return favoriteService.add(createFavoriteRequest, connectedUser);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public DeletedFavoriteResponse delete(@RequestParam int id) {
        return favoriteService.delete(id);
    }
}
