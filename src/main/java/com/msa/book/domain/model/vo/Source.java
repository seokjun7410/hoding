package com.msa.book.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Source {
    DONATION("후원"),
    SUPPLY("보급");

    private final String name;
}
