package com.msa.rental.application.input_port;

import com.msa.rental.application.output_port.RentalCardOutputPort;
import com.msa.rental.application.usecase.RentItemUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framwork.web.dto.UserItemInputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentItemInputPort implements RentItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDto rentItem(final UserItemInputDto rental) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.userId())
                .orElseGet(()-> RentalCard.createRentalCard(new IDName(rental.userId(),rental.userNm())));

        Item newItem = new Item(rental.itemId(), rental.itemTitle());
        rentalCard.rentItem(newItem);
        //RentalCard save = rentalCardOutputPort.save(rentalCard); JPA 변경감지를 예상하고 주석처리 하는 것은 일종의 기술오염
        return RentalCardOutputDto.mapToDTO(rentalCard);
    }
}
