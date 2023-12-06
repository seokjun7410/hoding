package movie_reservation.domain.discount_policy;

import movie_reservation.domain.Money;
import movie_reservation.domain.Screening;
import movie_reservation.domain.discount_condition.DiscountCondition;

public class AmountDiscountPolicy extends DiscountPolicy{

    private Money discountAmount;

    public AmountDiscountPolicy(final int discountAmount, DiscountCondition ...conditions) {
        super(conditions);
        this.discountAmount = Money.won(discountAmount);
    }

    @Override
    protected Money getDiscountAmount(final Screening screening) {
        return discountAmount;
    }
}
