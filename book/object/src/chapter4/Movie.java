package chapter4;

import movie_reservation.domain.Money;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;
    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    //오퍼레이션 생각하기
    //소유한 List<DiscountCondition>중 가능한 할인이 있는지 확인해야한다.
    public boolean isDiscountable(LocalDateTime whenScreened, int sequence){
        for (DiscountCondition discountCondition : discountConditions) {
            if(discountCondition.getType() == DiscountConditionType.PERIOD){
                discountCondition.isDiscountable(whenScreened.getDayOfWeek(),whenScreened.toLocalTime());
                        return true;
            }else{
                if(discountCondition.isDiscountable(sequence))
                    return true;
            }
        }
        return  false;
    }


    //오퍼레이션 생각하기
    //할인 정책에 따라 영화요금 계산방식이 결정된다.
    //따라서 할인정책별로 요금을 계산하는 메소드를 구현한다.
    public Money calculateAmountDiscountedFee(){
        if(movieType!= MovieType.AMOUNT_DISCOUNT) {
            throw new RuntimeException();
        }
        //생략
        return Money.ZERO;
    }

    public Money calculatePercentDiscountedFee(){
        if(movieType!= MovieType.PERCENT_DISCOUNT) {
            throw new RuntimeException();
        }
        //생략
        return Money.ZERO;
    }

    public Money calculateNoneDiscountedFee(){
        if(movieType!= MovieType.NONE_DISCOUNT) {
            throw new RuntimeException();
        }
        //생략
        return Money.ZERO;
    }


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
