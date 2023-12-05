package chapter4;

import movie_reservation.domain.Money;

public class ReservationAgency {

    public Reservation reserve(Screening screening, Customer customer, int audienceCount){
        Movie movie = screening.getMovie();

        boolean discountable = false;
        for (DiscountCondition condition : movie.getDiscountConditions()) {
            if(condition.getType().equals(DiscountConditionType.PERIOD)){
                //생략
            }else{//DiscountConditionType.SEQUENCE 알경우
                //생략
            }

            if(discountable){
                break;
            }
        }

        Money fee;
        if(discountable){
            Money dicountAmount = Money.ZERO;
            switch (movie.getMovieType()){ //생략
                case AMOUNT_DISCOUNT : break;
                case PERCENT_DISCOUNT:  break;
                case NONE_DISCOUNT: break;
            }

            fee = movie.getFee().minus(dicountAmount);
        }else {
            fee = movie.getFee();
        }

        return new Reservation(customer,screening,fee,audienceCount);
    }
}
