package com.msa.rental.application.usecase;

import com.msa.rental.framwork.web.dto.ClearOverDueInputDto;
import com.msa.rental.framwork.web.dto.RentalResultOutputDto;

public interface ClearOverdueItemUsecase {
    RentalResultOutputDto clearOverdue(ClearOverDueInputDto clearOverDueInputDto) throws Exception;
}
