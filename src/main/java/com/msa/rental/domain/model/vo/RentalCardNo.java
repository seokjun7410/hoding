package com.msa.rental.domain.model.vo;

//rentalCard 의 식별자가 되는 VO

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Embeddable
public class RentalCardNo implements Serializable {
    private String no;

    public static RentalCardNo createRentalCardNo(){
        String id = generateId();
        return new RentalCardNo(id);
    }

    private static String generateId() {
        UUID uuid = UUID.randomUUID();
        String year = String.valueOf(LocalDate.now().getYear());

        return year + '-' + uuid;
    }

    //sample code for test 운영할때 지우기
    public static RentalCardNo sample(){
        return RentalCardNo.createRentalCardNo();
    }

    public static void main(String[] args) {
        System.out.println(RentalCardNo.sample());
    }
}
