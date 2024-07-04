package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.FavoriteService;
import com.okuma.dostu.backend.business.dtos.requests.favorite.CreateFavoriteRequest;
import com.okuma.dostu.backend.business.dtos.responses.favorites.CreatedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.DeletedFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.FindByUserIdFavoriteResponse;
import com.okuma.dostu.backend.business.dtos.responses.favorites.GetAllFavoriteResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{id}")
    @PreAuthorize("#id == principal.id")
    public ResponseEntity<FindByUserIdFavoriteResponse> findByUserId(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(favoriteService.findByUserId(id));
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<GetAllFavoriteResponse>> getFavoriteByUser(Principal connectedUser, Pageable pageable) {
        return ResponseEntity.ok(favoriteService.getFavoriteByUser(connectedUser, pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFavoriteResponse add(@RequestBody @Valid CreateFavoriteRequest createFavoriteRequest, Principal connectedUser) {
        return favoriteService.add(createFavoriteRequest, connectedUser);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public DeletedFavoriteResponse delete(Principal connectedUser, @RequestParam int id) {
        return favoriteService.delete(connectedUser, id);
    }
}
