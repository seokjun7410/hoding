package com.msa.book.application.output_port;

import com.msa.book.domain.model.Book;
import org.springframework.stereotype.Repository;


@Repository
public interface BookOutputPort {
    Book loadBook(long bookNo);

    Book save(Book book);
}
