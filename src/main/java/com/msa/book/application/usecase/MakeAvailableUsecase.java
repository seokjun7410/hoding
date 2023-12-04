package com.msa.book.application.usecase;

import com.msa.book.framwork.web.dto.BookOutputDTO;

public interface MakeAvailableUsecase {

    BookOutputDTO available(Long bookNo);
}
