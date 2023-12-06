package movie_reservation.domain;

import java.time.LocalDateTime;

/**
 * @Class 상영
 */
public class Screening {
    public int sequence;
    private Movie movie;
    private LocalDateTime when;
    private int time;

    public Screening(final int sequence, final Movie movie, final LocalDateTime when, final int time) {
        this.sequence = sequence;
        this.movie = movie;
        this.when = when;
        this.time = time;
    }

    public Reservation reserve(Client client, int audienceCount){
        Money paymentAmount = calculatePaymentFee(audienceCount);
        return new Reservation(client,this,paymentAmount,audienceCount);
    }

    private Money calculatePaymentFee(int audienceCount) {
        return movie.calculateMovieFee(this).multiply(audienceCount); //구매금액은 같은 Movie여도 상영마다 다르게 책정될 수 있다.
    }
    public LocalDateTime getWhen() {
        return when;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public int getTime() {
        return this.time;
    }
}
