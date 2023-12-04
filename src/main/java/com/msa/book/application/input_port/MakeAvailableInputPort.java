package com.msa.book.application.input_port;

import com.msa.book.application.output_port.BookOutputPort;
import com.msa.book.application.usecase.MakeAvailableUsecase;
import com.msa.book.domain.model.Book;
import com.msa.book.framwork.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MakeAvailableInputPort implements MakeAvailableUsecase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO available(final Long bookNo) {
        Book book = bookOutputPort.loadBook(bookNo);
        book.makeAvailable();
        return BookOutputDTO.mapToDTO(book);
    }
}
