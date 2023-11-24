package com.msa.rental.domain.model.vo;

import com.msa.rental.domain.model.RentalItem;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ReturnItem {
    private RentalItem rentalItem;
    private LocalDate returnDate;

    public static ReturnItem createReturnItem(RentalItem rentalItem){
        return new ReturnItem(rentalItem,LocalDate.now());
    }

    public static ReturnItem sample() {
        return ReturnItem.createReturnItem(RentalItem.sample());
    }

    public static void main(String[] args) {
        System.out.println(ReturnItem.sample());
    }
}
