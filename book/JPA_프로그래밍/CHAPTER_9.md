# 9장 값 타입
@madeBy. hoding  
@reference. [자바 ORM 표준 JPA 프로그래밍](https://product.kyobobook.co.kr/detail/S000000935744?utm_source=google&utm_medium=cpc&utm_campaign=googleSearch&gt_network=g&gt_keyword=&gt_target_id=aud-901091942354:dsa-435935280379&gt_campaign_id=9979905549&gt_adgroup_id=132556570510&gad_source=1&gclid=CjwKCAiA9ourBhAVEiwA3L5RFjIpwY7oZ_U-os5PWYVR4SsArOwzf1q6HIQfzUHm1RK0fd_OYxQgGRoCFK0QAvD_BwE)  
@Date. 23.01.08

---
## JPA의 데이터 타입
- 값 타입
  - 기본값 타입, 임베디드 값 타입, 컬렉션 값 타입
- Entity

## Embedded
- 장점
  - 값타입을 위한 메소드 정의 가능
  - 가독성
- 특징
  - 기본 생성자 필수
  - 컴포지션 관계
  - 값 타입을 포함하거나, enttiy를 참조할 수 있다.
  - 불변성을 지켜야 한다.
    - setter를 생성하지 않아야 불변성을 지킬 수 있다.
### 같은 Embedded 타입 재정의하기
```java
public class Test {
    @AttributeOverrids({
            @AttributeOverrid(name = "city", column = @Column(name = "COMPANY_CITY")),
            @AttributeOverrid(name = "street", column = @Column(name = "COMPANY_STREET")),
    })
    Address compannyAddress;
}
```

### 값타입 비교
- 동일성을 비교하지 않고 동등성을 비교해야하므로 equals 메소드를 재정의 해야 한다.
  - HashSet,HashMap에서도 정상작동하기 위해 hashCode도 재정의 해줘야 한다.

### 값 타입 컬렉션
```java
@ElementCollection
@CollectionTable(name = "FAVORITE_FOODS",
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
@Coulmn(name = "FOOD_NAME")
private Set<String> favoriteFoods = new HashSet<String>();

@ElementCollection
@CollectionTable(name = "ADDRESS",
        joinColumns = @JoinColumn(name = "MEMBER_ID"))
private List<Address> addressHistory = new ArrayList<Address>();

```
#### 값 타입 컬렉션 사용
- 임베디드 값 타입 저장 -> 본체 Entity과 함꼐 저장
- 컬렉션 값 타입 -> 개수당 1 추가 쿼리 생성
- 값 타입 컬렉션은 기본으로 영속성 전이 고아객체 제거기능을 가진다.
- 기본 값 타입 컬렉션을 수정할 때 삭제하고 다시 Insert한다.
  - 2개의 주소를 가지고 있는 회원의 주소를 수정하면 delete + Insert*2 개의 쿼리가 발생한다.  
#### `값 타입 컬렉션에 데이터가 많이 들어간다면 일대 다를 고려하자`
- 일대다 테이블에 cascadeType.All, orphanRemoval = true
- 모든 필드 복합키(선택)
  - null 방지와 중복제약이 가능하나 수정이 어렵다.
