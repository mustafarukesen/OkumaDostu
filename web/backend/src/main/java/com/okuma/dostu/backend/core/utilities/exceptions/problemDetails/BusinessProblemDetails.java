package com.okuma.dostu.backend.core.utilities.exceptions.problemDetails;

public class BusinessProblemDetails extends ProblemDetails {

    public BusinessProblemDetails() {
        setTitle("Business Rule Violation");
        setStatus("500");
    }
}
