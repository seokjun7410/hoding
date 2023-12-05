package chapter4;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreend;

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
