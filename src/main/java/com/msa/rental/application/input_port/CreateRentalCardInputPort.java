package com.msa.rental.application.input_port;

import com.msa.rental.application.output_port.RentalCardOutputPort;
import com.msa.rental.application.usecase.CreateRentalCardUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.framwork.web.dto.UserInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateRentalCardInputPort implements CreateRentalCardUsecase {
    private final RentalCardOutputPort rentalCardOutputPort;
    @Override
    public RentalCardOutputDto createRentalCard(final UserInputDto owner) {
        IDName idName = new IDName(owner.userId(), owner.userNm());
        RentalCard rentalCard = RentalCard.createRentalCard(idName);
        RentalCard save = rentalCardOutputPort.save(rentalCard);
        return RentalCardOutputDto.mapToDTO(save);
    }
}
