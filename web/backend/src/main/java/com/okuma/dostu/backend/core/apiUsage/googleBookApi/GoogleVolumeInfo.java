package com.okuma.dostu.backend.core.apiUsage.googleBookApi;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GoogleVolumeInfo {
    private String title;
    private List<String> authors;
    private String publisher;
    private String description;
    private List<String> categories;
    private List<GoogleIndustryIdentifier> industryIdentifiers;
    private int pageCount;
    private String publishedDate;
    private ImageLinks imageLinks;

//    public LocalDate getFormattedPublishedDate() {
//        if (publishedDate != null) {
//            return LocalDate.parse(publishedDate);
//        }
//        return null;
//    }
}
