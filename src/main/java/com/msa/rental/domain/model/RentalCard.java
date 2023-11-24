package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static com.msa.rental.domain.model.vo.RentAvailableStatus.RENT_AVAILABLE;
import static com.msa.rental.domain.model.vo.RentAvailableStatus.RENT_UNAVAILABLE;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class RentalCard {
    private final int OVERDUE_POINT_PER_DAY = 10; //연체 1일당 10포인트가 부여된다.

    private RentalCardNo rentalCardNo;
    private IDName member;
    private RentAvailableStatus rentAvailableStatus;
    private LateFee lateFee;
    private List<RentalItem> rentalItemList;
    private List<ReturnItem> returnItemList;


    /** ------ 비지니스 로직 ------ **/
    /**
     * 대여카드 생성
     **/
    public static RentalCard createRentalCard(final IDName creator) {
        return new RentalCard(
                RentalCardNo.createRentalCardNo(),
                creator,
                RentAvailableStatus.RENT_AVAILABLE,
                LateFee.createLateFee(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    /**
     * 대여 처리
     **/
    public RentalCard rentItem(final Item item) {
        checkRentalAvailable();
        this.addRentalItem(RentalItem.createRentalItem(item));
        return this;
    }

    /**
     * 반납 처리
     **/
    public RentalCard returnItem(final Item item, final LocalDate returnDate) {
        //반납시 연체료를 계산한다.
        RentalItem rentalItem = this.rentalItemList.stream()
                .filter(f -> f.getItem().equals(item))
                .findFirst()
                .orElseThrow(this::DOES_NOT_MATCH_ITEM_EXCEPTION);

        calculateLateFee(rentalItem, returnDate);
        this.addReturnItem(ReturnItem.createReturnItem(rentalItem));
        this.removeRentalItem(rentalItem);
        return this;
    }

    /** 연체 처리 **/
    public RentalCard overdueItem(Item item){
        //원래 배치나 스케줄러 처리되어야함 예제적 허용
        RentalItem rentalItem = this.rentalItemList.stream()
                .filter(f -> f.getItem().equals(item))
                .findFirst()
                .orElseThrow(this::DOES_NOT_MATCH_ITEM_EXCEPTION);

        rentalItem.setOverDued(true);
        rentAvailableStatus = RENT_UNAVAILABLE;

        //현재 억지로 만들기 필요없는 코드
        rentalItem.setReturnDate(LocalDate.now().minusDays(1));
        return this;
    }

    /** 정지 해제 처리 **/
    public long makeAvailableRental(long point) throws Exception {
        if(this.rentalItemList.size() != 0)
            throw new IllegalArgumentException("모든 도서가 반납되어야 정지를 해제할 수 있습니다.");

        if(this.getLateFee().getPoint() != point) //같아야 반납할 수 있도록 요구사항 단축: 예제적 허용
            throw new IllegalArgumentException("해당 포인트로 연체를 해제할 수 없습니다.");

        this.setLateFee(lateFee.removePoint(point));
        if (this.getLateFee().getPoint() ==0)
            this.rentAvailableStatus = RENT_AVAILABLE;

        return this.getLateFee().getPoint();
    }

    private void setLateFee(final LateFee lateFee) {
        this.lateFee = lateFee;
    }


    /** ------ 비지니스 로직 ------ **/
    /** ------ 비지니스 제약 조건 ------ **/

    /**
     * 대여 제약조건
     **/
    private void checkRentalAvailable() {
        // 1권이라도 연체되면 사용자는 대여 불가 상태가 된다.
        if (this.rentAvailableStatus.equals(RENT_UNAVAILABLE))
            throw new IllegalArgumentException("대여 불가 상태입니다.");
        // 1인당 5권 이내
        if (this.rentalItemList.size() >= 5)
            throw new IllegalArgumentException("이미 5권을 대여했습니다.");
    }

    /** ------ 비지니스 제약 조건 ------ **/
    /** ------ 비지니스 서브 메소드 ------ **/

    private void calculateLateFee(final RentalItem rentalItem, final LocalDate returnDate) {
        if(returnDate.isAfter(rentalItem.getReturnDate())) { //늦게 반납했을 경우
            long point = (long) Period.between(rentalItem.getReturnDate(), returnDate).getDays() * OVERDUE_POINT_PER_DAY;
            this.lateFee.addPoint(point);
        }
    }

    private void addRentalItem(final RentalItem rentalItem) {
        this.rentalItemList.add(rentalItem);
    }

    private void removeRentalItem(final RentalItem rentalItem) {
        this.rentalItemList.remove(rentalItem);
    }

    private void addReturnItem(final ReturnItem returnItem) {
        this.returnItemList.add(returnItem);
    }

    private void removeReturnItem(final ReturnItem returnItem) {
        this.returnItemList.remove(returnItem);
    }

    /** ------ 비지니스 서브 메소드 ------ **/
    /** ------ 예외 -> cutomExcpetion 생략 ------ **/
    private  IllegalArgumentException DOES_NOT_MATCH_ITEM_EXCEPTION() {
        return new IllegalArgumentException("대여하지 않은 항목 입니다.");
    }
    /** ------ 예외 -> cutomExcpetion 생략 ------ **/
    /** ------ samp for test method ------ **/
    public static RentalCard sample() {
        return new RentalCard(
                RentalCardNo.sample(),
                IDName.sample(),
                RentAvailableStatus.RENT_AVAILABLE,
                LateFee.createLateFee(),
                List.of(RentalItem.sample()),
                List.of(ReturnItem.sample())
        );
    }

    public static void main(String[] args) {
        System.out.println(sample());
    }
    /** ------ samp for test method ------ **/

}
