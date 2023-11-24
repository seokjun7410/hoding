package com.msa.rental.domain.model.vo;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Item {
    private Integer no;
    private String title;

    public static Item sample(){
        return new Item(10,"그대들은 어떻게 살 것인가");
    }

    public static void main(String[] args) {
        System.out.println(Item.sample());
    }
}
