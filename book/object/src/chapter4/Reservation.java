package chapter4;

import movie_reservation.domain.Money;

public class Reservation {
    private Customer customer;
    private Screening screening;
    private Money fee;
    private int audienceCount;

    public Reservation(final Customer customer, final Screening screening, final Money fee, final int audienceCount) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public void setScreening(final Screening screening) {
        this.screening = screening;
    }

    public void setFee(final Money fee) {
        this.fee = fee;
    }

    public void setAudienceCount(final int audienceCount) {
        this.audienceCount = audienceCount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Screening getScreening() {
        return screening;
    }

    public Money getFee() {
        return fee;
    }

    public int getAudienceCount() {
        return audienceCount;
    }
}
