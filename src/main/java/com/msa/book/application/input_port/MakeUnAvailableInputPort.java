package com.msa.book.application.input_port;

import com.msa.book.application.output_port.BookOutputPort;
import com.msa.book.application.usecase.MakeUnAvailableUsecase;
import com.msa.book.domain.model.Book;
import com.msa.book.framwork.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MakeUnAvailableInputPort implements MakeUnAvailableUsecase {
    private final BookOutputPort bookOutputPort;
    @Override
    public BookOutputDTO unavailable(final long bookNo) {
        Book book = bookOutputPort.loadBook(bookNo);
        book.makeUnAvailable();
        return BookOutputDTO.mapToDTO(book);
    }
}
