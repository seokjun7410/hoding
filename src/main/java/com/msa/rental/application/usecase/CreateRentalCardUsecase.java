package com.msa.rental.application.usecase;

import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.framwork.web.UserInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;

public interface CreateRentalCardUsecase {
    public RentalCardOutputDto createRentalCard(UserInputDto userInputDto);
}
