package chapter4;

import movie_reservation.domain.Money;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreend;

    public Money calculateFee(int audienceCount){
        switch (movie.getMovieType()){
            case AMOUNT_DISCOUNT:
                if(movie.isDiscountable(whenScreend,sequence)){
                    return movie.calculatePercentDiscountedFee().multiply(audienceCount);
                }
                break;
            case PERCENT_DISCOUNT:return null; //생략
            case NONE_DISCOUNT: return null; //생략
        }

        return movie.calculateNoneDiscountedFee();
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }

    public void setSequence(final int sequence) {
        this.sequence = sequence;
    }

    public void setWhenScreend(final LocalDateTime whenScreend) {
        this.whenScreend = whenScreend;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSequence() {
        return sequence;
    }

    public LocalDateTime getWhenScreend() {
        return whenScreend;
    }
}
