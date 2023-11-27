package com.msa.rental.application.usecase;

import com.msa.rental.framwork.web.dto.RentItemOutputDto;
import com.msa.rental.framwork.web.dto.ReturnItemOutputDto;
import com.msa.rental.framwork.web.dto.UserInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;

import java.util.List;
import java.util.Optional;

public interface InquiryUsecase {
    public Optional<RentalCardOutputDto> getRentalCard(UserInputDto userInputDTO);
    public Optional<List<RentItemOutputDto>> getAllRentItem(UserInputDto userInputDTO);
    public Optional<List<ReturnItemOutputDto>> getAllReturnItem(UserInputDto userInputDTO);
}
