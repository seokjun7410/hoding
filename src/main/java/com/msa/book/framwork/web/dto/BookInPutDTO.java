package com.msa.book.framwork.web.dto;

import java.time.LocalDate;

public record BookInPutDTO(
        String title,
        String description,
        String author,
        String isbn,
        LocalDate publicationDate,
        String source,
        String classfication,
        String location
) {}
