package com.okuma.dostu.backend.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookWithPublisherDto {
    private int id;
    private String bookName;
    private String publisherName;
}
