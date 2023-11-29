package com.msa.rental.framwork.web.dto;

public record UserItemInputDto(
        String userId,
        String userNm,
        Integer itemId,
        String itemTitle
) { }
