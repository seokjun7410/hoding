package chapter4;

import movie_reservation.domain.Money;

import java.time.Duration;
import java.util.List;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;
    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setRunningTime(final Duration runningTime) {
        this.runningTime = runningTime;
    }

    public void setFee(final Money fee) {
        this.fee = fee;
    }

    public void setDiscountConditions(final List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public void setMovieType(final MovieType movieType) {
        this.movieType = movieType;
    }

    public void setDiscountAmount(final Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setDiscountPercent(final double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public Money getFee() {
        return fee;
    }

    public List<DiscountCondition> getDiscountConditions() {
        return discountConditions;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
}
