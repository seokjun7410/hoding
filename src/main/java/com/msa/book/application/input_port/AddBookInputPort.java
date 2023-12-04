package com.msa.book.application.input_port;

import com.msa.book.application.output_port.BookOutputPort;
import com.msa.book.application.usecase.AddBookUsecase;
import com.msa.book.domain.model.Book;
import com.msa.book.domain.model.vo.Classfication;
import com.msa.book.domain.model.vo.Location;
import com.msa.book.domain.model.vo.Source;
import com.msa.book.framwork.web.dto.BookInPutDTO;
import com.msa.book.framwork.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AddBookInputPort implements AddBookUsecase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO addBook(final BookInPutDTO bookInPutDTO) {
        Book book = Book.enterBook(
                bookInPutDTO.title(),
                bookInPutDTO.author(),
                bookInPutDTO.isbn(),
                bookInPutDTO.description(),
                bookInPutDTO.publicationDate(),
                Source.valueOf(bookInPutDTO.source()),
                Classfication.valueOf(bookInPutDTO.classfication()),
                Location.valueOf(bookInPutDTO.location()));

        Book save = bookOutputPort.save(book);
        return BookOutputDTO.mapToDTO(save);

    }
}
