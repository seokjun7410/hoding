package com.msa.book.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Classfication {
    ARTS("예술"),
    COMPUTER("컴퓨터"),
    LITERATURE("문학");

    private final String name;
}
