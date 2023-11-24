package com.msa.rental.domain.model;


import com.msa.rental.domain.model.vo.Item;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class RentalItem {
    private Item item;
    private LocalDate rentDate;
    private boolean overdued; //연체
    private LocalDate returnDate;

    public static RentalItem createRentalItem(Item item){
        return RentalItem.builder()
                .item(item)
                .rentDate(LocalDate.now())
                .overdued(false)
                .returnDate(LocalDate.now().plusDays(14))
                .build();
    }

    public static RentalItem sample(){
        return RentalItem.createRentalItem(Item.sample());
    }

    public static void main(String[] args) {
        System.out.println(RentalItem.sample());
    }

    public void setOverDued(final boolean overdued) {
        this.overdued = overdued;
    }

    public void setReturnDate(final LocalDate localDate) {
        this.returnDate = localDate;
    }
}
