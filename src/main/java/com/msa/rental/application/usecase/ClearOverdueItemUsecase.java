package com.msa.rental.application.usecase;

import com.msa.rental.framwork.web.ClearOverDueInputDto;
import com.msa.rental.framwork.web.RentalResultOutputDto;
import com.msa.rental.framwork.web.dto.RentalCardOutputDto;

public interface ClearOverdueItemUsecase {
    RentalResultOutputDto clearOverdue(ClearOverDueInputDto clearOverDueInputDto) throws Exception;
}
