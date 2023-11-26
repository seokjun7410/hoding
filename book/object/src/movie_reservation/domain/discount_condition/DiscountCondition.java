package movie_reservation.domain.discount_condition;

import movie_reservation.domain.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(final Screening screening);
}
