package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.responses.users.GetAllRecommendationResponse;

import java.security.Principal;
import java.util.List;

public interface RecommendationService {
    List<GetAllRecommendationResponse> getAll(Principal connectedUser);
}
