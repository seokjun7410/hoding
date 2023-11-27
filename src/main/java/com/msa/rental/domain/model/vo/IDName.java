package com.msa.rental.domain.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@ToString
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
