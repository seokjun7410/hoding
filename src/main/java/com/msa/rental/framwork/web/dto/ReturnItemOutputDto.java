package com.msa.rental.framwork.web.dto;

import com.msa.rental.domain.model.vo.ReturnItem;

import java.time.LocalDate;

public record ReturnItemOutputDto(
        Integer itemNo,
        String itemTitle,
        LocalDate returnDate
) {

    public static ReturnItemOutputDto mapToDTO(ReturnItem returnItem) {
        ReturnItemOutputDto rentItemOutputDTO = new ReturnItemOutputDto(
        returnItem.getRentalItem().getItem().getNo(),
        returnItem.getRentalItem().getItem().getTitle(),
        returnItem.getReturnDate()
        );
        return rentItemOutputDTO;
    }
}
