package com.msa.book.application.usecase;

import com.msa.book.framwork.web.dto.BookOutputDTO;

public interface InquiryUsecase {

    BookOutputDTO getBookInfo(long bookNo);
}
