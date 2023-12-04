package com.msa.book.application.usecase;

import com.msa.book.framwork.web.dto.BookInPutDTO;
import com.msa.book.framwork.web.dto.BookOutputDTO;

public interface AddBookUsecase {
    BookOutputDTO addBook(BookInPutDTO bookInPutDTO);
}
