package com.msa.rental.application.output_port;

import com.msa.rental.domain.model.RentalCard;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalCardOutputPort {
    Optional<RentalCard> loadRentalCard(String userId);
    RentalCard save(RentalCard rentalCard);
}
