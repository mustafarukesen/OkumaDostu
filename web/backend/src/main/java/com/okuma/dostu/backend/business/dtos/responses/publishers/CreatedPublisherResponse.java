package com.okuma.dostu.backend.business.dtos.responses.publishers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedPublisherResponse {
    private int id;
    private String name;
}
