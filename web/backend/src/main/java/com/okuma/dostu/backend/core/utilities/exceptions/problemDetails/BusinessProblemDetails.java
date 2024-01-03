package com.okuma.dostu.backend.core.utilities.exceptions.problemDetails;

public class BusinessProblemDetails extends ProblemDetails{

    public BusinessProblemDetails() {
        setTitle("Business Rule Violation");
        setType("http://localhost/exceptions/business");
        setStatus("500");
    }
}
