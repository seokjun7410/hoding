package com.msa.book.framwork.web.dto;

import com.msa.book.domain.model.Book;


public record BookOutputDTO(
        long bookNo,
        String bookTitle,
        String bookStatus
) {

    public static BookOutputDTO mapToDTO(Book book) {
        BookOutputDTO bookOutputDTO = new BookOutputDTO(
                book.getNo(),
                book.getTitle(),
                book.getBookStatus().toString()
        );

        return bookOutputDTO;
    }

}
