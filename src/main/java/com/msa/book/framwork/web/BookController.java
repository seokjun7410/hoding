package com.msa.book.framwork.web;

import com.msa.book.application.usecase.AddBookUsecase;
import com.msa.book.application.usecase.InquiryUsecase;
import com.msa.book.framwork.web.dto.BookInPutDTO;
import com.msa.book.framwork.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final AddBookUsecase addBookUsecase;
    private final InquiryUsecase inquiryUsecase;

    //이용가능 이용 불가능은 카프카 이벤트를 통해 처리되는 도메인 로직

    @PostMapping("/book")
    public ResponseEntity<?> createBook(@RequestBody BookInPutDTO bookInPutDTO){
        BookOutputDTO bookOutputDTO = addBookUsecase.addBook(bookInPutDTO);
        return new ResponseEntity<>(bookOutputDTO, HttpStatus.CREATED);
    }

    @GetMapping("/book/{no}")
    public ResponseEntity<?> getBook(@PathVariable Long no){
        BookOutputDTO bookInfo = inquiryUsecase.getBookInfo(no);
        return bookInfo != null
                ? new ResponseEntity<>(bookInfo, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
