package com.okuma.dostu.backend.business.dtos.requests.publishers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePublisherRequest {
    private String name;
}
