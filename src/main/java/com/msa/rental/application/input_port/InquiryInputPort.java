package com.msa.rental.application.input_port;

import com.msa.rental.application.output_port.RentalCardOutputPort;
import com.msa.rental.application.usecase.InquiryUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.framwork.web.dto.RentItemOutputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;
import com.msa.rental.framwork.web.dto.ReturnItemOutputDto;
import com.msa.rental.framwork.web.dto.UserInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public Optional<RentalCardOutputDto> getRentalCard(final UserInputDto userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.userId())
                .map(RentalCardOutputDto::mapToDTO);
    }

    @Override
    public Optional<List<RentItemOutputDto>> getAllRentItem(final UserInputDto userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.userId())
                .map(card-> card.getRentalItemList()
                        .stream()
                        .map(RentItemOutputDto::mapToDTO)
                        .collect(Collectors.toList()));

    }

    @Override
    public Optional<List<ReturnItemOutputDto>> getAllReturnItem(final UserInputDto userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.userId())
                .map(card->card.getReturnItemList()
                        .stream()
                        .map(ReturnItemOutputDto::mapToDTO)
                        .collect(Collectors.toList()));
    }
}
