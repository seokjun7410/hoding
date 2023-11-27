# 2장 
@madeBy. hoding  
@reference. [MYSQL을 더 빠르게, 성능 최적화 선택과 집중 - 대니얼 니히터](https://product.kyobobook.co.kr/detail/S000210684181)  
@Date. 23.11.27

### InnoDB
- B-tree 구조로 이루어져 있다.
- pk 조회는 매우 빠르고 효율적이다.
### 세컨더리 인덱스
- 세컨더리 인덱스도 B-tree 구조다
- 리프노드에 pk 값을 가진다.
- 리프노드를 찾고 해당노드에 pk를 찾아서 전체조회를한다. (2번의 조회)

### 테이블 접근방벙
- 인덱스 조회 : 인덱스를 통해 접근
- 인덱스 스캔 : 인덱스 조회가 불가능 할때 모든 행을 읽는데, 이 때 세컨더리 인덱스로 행읽기를 시도하는 것
  - 풀 인덱스 스캔 : index를 사용해 전체 행 읽기, index와 orderBy가 일치하면 정렬을 피할 수 있는 장점이 있음.
  - 인덱스-only 스캔 : 커버링 index가 필요하며 풀 인덱스 스캔보다 낫다. 이후에 다룬다
- 테이블 스캔 : pk 순서대로 읽는것, 사용을 피할 수 있으니 가능한 피하라

### EXPLAIN
- table : 테이블 이름 또는 subquery
- type : 접근방법
  - ALL : 테이블 스캔
  - INDEX : index 스캔
  - CONST, RANGE, REF : 인덱스 조회
- possible_keys : 사용할 수 있는 인덱스 나열
- key : 사용할 인덱스 이름 nullable
- ref : 단일 테이블 쿼리 일경우 상수조건, 아닐 경우 이전 테이블 열 참조
- rows : 조회할 예상 수(반환 행수 x)
- extra : 부가정보
