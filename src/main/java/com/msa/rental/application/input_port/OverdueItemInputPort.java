package com.msa.rental.application.input_port;

import com.msa.rental.application.output_port.RentalCardOutputPort;
import com.msa.rental.application.usecase.OverdueItemUsercase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framwork.web.dto.UserItemInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OverdueItemInputPort implements OverdueItemUsercase {

    private final RentalCardOutputPort rentalCardOutputPort;
    @Override
    public RentalCardOutputDto overDueItem(final UserItemInputDto rental) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.userId())
                .orElseThrow(() -> new IllegalArgumentException("카드가 존재하지 않습니다."));

        rentalCard.overdueItem(new Item(rental.itemId(), rental.itemTitle()));

        return RentalCardOutputDto.mapToDTO(rentalCard);
    }
}
