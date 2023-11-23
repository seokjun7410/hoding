package movie_reservation.domain.discount_condition;

import movie_reservation.domain.Screening;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MorningCondition implements DiscountCondition {
    public MorningCondition() {
    }

    @Override
    public boolean isSatisfiedBy(final Screening screening) {
        LocalTime startTime = screening.getWhen().toLocalTime();
        return startTime.isAfter(LocalTime.of(6,0)) && startTime.isBefore(LocalTime.of(9,0));
    }
}
