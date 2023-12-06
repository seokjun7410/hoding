package chapter4;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private DiscountConditionType type;
    
    // 할인 조건에 따라 할인여부를 결정하는 로직이 달라진다.
    // 따라서 할인조건을 판단할 수 있는 인터페이스가 필요하다
    public boolean isDiscountable(DayOfWeek dayOfWeek,LocalTime time){
        if(type != DiscountConditionType.PERIOD){
            throw new RuntimeException();
        }

        //생략
        return true;
    }

    public boolean isDiscountable(int sequence){
        if(type != DiscountConditionType.SEQUENCE){
            throw new RuntimeException();
        }

        //생략
        return true;
    }

    public void setType(final DiscountConditionType type) {
        this.type = type;
    }

    public void setSequence(final int sequence) {
        this.sequence = sequence;
    }

    public void setDayOfWeek(final DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }

    public DiscountConditionType getType() {
        return type;
    }

    public int getSequence() {
        return sequence;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
