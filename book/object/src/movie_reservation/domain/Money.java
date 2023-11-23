package movie_reservation.domain;

import java.math.BigDecimal;

public final class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);
    private final BigDecimal amount;

    public Money(final BigDecimal amount) {
        this.amount = amount;
    }

    public static Money won(final int discountAmount) {
        return new Money(BigDecimal.valueOf(discountAmount));
    }



    public BigDecimal getAmount() {
        return amount;
    }

    public Money minus(final Money money) {
         return new Money(this.amount.subtract(money.amount));
    }

    public Money multiply(final int count) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(count)));
    }
}
