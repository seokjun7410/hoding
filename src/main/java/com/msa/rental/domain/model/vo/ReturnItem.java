package com.msa.rental.domain.model.vo;

import com.msa.rental.domain.model.RentalItem;
import lombok.*;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Embeddable
@Access(AccessType.FIELD)
public class ReturnItem {
    @Embedded
    private RentalItem rentalItem;
    private LocalDate returnedDate;

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
