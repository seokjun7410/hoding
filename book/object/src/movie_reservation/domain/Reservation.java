package movie_reservation.domain;

public class Reservation {
    private final Client client;
    private final Screening screening;
    private final Money paymentAmount;
    private final int audienceCount;

    public Reservation(final Client client, final Screening screening, final Money paymentAmount, final int audienceCount) {
        this.client = client;
        this.screening = screening;
        this.paymentAmount = paymentAmount;
        this.audienceCount = audienceCount;
    }

    public Client getClient() {
        return client;
    }

    public Screening getScreening() {
        return screening;
    }

    public Money getPaymentAmount() {
        return paymentAmount;
    }

    public int getAudienceCount() {
        return audienceCount;
    }
}
