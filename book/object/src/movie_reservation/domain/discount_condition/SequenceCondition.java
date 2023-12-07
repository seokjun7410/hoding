package movie_reservation.domain.discount_condition;

import movie_reservation.domain.Screening;

public class SequenceCondition implements DiscountCondition{

    private int sequence;

    public SequenceCondition(final int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(final Screening screening) {
        return screening.sequence == this.sequence;
    }
}
