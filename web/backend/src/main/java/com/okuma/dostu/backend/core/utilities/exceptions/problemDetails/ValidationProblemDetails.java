package com.okuma.dostu.backend.core.utilities.exceptions.problemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationProblemDetails extends ProblemDetails {

    public ValidationProblemDetails () {
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        setType("http://localhost:8080/exceptions/validation");
        setStatus("500");
    }

    private Map<String, String> errors;
}
