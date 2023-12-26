package com.okuma.dostu.backend.core.apiUsage.googleBookApi;

import lombok.Data;

import java.util.List;

@Data
public class GoogleBooksApiResponse {
    private List<GoogleBookItem> items;
}
