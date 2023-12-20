package movie_reservation.domain;

import movie_reservation.domain.discount_policy.AmountDiscountPolicy;
import movie_reservation.domain.discount_policy.DiscountPolicy;

public class Movie {
    private String title;
    private DiscountPolicy discountPolicy;
    private Money fee;

    public Movie(final String title, final Money fee, final AmountDiscountPolicy discountPolicy) {
        this.title = title;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money calculateMovieFee(final Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountFee(screening));
    }

    public String getTitle() {
        return this.title;
    }

    public Money getFee() {
        return this.fee;
    }
}
