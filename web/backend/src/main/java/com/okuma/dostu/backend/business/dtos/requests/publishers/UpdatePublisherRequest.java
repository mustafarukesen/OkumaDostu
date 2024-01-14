package com.okuma.dostu.backend.business.dtos.requests.publishers;

import com.okuma.dostu.backend.business.messages.validation.PublisherValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePublisherRequest {
    @NotNull(message = PublisherValidationMessages.notNullPublisherId)
    @Positive(message = PublisherValidationMessages.positivePublisherId)
    private int id;

    @NotNull(message = PublisherValidationMessages.notNullName)
    @NotBlank(message = PublisherValidationMessages.notBlankName)
    private String name;
}
