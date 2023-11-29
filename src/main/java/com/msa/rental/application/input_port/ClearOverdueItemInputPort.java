package com.msa.rental.application.input_port;

import com.msa.rental.application.output_port.RentalCardOutputPort;
import com.msa.rental.application.usecase.ClearOverdueItemUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.framwork.web.dto.ClearOverDueInputDto;
import com.msa.rental.framwork.web.dto.RentalResultOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClearOverdueItemInputPort implements ClearOverdueItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;
    @Override
    public RentalResultOutputDto clearOverdue(final ClearOverDueInputDto clearOverDueInputDto) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(clearOverDueInputDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다"));

        rentalCard.makeAvailableRental(clearOverDueInputDto.point());
        return RentalResultOutputDto.mapToDTO(rentalCard);

    }
}
