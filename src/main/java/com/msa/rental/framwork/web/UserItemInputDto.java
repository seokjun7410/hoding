package com.msa.rental.framwork.web;

public record UserItemInputDto(
        String userId,
        String userNm,
        Integer itemId,
        String itemTile
) { }
