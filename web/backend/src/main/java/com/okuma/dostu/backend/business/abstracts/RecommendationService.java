package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.responses.users.GetAllRecommendationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface RecommendationService {
    Page<GetAllRecommendationResponse> getAll(Principal connectedUser, Pageable pageable);
}
