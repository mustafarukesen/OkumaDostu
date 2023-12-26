package com.okuma.dostu.backend.core.apiUsage.googleBookApi;

import lombok.Data;

@Data
public class GoogleBookItem {
    private GoogleVolumeInfo volumeInfo;

    private String getIsbn13() {
        if (volumeInfo != null && volumeInfo.getIndustryIdentifiers() != null) {
            for (GoogleIndustryIdentifier identifier : volumeInfo.getIndustryIdentifiers()) {
                if ("ISBN_13".equals(identifier.getType())) {
                    return identifier.getIdentifier();
                }
            }
        }
        return null;
    }
}
