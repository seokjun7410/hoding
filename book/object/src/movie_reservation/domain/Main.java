package movie_reservation.domain;

import movie_reservation.domain.discount_condition.MorningCondition;
import movie_reservation.domain.discount_condition.PeriodCondition;
import movie_reservation.domain.discount_condition.SequenceCondition;
import movie_reservation.domain.discount_policy.AmountDiscountPolicy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        String title = "그대들은 어떻게 살 것인가";
         Money fee = Money.won(10000);
        AmountDiscountPolicy discountPolicy = new AmountDiscountPolicy(800,
                new MorningCondition(),
                new SequenceCondition(10),
                new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(18, 21)),
                new PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0)));
        Movie movie = new Movie(title, fee, discountPolicy);

        LocalDateTime whenScreening = LocalDateTime.of(LocalDate.of(2023, 11, 23), LocalTime.of(7, 0));
        Screening screening = new Screening(1, movie, whenScreening,90);

        Client client = new Client();

        Reservation reserve = screening.reserve(client, 2);

        String reserveMovieTitle = reserve.getScreening().getMovie().getTitle();
        LocalTime startTime = reserve.getScreening().getWhen().toLocalTime();
        LocalTime endTime = startTime.plusMinutes(reserve.getScreening().getTime());
        int audienceCount = reserve.getAudienceCount();
        BigInteger reserveMovieFee = reserve.getScreening().getMovie().getFee().getAmount().toBigInteger();
        BigInteger discountFee = reserve.getPaymentAmount().getAmount().toBigInteger();

        System.out.println("제목: "+reserveMovieTitle);
        System.out.println("시작시간: "+startTime);
        System.out.println("종료시간: "+endTime);
        System.out.println("인원: "+audienceCount);
        System.out.println("정가: "+reserveMovieFee);
        System.out.println("결제금액: "+discountFee);

    }
}
