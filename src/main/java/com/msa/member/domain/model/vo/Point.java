package com.msa.member.domain.model.vo;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.AccessType;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Access(AccessType.FIELD)
@ToString
public class Point {
    public long pointValue ;

    private void setPointValue(long pointValue) {
        this.pointValue = pointValue;
    }

    public long addPoint(long point){
        this.setPointValue(this.pointValue + point);
        return this.pointValue;
    }

    public long removePoint(long point) throws Exception {
        if(point > this.pointValue) {
            throw new Exception("기존 보유 Point보다 적어 삭제할 수 없습니다.");
        }
        this.setPointValue(this.pointValue - point);
        return this.pointValue;
    }

    public static Point createPoint()
    {
        return new Point(0L);
    }
    public static Point sample(){
        return new Point(10L);
    }
}
