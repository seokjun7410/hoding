package com.msa.book.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BootStatus {
    ENTERED("입고"),
    AVAILABLE("이용 가능"),
    UNAVAILABLE("이용 불가능");

    private final String name;
}
