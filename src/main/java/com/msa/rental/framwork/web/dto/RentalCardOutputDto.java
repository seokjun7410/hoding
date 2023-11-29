package com.msa.rental.framwork.web.dto;

import com.msa.rental.domain.model.RentalCard;


public record RentalCardOutputDto(
        String rentalCardId,
        String memberId,
        String memberName,
        String rentStatus,
        Long totalLateFee,
        Long totalRentalCnt,
        Long totalReturnCnt,
        Long totalOverduedCnt
) {

    public static RentalCardOutputDto mapToDTO(RentalCard rental) {
        RentalCardOutputDto rentDTO = new RentalCardOutputDto(
                rental.getRentalCardNo().getNo().toString(),
                rental.getMember().getId().toString(),
                rental.getMember().getName(),
                rental.getRentAvailableStatus().toString(),
                rental.getRentalItemList().stream().count(),
                rental.getRentalItemList().stream().count(),
                rental.getRentalItemList().stream().filter(i -> i.isOverdued()).count(),
                0L
        );
        return rentDTO;
    }
}
