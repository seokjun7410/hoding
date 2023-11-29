package com.msa.rental.application.usecase;

import com.msa.rental.framwork.web.dto.UserItemInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;

public interface OverdueItemUsercase {
    public RentalCardOutputDto overDueItem(UserItemInputDto rental) throws Exception;
}
