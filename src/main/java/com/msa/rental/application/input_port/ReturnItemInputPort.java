package com.msa.rental.application.input_port;

import com.msa.rental.application.output_port.RentalCardOutputPort;
import com.msa.rental.application.usecase.ReturnItemUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framwork.web.dto.UserItemInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@Transactional
@RequiredArgsConstructor
public class ReturnItemInputPort implements ReturnItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;
    @Override
    public RentalCardOutputDto returnItem(final UserItemInputDto returnDto) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(returnDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("카드가 없습니다."));
        Item item = new Item(returnDto.itemId(), returnDto.itemTitle());
        rentalCard.returnItem(item, LocalDate.now());
        return RentalCardOutputDto.mapToDTO(rentalCard);
    }
}
