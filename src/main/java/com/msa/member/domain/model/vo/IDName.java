package com.msa.member.domain.model.vo;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.AccessType;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Access(AccessType.FIELD)
@ToString
public class IDName {
    private String id;
    private String name;

    public static IDName sample(){
        return new IDName("can","name");
    }

    public static void main(String[] args) {
        System.out.println(sample());
    }
}
