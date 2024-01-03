# 책임이_부여된_데이터중심설계_문제점
madeBy hoding  
reference [Object- 조영호](https://product.kyobobook.co.kr/detail/S000001766367)  
Date 23.01.03

# 책임 할당을 위한 GRAGP 패턴
- 책임을 할당할 때 지침으로 삼을 수 있는 원칙들

## 도메인 개념에서 출발하기
- 설계를 시작하기전 도메인에 대한 개략적인 모습을 그려보자
#### 책임 할당하기 과정 예시
- 메시지 식별 : 예매하라
  - 수신할 객체는 누구인가? -> 정보전문가(상영)
    - 정보와 데이터는 다르다. 알고 있다고 해서 저장하고 있을 필요는 없다.
- 상영에서 스스로 처리할 수 있는 부분과 그렇지 않은 부분은 식별한다.
  - 스스로 할 수 없는 것 = 예매가격 계산 -> 영화가격을 모른다. -> 새로운 메시지 식별 : 영화가격을 계산하라 -> 정보전문가(영화) - .. -> 할인여부 판단 메시지 식별
  - 스스로 할 수 있는 것 = 예약정보 반환

#### 잘못된 설계인지 판단하는 방법
- 상영 도메인에서 영화와 할인조건에 직접적으로 의존하는 설계는 무엇이 문제인가?
- 결합도 관점
  - 도메인에서 이미 영화는 할인조건과 결합되어 있기 때문에 불필요한 결합이다.
- 응집도 관점
  - 위 설계에서 상영은 영화가 영화가 할인여부를 필요로한다는 사실을 알고 있어야한다.
  - 즉 할인여부 판단은 '가격계산해라'라는 메시지에 포함된 작은 메시지로 판단하는 것이 옳다.

#### 객체 생성 책임을 판단하는 방법
- 참조,기록,긴밀하게 사용하는지, 초기화하는데 필요한 정보를가지고 있는지를 기준으로 참고하면 좋다.

#### 클래스 분리 원칙
- 변경의 이유에 따라 클래스를 분리하는 것이 좋다.
```java
public class DiscountCondition {
    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    /** 새로운 할인조건인 추가될 때 변경될 위험이 있다. **/
    public boolean isSatisfiedBy(Screening screening) {
        if (type == DiscountConditionType.PERIOD) {
            return isSatisfiedByPeriod(screening);
        }

        return isSatisfiedBySequence(screening);
    }
    /** 기간 판단조건 편경에 따라 같이 수정될 위험이 있다. **/
    private boolean isSatisfiedByPeriod(Screening screening) {
        return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
                startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                endTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0;
    }

    /** 순번 판단조건 변경에 따라 같이 수정될 위험이 있다.**/
    private boolean isSatisfiedBySequence(Screening screening) {
        return sequence == screening.getSequence();
    }
}
```
- 해당 클래스는 변경에 이유가 1개 이상으로 판된되어 부적절한 설계로 볼 수 있다.
- 클래스를 변경의 이유가 한가지가 되도록 구성하면 유지보수에 용이하다.

#### 변경의 이융 쉽게 판단할 수 있는 질문 2가지
- 인스턴스 변수가 초기화 되는 시점에 한번에 초기화가 되는가?
- 메서드들이 객체의 모든 속성을 사용한가?

