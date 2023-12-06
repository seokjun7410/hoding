# 3장: 역할 책임 협력
@madeBy. hoding  
@reference. [오브젝트-조영호](https://product.kyobobook.co.kr/detail/S000001766367?utm_source=google&utm_medium=cpc&utm_campaign=googleSearch&gad_source=1&gclid=CjwKCAiAmZGrBhAnEiwAo9qHiUph0COkC23yRjJtJ0DvacIN3ut2o79QiQ_mjy1WjcR4sggFt4cOUxoCzp4QAvD_BwE)  
@date. 23.11.28


# 역할 책임 협력
- 객체지향에서 가장 중요한 것은 역할,책임,협력이다.
- 역할,책임,협력을 고려하지 않은 채 상속,클래스,지연 바인딩과 같은 구현요소에 초점을 맞추면 유연하지 못한 코드를 낳는 원인이 된다.

### 영화 예매 시스템 돌아보기
- 다양한 객체들이 영화예매라는 usecase를 달성하기 위해 메시지를 주고받으며 상호작용(협럭) 한다.

# 협력
- 다른 객체에게 무언가 요청하는 것
- 메서드 : 메시지를 처리하는 방법
- 객체의 자율성을 보장하기 위해서는 필요한 정보와 정보에 기반한 행동을 클래스 안에 모아두어야 한다. => 응집
- 객체를 자율적으로 만드는 기본적인 방법은 캡슐화이다.
- 자신에게 할당된 정보 이외의 정보가 필요하면 다른객체에게 협력을 요청하자.

### 협력은 설계를 위한 문맥을 결정한다.
#### 객체가 가질 행동과 상태를 결정하는 기준은?
- 객체가 참여하고 있는 협력이 기준이 된다.
- #### ex: movie객체는 어떤 행동을 수행해야 할까?
  - movie의 관점에서 생각을 하면 movie.play()를 떠올릴 수 있다 -> x
  - 협력의 관점에서 생각해야한다. movie는 예매라는 협력에 참여하고 있다.
  - 협력에 의거하여 행동을 결정한다.
  - 행동은 상태를 결정한다
  - 협력 --(결정)--> 책임(=행동->메시지) --(결정)--> 상태

# 책임
- 책임은 행동이다.
- 책임은 하는 것과 아는 것으로 나뉜다.
- #### ex 영와 예메 협력
  - 메시지 -> 예매,요금 계산,할인 계산, 할인 조건 ... [하는 것 책임]
  - 예매 메시지에서 영화요금을 알아야 한다.[아는 것 책임]
  - 메시지 <= 책임
  
- #### Movie 에게 요금 계산 책임을 할당
  - 요금계산 메시지를 받는다 -> calculateFee() -> 할인정보는 모름으로 메시지 요청한다.
  - 영화(요금) 정보를 알아야한다. -> fee

### 책임할당 자세히
- 책임할당은 정보전문가에게 책임을 할당하자 (정보전문가 전략)
- 협력을 설계하는 출발점은 시스템의 책임
- 시스템 책임이 영화예매 일때
  - 영화 예매라는 책임을 달성하기위해 다른 객체에게 메시지를 보내야 한다.
    - 이로써 메시지 이름을 도출할 수 있다 -> 예매하라(reserve)
  - 메시지를 처리할 객체를 선택한다
    - 정보 전문가는 누구인가? -> 상영+요금을 알야아한다. -> '상영'도출
  - 요금에 대한 전문가는 '상영'이 아니다
    - '요금계산하라' 라는 메시지를 보내야한다.
    - 정보 전문가는 누구인가? -> 요금과 할인정보를 알고있어야 한다. -> '할이정책' 도출
  - ...

### 정리
- 시스템이 사용자에게 제공해야 하는 기능인 시스템 책임을 파악한다.
- 시스템 책임을 더 작은 책임으로 분할한다.
- 분할된 책임을 수행할 수 있는 적절한 객체 또는 역할을 찾아 책임을 할당한다.
- 객체가 책임을 수행하는 도중 다른 객체의 도움이 필요한 경우 이를 책임질 적절한 객체 또는 역할을 찾는다.
- 해당 객체 또는 역할에게 책임을 할당함으로써 두 객체가 협력하게 된다.

### 메시지가 객체를 선택하는 이유
- 최소 인터페이스를 가질 수 있고 추상적인 인터페이스가 도출된다는 장점
- 협력 ->행동(=책임->메시지) -> 정보전문가 도출(객체) 맟 상태 도출

# 역할과 협력
- 역할: 책임의 집합
- 역할에 책임을 할당한다고 생각하자.
- 역할을 통해 유연하고 재사용 가능한 협력을 얻을 수 있다.
- ex: 퍼센트 요금계산, 금액 요금계산 -> 객체관점 -> x
  - 요금계산 역할 -> 추상화 -> o
- 역할은 일종의 슬롯이다. 누구든지(객체) 역할의 책임을 다한다면 교체될 수 있다. -> 추상화

### 역할의 구현방법
- 추상클래스 -> 책임의 일부를 구현해논다. 
- 인터페이스 -> 책임에 대해서 정의(나열)만 해논다.
- 단 협력에 참여할 수 있는 대상은 후보(역할,객체)인데 한객체만 참여할 필요가 있다면 후보는 객체가 된다. 쉽게 말해 항상 역할을 고려할 필요는 없다는 것
- "객체가 무슨역할을 수행해야 되는가?" 라고 자문하자
  - 역할과 객체가 동일한지 아닌지로 후보를 결정하자
- 설계 초반에는 절절한 책임과 협력의 큰그림을 탐색하는 것이 우선되어야한다.
  - 이후 반복적으로 책임과 협력을 정제해 가면서 필요한 순간에 객체로부터 역할을 분리하는 전략을 취하자
  - 후보의 상세결정은 초반에 고려하지 않는다
  - 도메인 모델에 개념을 후보로 생각하고 책임할당한 뒤 이후에 정제하자

### 역할과 추상화
- 객체는 협력에 따라 역할이 달라질 수 있다.
- 역할은 협력에 참여하는 잠시동안에만 존재하는 개념이다
  - 문맥(협력)안에서 무엇을 하는지에 의해서만 정의된다.