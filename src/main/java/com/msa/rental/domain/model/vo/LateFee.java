package com.msa.rental.domain.model.vo;

import lombok.*;

import javax.persistence.Embeddable;

//포인트 관리하는 비즈니스 로직
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Embeddable
public class LateFee {
    private long point;

    public static LateFee createLateFee(){
        return new LateFee(0);
    }

    public LateFee addPoint(long point){
        return new LateFee(this.point + point);
    }

    public LateFee removePoint(long point) throws Exception {
        if(!canRemove(point))
            throw new Exception("보유한 포인트보다 커서 삭제할 수 없습니다.");
        return new LateFee(this.point - point);
    }

    private boolean canRemove(final long point) {
        return this.point >= point;
    }

    public static LateFee sample(){
        return new LateFee(100);
    }


    public static void main(String[] args) {
        System.out.println(LateFee.sample());
    }
}
