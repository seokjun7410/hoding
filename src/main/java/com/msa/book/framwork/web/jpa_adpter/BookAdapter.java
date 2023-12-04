package com.msa.book.framwork.web.jpa_adpter;

import com.msa.book.application.output_port.BookOutputPort;
import com.msa.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class BookAdapter implements BookOutputPort {

    private final BookRepository bookRepository;

    @Override
    public Book loadBook(final long bookNo) {
        return bookRepository.findById(bookNo).get(); //예외처리 간소화를 위한 생략
    }

    @Override
    public Book save(final Book book) {
        return bookRepository.save(book);
    }
}
