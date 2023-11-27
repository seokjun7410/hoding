package com.msa.rental.framwork.web.dto;

import com.msa.rental.domain.model.RentalItem;

import java.time.LocalDate;

public record RentItemOutputDto(
        Integer itemNo,
        String itemtitle,
        LocalDate rentDate,
        boolean overdued,
        LocalDate overdueDate//반납 예정
) {

    public static RentItemOutputDto mapToDTO(RentalItem rentItem) {
        RentItemOutputDto rentItemOutputDTO = new RentItemOutputDto(
                rentItem.getItem().getNo(),
                rentItem.getItem().getTitle(),
                rentItem.getRentDate(),
                rentItem.isOverdued(),
                rentItem.getReturnDate()
        );
        return rentItemOutputDTO;
    }
}
