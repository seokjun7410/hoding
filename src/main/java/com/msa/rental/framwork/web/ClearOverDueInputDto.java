package com.msa.rental.framwork.web;

public record ClearOverDueInputDto(
        String userId,
        String userNm,
        Integer point
) { }
