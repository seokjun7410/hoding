package com.msa.rental.application.usecase;

import com.msa.rental.framwork.web.UserItemInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;

public interface OvuerdueItemUsercase {
    public RentalCardOutputDto overDueItem(UserItemInputDto rental) throws Exception;
}
