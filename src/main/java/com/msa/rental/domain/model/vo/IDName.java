package com.msa.rental.domain.model.vo;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Embeddable
public class IDName {
    private String id;
    private String name;

   public static IDName sample(){
       return new IDName("scant","han");
   }

    public static void main(String[] args) {
        System.out.println(IDName.sample());
    }
}
