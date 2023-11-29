package com.msa.rental.domain.model.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RentAvailableStatus {
    RENT_AVAILABLE("대여 가능"),
    RENT_UNAVAILABLE("대여 불가");

    private final String name;

}
