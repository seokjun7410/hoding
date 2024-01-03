# 매사지 인터페이스
madeBy hoding  
reference [Object- 조영호](https://product.kyobobook.co.kr/detail/S000001766367)  
Date 23.01.03

## 객체를 지향하라
#### 코드 관점에서의 객체와 메시지 관계
```java
condition.isStatisfiedBy(screening)
수신자.메시지(인자)
```
#### 설계 관점에서의 객체와 메시지 관계
- 클라이언트가 isStatisFiedBy 라는 메시지를 발행한다.
- Condition 객체가 메시지를 수신한다.
- 메시지를 수행하기위해 객체 본인의 method를 수행한다.

## 디미터 법칙
#### 아래 조건을 만족하는 인스턴스에만 메시지를 전송할 것을 권장.
- this객체
- 메서드와 매개변수
- this속성
- this의 속성인 컬렉션 요소
- 메서드 내에서 생성된 지역객체
#### 잘못된 협력
```java
screening.getMovie().getDiscount(conditions)
```
- 객체 내부 구조에 갈한 결합을 유발한다.
- 협력의 경로를 제한하는 디미터 법칙을 참고하자.

## 인터페이스 의도를 드러내라
#### 잘못된 인터페이스
```java
AClassIsStatisfiedByPreoid()
BClassIsStatisfiedBySequence()
```
- 메소드 수준의 캡슐화를 위반했다.
- 어떻게 하는지는 숨기고 무엇을 하는지만 드러내라

#### 추상화를 통한 해결
- 클라이언트가 구체적인 요소에 상관하지 않도록 추상화를 진행한다.
```java
interface ABInterface{
    isSatisfiedBy();
}
```
## 묻지말고 시켜라
```java
public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
```
#### 문제점
```java
   audience.getBag().setTicket(ticket);
        ...
   audience.getBag().minusAmount(ticket.getFee());
```
- audience 내부에 포함된 bag에 대해 질문한 후 반환 된 결과를 이용해 bag의 상태를 변경한다.
- audience 객체에 대한 캡슐화 위반
- 해당 로직을 Audience에게 위임하여 내부 구조를 감춘다.
```java
public class Audience{
    public Long setTicket(Ticket ticket) {
        if (bag.hasInvitation()) {
            bag.setTicket(ticket);
            return 0L;
        } else {
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee());
            return ticket.getFee();
            }
        }
}
```
#### 디미터 법칙 위반사항
```java
     bag.setTicket(ticket);
     bag.minusAmount(ticket.getFee());
```
- 협력의 경로가 audince가 의존하는 객체의 메소드까지 이어진다.
#### 생각해보기
- audience 제작할 때를 상상해보면 bag이 어떤 메소드를 가지고있는지 알아야한다.
- audience가 bag을 사용해 조작하는 것은 본연의 책임을 벗어난 것인지 검토해야 한다.
- 실제로 위 로직은 티켓을 가방에 저장하는 로직이다
  - 자율성 관점에서도 bag이 스스로 해야한다.
  - '가방에 티켓을 넣어라' 메시지의 정보전문가는 bag이다
  - 저장할 때 특정한 로직이 추가된다면 audince의 변경을 야기한다.

## 디미터 법칙의 오해
- 디미터 법칙은 하나의 도트를 강제하는 규칙이 아니다.
```java
  IntStream.of(1,15,20,3,9).filter(x->x>10).distinct().count();
```
- 디미터 법칙을 위반하지 않았다.
- 디미터 법칙은 의존하는 객체보다 더 깊은 깊이로 들어가 내부구조를 노출하는지 유무가 핵심이다.

## 디미터 법칙으로 더 안좋은 결과를 가져오는 경우
### 첫번째 경우
```java
public class PreiodCondition implements DiscountCondition{
    public boolean isStaisfiedBy(Screening screening){
        return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) &&
                startTime.compareTo(screening.getStartTime().toLocaTime()) <= 0 &&
                endTime.compareTo(screening.getStartTime().toLocaTime()) >= 0;
    }
}
```
- screening에게 질의한 후 상태를 변경한다.
  - screening의 내부 상태를 가져와서 사용하기 때문에 캡슐화를 위반한다?!
  - 사실을 그렇지 않다.
#### 해설
- 캡슐화를 위반한다고 가정하고 Screening의 isDiscountable 메소드로 해당 로직을 옮겨보자.
```java
public class Screening{
    public boolean isDiscountable(DayOfWeek dayOfWeek, LocaTime startTime, LocalTime endTime){
        return whenScreened.getDayOfWeek().equals(dayOfWeek) &&
                startTime.compareTo(screening.getStartTime().toLocaTime()) <= 0 &&
                endTime.compareTo(screening.getStartTime().toLocaTime()) >= 0;
    }
}
```
```java
public class PeriodCondition implements DiscountCondition{
    public boolean isSatisfiedBy(Screenging screening){
        return screening.isDiscountable(dayOFWeek,startTime,endTime);
    }
}
```
- Screening이 기간에 따른 할인 조건을 판단하는 책임을 가지게된다.
  - Screening의 본질적인 책임은 영화를 예매하는 것이다.
    - 객체의 응집도가 낮아졌다.
  - Screening이 PreiodCondition의 인스턴스 변수를 인자로 받게 되어서 인스턴스 변수 목록에 대한 변경에 영향을 받는다
    - 객체의 결합도가 올라갔다.

### 두번째 경우
```java
for(Movie each : movies){
    total += each.getFee();
        }
```
- 모든 영화의 총 비용을 계산하는 로직에서 Movie에게 묻지않고도 movies 컬렉션에 포함된 전체 영화의 가격을 계산할 수 있는 방법이 있을까?
- 묻는 대상이 객체인지 자료구조인지 판단해야 한다.
- 자료구조라면 당연히 내부를 노출해야 하므로 디비터 법칙을 적용할 필요가 없다.
- Q. 새로운 자료구조 클래스를 탄생시켜 관리하면 안되는가? 
  - 자료구조에 관한 계산로직이 많아진다면 그럴 가치가 있다고 생각한다. 그렇지 않다면 객체간의 협력 복잡도를 늘릴 수 있는 여지가 있다.
  - 만들게 된다면 CollectionWrapping(일급 컬렉션)을 정의하는 것이 좋은 방법일 것 같다.

## 말하고자 하는 것
- 객체지향 설계에 법칙은 존재하지 않는다.
- 설계는 트레이드오프의 산물이다.   
#### ` 하나의 문제에 대해 여러 설계에 따른 트레이드 오프를 식별하고 현재 상황에 맞는 근거있는 설계를 채택하는 능력이 설계능력 이다 `