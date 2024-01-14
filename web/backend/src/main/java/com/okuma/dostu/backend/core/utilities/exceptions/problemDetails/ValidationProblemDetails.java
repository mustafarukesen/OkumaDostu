package com.okuma.dostu.backend.core.utilities.exceptions.problemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationProblemDetails extends ProblemDetails {

    public ValidationProblemDetails() {
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        setStatus("400");
    }

    private Map<String, String> errors;
}
