package com.msa.rental.framwork.jpa_adpater;

import com.msa.rental.application.output_port.RentalCardOutputPort;
import com.msa.rental.domain.model.RentalCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalCardJpaAdapter implements RentalCardOutputPort {

    private final RentalCardRepository rentalCardRepository;
    @Override
    public Optional<RentalCard> loadRentalCard(final String userId) {
        return rentalCardRepository.findByMemberId(userId);

    }

    @Override
    public RentalCard save(final RentalCard rentalCard) {
        return rentalCardRepository.save(rentalCard);
    }
}
