# 6장 다양한 연관관계 매핑
@madeBy. hoding  
@reference. [자바 ORM 표준 JPA 프로그래밍](https://product.kyobobook.co.kr/detail/S000000935744?utm_source=google&utm_medium=cpc&utm_campaign=googleSearch&gt_network=g&gt_keyword=&gt_target_id=aud-901091942354:dsa-435935280379&gt_campaign_id=9979905549&gt_adgroup_id=132556570510&gad_source=1&gclid=CjwKCAiA9ourBhAVEiwA3L5RFjIpwY7oZ_U-os5PWYVR4SsArOwzf1q6HIQfzUHm1RK0fd_OYxQgGRoCFK0QAvD_BwE)  
@Date. 23.12.04


## 연관관계 매핑시 고려해야할 3가지
- 다중성 
- 단방향, 양방향
- 연관관계 주인

#### 양방향
- 양방향은 외래키가 있는 쪽이 연관관계 주인이다.
  - 연관관계 편의 메소드를 작성하자
- 양방향 연관관계는 항상 서로를 참조하고 있어야 한다.

## 일대다 매핑
- 일대다 단방향 매핑은 외래키가 다른테이블에 있다.
  - 연관관계 설정시 다른테이블 외래키 update 쿼리가 나간다.
  - 일대다 양방향 매핑을 이용하자.

#### ManyToOne에는 mappedBy 속성이 없다.

## 일대일 매핑
- 어느 곳이나 외래킬 주인이 될수있다. 
- 대상 테이블에 외래키를 설정할경우 프록시 한계로 lazy로딩이 되지 않는다.
  - 성능상 이점을 챙기려서 주테이블에 외래키 주인을 설정하자.

## 다대다 매핑
- 일대다 다대다로 풀어서 설정하자.
  - 연결테이블에 추가적인 정보가 필요한 경우가 많음으로.
- 식별관계로 하면 orm 매핑이 복잡해진다. 가능하면 비식별관계로 구현하자.