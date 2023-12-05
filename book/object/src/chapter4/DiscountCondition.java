package chapter4;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

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
