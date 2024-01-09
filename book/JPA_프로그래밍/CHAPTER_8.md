# 7장 고급매핑
@madeBy. hoding  
@reference. [자바 ORM 표준 JPA 프로그래밍](https://product.kyobobook.co.kr/detail/S000000935744?utm_source=google&utm_medium=cpc&utm_campaign=googleSearch&gt_network=g&gt_keyword=&gt_target_id=aud-901091942354:dsa-435935280379&gt_campaign_id=9979905549&gt_adgroup_id=132556570510&gad_source=1&gclid=CjwKCAiA9ourBhAVEiwA3L5RFjIpwY7oZ_U-os5PWYVR4SsArOwzf1q6HIQfzUHm1RK0fd_OYxQgGRoCFK0QAvD_BwE)  
@Date. 23.01.08

---
## 프록시
- 문제 : 객체가 DB에 저장되어 있기 때문에 객체 그래프를 자요롭게 탐색할 수 없다.
- 해결 : 객체그래프가 탐색되는 시점에 DB에서 조회한다.
- 주의 : 자주 함께 사용되는 객체는 조인을 사용해서 함께 조회하는 것이 효과적이다.

### 지연로딩
```
team.getName();
```
- 팀의 속성에 접근하는 시점에 조회된다.
- 지연로딩을 위해 최초의 로딩에서는 프록시 객체를 반환 받는다.
#### 프록시 객체
- 실제 클래스를 상속받아서 만들어진다.
- 사용자 입장에서는 진째객체인지 프록시 객체인지 모른다. (캡슐화)
- 위임패턴을 사용하여 프로시 객체의 메소드를 호출하면 프록시는 실제 객체의 메소드를 호출한다.
- 실제 사용될 때 DB에서 조회하여 실제 엔티티를 생성하는 것을 프록시 객체 초기화라 한다.

#### 프록시 객체 특징
- 프록시 객체는 처음 사용할 때 한 번만 초기화된다.
- 프록시 객체를 초기화한다고 프록시 객체가 실제 엔티티로 바뀌는 것은 아니다.
- 프록시 객체는 원본 엔티티를 상속받은 객체이므로 타입 체크시 주의해야한다.
- 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 DB에 조회할 필요도 없으므로 실제 엔티티가 반환된다.
- 초기화는 영속성 컨텍스트의 도움을 받아야 가능하다. 따라서 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태의 프록시를 초기화하면 문제가 발생한다.

## 프록시와 식별자
```java
Team team = em.getRefernce(Team.class,"team1");
team.getId();
```
- 프록시 객체는 조회될 때 식별자 값을 보관하므로 식별자 조회에서는 초기화 되지 않는다.
- `@Access(AccessType.FIELD)`로 설정하면 JPA는 getId() 메소드가 id만 조회하는 메소드인지 다른 필드끼리 추가적인 연산을 하는 메소드인지 알지 못하므로 프록시 객체를 초기화한다.
  - 단 연관관계를 설정할때는 FIELD 접근 방식이어도 조회하지 않는다.

## 즉시로딩
- JPA는 즉시로딩을 최적화하기 위해 가능하면 조인쿼리를 사용한다.
  - 대상에 not null 조건이 없다면 외부 조인을 사용한다.
  - 내부조인이 성능과 최적화에서 더 유리하다. 즉, not null을 넣을 수 있는 상황이라면 반드시 조건을 활성화하자.
```java
@JoinColumn(name="TEAM_ID",nullable=false)
or
@ManyToOne(fetch=FetchType.EAGER, optional=false)
```

## 지연로딩
- 관계가 자주 함께 사용된다면 EAGER
- 관계가 가끔 함꼐 사용된다면 LAZY  
`권장 : Lazy로 개발 후 빈도를 확인하여 EAGER로 최적하 합시다.`

## 컬렉션과 EAGER 주의점
- 컬렉션은 가능한 즉시로딩을 하지 않는다.
  - 예를 들어 2개이상 일대다 조인으로 인해 너무 많은 데이터가 반환될 수 있고 JPA는 이 반환된 결과를 메모리에서 필터링한다.
- 항상 외부조인이 사용된다.

### 정리
```java
~One 
        => null(O) : 외부조인
        => null(X) : 내부조인
~Many
        => 항상 외부조인
```

## 영속성 전이
- 부모저장시 자식 저장이 가능
- 정확히는 영속성 전이가 설정된 관계 Entity에 대해 주체의 영속성을 전이시켜 주체가 저장될 때 변경감지로 함께 저장된다.
- CascadeType.persist

#### Remove
- 부모가 삭제되면 자식도 삭제된다.

#### 고아객체
- 부모와 연관관계가 끊어진 자식
- orphanRemoval 적용시 고아객체를 자동으로 삭제한다.
  - 다른 곳에서 소유하고 있으면 안되기 때문에 OneToOne,oneToMany 관계에서만 사용 가능하다.
- 부모객체가 삭제되도 고아객체가 되기 때문에 기능적으로는 CascadeType.remove의 확장판이다.
- 만약 CascadeType.remove 와 orphanRemoval을 같이 사용한다면 부모 Entity스스로 생명주기를 관리한다는 의미이다.
- DDD Aggregate Root 개념 구현할 때 편리하다.

