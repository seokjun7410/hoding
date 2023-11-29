package com.msa.rental.application.usecase;

import com.msa.rental.framwork.web.dto.UserItemInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;

public interface ReturnItemUsecase {
    public RentalCardOutputDto returnItem(UserItemInputDto returnDto) throws Exception;
}
