package com.okuma.dostu.backend.business.dtos.responses.authors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedAuthorResponse {
    private int id;
    private String name;
}
