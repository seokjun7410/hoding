package movie_reservation.domain.discount_policy;

import movie_reservation.domain.Money;
import movie_reservation.domain.Screening;
import movie_reservation.domain.discount_condition.DiscountCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DiscountPolicy {

    private List<DiscountCondition> discountConditions = null;

    public DiscountPolicy(final DiscountCondition... conditions) {
        discountConditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountFee(final Screening screening) {

        for (DiscountCondition each : discountConditions) {
            if (each.isSatisfiedBy(screening))
                return getDiscountAmount(screening);
        }
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(final Screening screening);
}
