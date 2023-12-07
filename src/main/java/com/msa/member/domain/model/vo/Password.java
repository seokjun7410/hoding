package com.msa.member.domain.model.vo;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.AccessType;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Access(AccessType.FIELD)
@ToString
public class Password {
    public String presentPMD;
    private String pastPMD;

    public static Password sampe(){
        return new Password("12345","abcde");
    }

}
