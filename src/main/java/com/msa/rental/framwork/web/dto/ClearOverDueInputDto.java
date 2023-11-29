package com.msa.rental.framwork.web.dto;

public record ClearOverDueInputDto(
        String userId,
        String userNm,
        Integer point
) { }
