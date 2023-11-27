package com.msa.rental.framwork.web.dto;

import com.msa.rental.domain.model.RentalCard;

public record RentalResultOutputDto(
        String userId,
        String userNm,
        Integer rentedCount,
        long totalLateFee

) {
    public static RentalResultOutputDto mapToDTO(RentalCard rental){
        RentalResultOutputDto rentDTO = new RentalResultOutputDto(
        rental.getMember().getId(),
        rental.getMember().getName(),
        rental.getRentalItemList().size(),
        rental.getLateFee().getPoint()
        );
        return rentDTO;
    }
}
