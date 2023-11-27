package com.msa.rental.application.usecase;

import com.msa.rental.framwork.web.UserItemInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;

public interface RentItemUsecase {
    public RentalCardOutputDto rentItem(UserItemInputDto rental) throws Exception;
}
