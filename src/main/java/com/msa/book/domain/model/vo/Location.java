package com.msa.book.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Location {
    JEONGJA("정자"),
    PANGYO("판교");

    private final String name;
}
