package com.okuma.dostu.backend.business.dtos.requests.publishers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePublisherRequest {
    @NotNull
    private int id;

    @NotNull
    @NotBlank
    private String name;
}
