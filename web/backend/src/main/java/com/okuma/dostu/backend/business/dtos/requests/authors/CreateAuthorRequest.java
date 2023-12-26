package com.okuma.dostu.backend.business.dtos.requests.authors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorRequest {
    private String name;
}
