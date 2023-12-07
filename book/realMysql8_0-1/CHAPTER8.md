# 8장 

## 랜덤 I/O, 순차 I/O
- 디스크 I/O는 느리다
- 디스크 I/O를 줄이는 것이 성능 튜닝의 핵심이다.

#### 순차 I/O
- 디스크 헤더를 움직이지 않고 한번에 많은 데이터를 읽는 방식
- SSD >= HDD , 비교적 작은차이다
- 풀 테이블 스캔은 순차 I/O 방식이다

#### 램덤 I/O
- DB에서 비중이 큰 I/O이고 SSD가 월등히 빠르다.
- 순차I/O에 비해 비용이 큰 I/O이다.
- 쿼리 튜닝 = 랜덤I/O 줄이기 = 꼭 필요한 데이터만 읽도록 쿼리수정

## INDEX
- INDEX가 많은 테이블은 INSERT,UPDATE,DELETE가 느리다.
#### 저장 방식으로 분류
- B-Tree 
- Hash
- Fracta
- Merge-Tree
- R-Tree
#### 데이터 중복여부로 분류
- 유니크 인덱스
  - 유니크 인덱스에 대해 동등조건 검색은 옵티마이저에게 항상 1건의 레코드만 찾으면 된다는 정보가 된다.
- 논 유니크 인덱스

#### 데이터 기능별로 구분
- 전문 검색용
- 공간 검색용

## B-Tree INDEX
- 밸런스 트리
- 정렬 상태 유지
- InnoDB 스토리지 엔진은 PK를 데이터 주소처럼(논리주소) 사용한다

#### INDEX 키 삽입
1. 저장될 위치 검색
2. 리프노드가 꽉차면 split(상위 노드까지)
- 쓰기 작업 비용 크다
- 인덱스 하나당 쓰기 약 1.5배
  - 인덱스 없는 테이블에 레코드 추가 비용을 1이라고 하면 인덱스 3개 테이블에는는 5.5
- 최적화를 위한 쓰기지연 가능
  - pk나 유니크는 지연 불가

#### INDEX 키 삭제 
- 해당 키의 리프노트드를 찾아서 삭제마크표시(disk I/O)
- 최적화를 위한 지연처리 가능

#### INDEX 키 수정
- 키를 삭제하고 다시 새로운 키를 추가
- 키값이 리프노드의 위치를 결정지으므로 단순한 변경이 불가능
- 최적화를 위한 지연처리 가능

#### INDEX 키 조회
- 트리탐색
- update,delete를 위한 검색도 검색
- B-Tree Index 이용은 100%일치,값의 앞부분일치, 부등호 일때만 가능
- 인덱스의 키값이 변형된 후 비교되는 경우는 좋은 인덱스사용 불가
- UPDATE,DELETE에서 적절한 INDEX가 없으면 불필요하게 많은 레코드를 잠군다.

## INDEX 사용에 영향을 미치는 요소
#### 기본단위 페이지
- InnoDB 스토리지 엔진은 디스크에 데이터를 저장하는 기본 단위를 페이지(블록)이라고 한다.
- 디스크의 모든 Read/Write 최소 단위가 된다.
- 버버풀에서 데이터를 버퍼링하는 기본 단위가 된다.
- INDEX도 페이지 단위로 관리한다.
#### 인덱스 칼럼의 크기
- B-Tree가 가질 수 있는 자식노드의 수
  - index의 페이지 크기와 키값의 크기에 따라 결정된다.
  - ex: 인덱스 페이지 크기 16KB, 키값 크기(16)+자식노드주소 크기(12) = 28
    - 자식 수 = 16*1024/28 = 585
    - 키값 크기가 32로 늘어났을 경우 자식 수 = 16*1024/44 = 372 
- index 키값이 커지면
  - 캐싱할 수 있는 레코드가 줄어든다.
  - 페이지에 담는 키 수가 적어진다.
  - 트리 높이당 가질 수 있는 키값이 늘어난다.
  - => 랜덤 I/O 횟수가 늘어난다.   
`가능한 키값을 작게 만들어라.`
#### 유니크 인덱스 키 값 갯수
- 키 값의 중복이 많아지면 선택도는 낮아진다.
- 총 10000개의 데이터
- 유니크 값이 10개 존재 시 인덱스 검색
  - 종류가 10개 -> 약 1000번 검색
- 유니크 값이 1000개 존재 시 인덱스 검색
  - 종류가 1000개 -> 약 10번 검색
#### 레코드 건수
- 100만개중 50만개 읽기
  - 풀 테이블 스캔 VS INDEX
    - 전체행의 20~25프로를 넘게 읽으면 손익분기점을 넘긴다.
    - 옵티마이저가 자동으로 인덱스 사용을 반려시킨다.

## 인덱스 접근 방식
#### 인덱스 레인지 스캔 
- 빠르다 
- 범위 스캔이다 -> 시작위치를 찾은뒤 순차적으로 스캔한다.
- 스캔하면서 실제 데이터파일을 읽을때마다 랜덤 I/O가 일어난다.
  - INDEX를 쓰면 비용이 크다 그래서 전체의 약 25퍼를 넘어가면 데이터 파일을 직접 읽는다
- index seek(데이터 시작위치 찾기) -> index scan -> 랜덤 I/O
- 커버링 인덱스 쓰면 랜덤 I/O가 많이 줄어든다.

#### 인덱스 풀 스캔
- index에 해당하는 리프노드를 전부 스캔한다.
- 대표적으로 where절에서 맨왼쪽 접두가사 안 쓰인 경우
- 비효율적이다. 인덱스를 사용은 하지만 효율적으로 사용하지 못한다.

#### 루스 인덱스 스캔
- 듬성 듬성 읽는다.
- 레인지랑 비슷한데 중간에 필요없는애는 무시하고 넘어간다.
- Group By 또는 Max,Min
- ex : SELECT dept_no,MIN(emp_no) FROM dept WHERE dept_no BETWEEN '1' AND '3' GROUP BT dept_no
  - INDEX(dept_no,emp_no)
  - dept_no 1,2,3의 첫번째만 읽으면 된다. 범위 내의 모든 노드를 스캔할 필요가 없다.

#### 인덱스 스킵 스캔
- idx(gender,birth_date)
- SELECT gender,birth_date FROM emp WHERE birth_date >= ...
  - 맨 왼쪽 접두사가 없지만 8.0이후 gender(M,F) 케이스별로 WHERE birth_date >= 쿼리를 실행하는 것과 비슷하게 최적화해준다.
  - = gender-M의 Range, gender-F의 range로 최적화 해준다.
- SELECT * FROM emp WHERE birth_date >= ...
  - 쿼리가 index에 존재하는 컬럼만으로 처리가 불가능하면 스킵 불가능
- Gender의 중복도가 (현재는 M아니면 F) 높아야 된다.

## 다중 컬럼 인덱스
- idx(a,b) 와 idx(b,a)는 다르다
- n인덱스는 n-1에 의존하여 정렬되기 떄문이다.
- 정렬 순서를 혼합해서 인덱스를 생성할 수 있다.
- 인덱스를 읽는 방향은 쿼리에 따라 옵티마이저가 결정한다.

### 읽는 방향, 정순과 역순
- 정순과 역순은 비용이 같지않다. 역순이 더 비용이 크다
  - 페이지 잠금이 정순에 적합하다.
  - 페이지 내에서 index 레코드가 단방향 링크이다.

## 인덱스 가용성
#### 동등조건과 범위 조건
- SELECT * FROM dept_cmp WHERE dept_no = ... AND emp_no >= ...
  - CASE A : idx(dept_no,emp_no)
    - dept_no를 찾고 empt_no범위 스캔한다. = 범위조건
    - = dept_no가 ...인 애들 중에서 empt_no범위를 찾아 반환한다.
  - CASE B : idx(emp_no,dept_no)
    - emp범위를 찾고 하나씩 dept_no를 찾는다 = 필터링
    - = emp범위를 찾고 그중에 일치하는 dept_no를 찾아 반환한다.
- SELECT * FROM emp WEHRE first_name Like %fdas
  - 왼쪽같이 고정되어 있지 않으면 range-scan 불가능
  - 정렬 우선순위가 낮은 값만으로는 왼쪽 우선 정렬 기반의 B-Tree idnex 사용불가

### 대표적 인덱스 사용 조건
- idx (i1,i2,i3,i4,---,in)
- 사용 못하는 경우
  - i1에 대한 조건이 없는 경우
  - i1 비교조건이 사용불가 조건중 하나인경우 (NOT,인덱스 컬럼 변경,뒷부분 일치 Like, ...)
- 사용 가능한 경우
  - i1~ i-1.. 까지 동등비교 형태
  - i부터
    - 동등비교(IN 포함)
    - 크다작다 형태(<,>)
    - LIKE 좌측 일치 패턴

## R-Tree Index
- 공간 인덱스 -> 2차원 데이터를 인덱싱하고 검색하는 목적으로 사용된다.
- MySQL은 위치기반 서비스를 위한 2차원 데이터를 다루는 방법을 공간확장을 이용해 제공한다.
#### 공간확장의 3가지 기능
- 공간데이터를 저장할수 있는 타입
- 공간 데이터의 검색을 위한 공간인덱스(R-Tree INDEX)
- 공간 데이터의 연산함수 (거리 또는 포함관계 처리)
#### 구조 및 특징
- 도형 타입 Geometry를 지원
#### R-Tree 알고리즘 이해
- MBR : Minimum Bounding Rectangle
  - 도형을 감싸는 최소 크기의 사각형
- 최상위 BR -> 루트 노드
- 차상위 BR -> 브랜치 노드
- 최하위 BR -> 리프 노드
`즉 R-Tree는 MBR 정보를 이용해 B-Tree 형태로 인덱스를 구축한다. `

#### 기준점 반경 이내의 점들 검색하기
- ST_contains() 또는 ST_within() 함수를 이용
- 위 함수는 기본적으로 반경 MBR로 관계 비교를 하기 떄문에 정확한 계산을 위해서는 추가적인 조건이 필요하다. 
```sql
ex 
SELECT * FROM tb_location
WHERE ST-contains(사각상좌좌표,px)
AND ST_DISTANCE_SPHER(p,px)<= 5 * 10000 // 5km 
``` 

## 전문 검색 인덱스

#### B-Tree 한계
- 실제 컬럼 값이 1MB이어도 InnoDb 기준 3072바이트 까지만 잘라서 인덱스 키로 사용한다
  - InnoDb의 로우포맷 시스템 변수 값이 DYNAMIC 또는 COMPRESSED 일경우 3072 바이트고 다른 값은 더 작다
- 좌측 일치 및 전체 일치 검색만 가능하다.

#### 전문(Full-text) 검색
- 문서의 내용 전체를 인덱스화 해서 특정 키워드를 찾는 방법

#### 어근 분석 알고리즘
- 과정 1 : 불용어(검색에 가치 없는 단어) 필터링
- 과정 2 : 어근 분석
  - MeCap 오픈소스 제공
  - 한글 특징에 맞춰 완성도를 갖추는 작업은 어렵다.
#### n-gram 알고리즘
- 어근 분석 개선
- 키워드를 검색해내기 위한 인덱싱 알고리즘
- 글자를 잘라서 인덱싱 함으로 인덱스의 크기가 크다. 
- 2-gram : 2글자 단위로 중첩해서 키워드를 쪼갠뒤 인덱싱 하는 방식으로 많이 사용된다.
```
ex To be or not to be. That is the question
To -> To
...
That -> th ha at
...
question -> qu ue es st ti io on

```
=> 해당 토근들에 대해 불용어를 걸러낸다.
information_schema,innodb_ft_default_stopword - 불용어 테이블
불용어를 직접 추가할 수도 무시할 수도 있다.
```sql
사용
SELECT * FROM table
WHERE MATCH(doc_body) AGAINST('애플' IN BOOLEANMODE) 
```

## 함수 기반 인덱스
- 변형된 칼럼에 대해 인덱스를 구축

#### 가상칼럼 이용
```mysql
ALTER TABLE user(
    ADD full_name VARCHAR(30) AS (CONCAT(first_name,' ', last_name)) VIRUTAL
    ADD INDEX ix_fullname (full_name)
  )

```
- 가상 칼럼은 새로운 칼럼을 추가하는 것과 같은 효과(테이블 구조 변경)
#### 함수 이용
```mysql
CREATE TABLE user(
    ...
    INDX ix_fullname((CONCAT(frist_name,' ',last_name)))
    ...
)
```
```mysql
SELECT * FROM user where CONCAT(first_name,' ',last_name) = 'MUTT LEE'
```
- 인덱스에 명시된 표현식을 그대로 사용해야 한다.
- CONCAT 함수 공백 리터럴 때문에 인덱스를 사용하지 않을 수가 있댜.
  - 시스템 변수 값을 통일하면 해결된다.
- 내부적으로 가상칼럼과 구현이 같음으로 둘사이의 성능차이는 없다.
## 멀티벨류 인덱스
- 하나의 데이터 레코드가 여러개의 키값을 가질 수 있는 형태
- JSON 타입을 관리할 때 효율적이다.
```mysql
create table user(
...
credit_info JSON
INDEX my_creditscores ((CAST credit_info -> '$.credit.scores' AS UNSIGNED ARRAY))
)
```
```
INSERT INTO user VALUES (..., '{"credit_scores" : [1,2,3]}')
```
```
SELECT * FROM user WHERE 1 MEMBER OF(credit_info->'$.credit_scores');
```
- MEMBER OF()
- JSON_CONTAINS()
- JSON_OVERLAPS()
- 전용 함수를 이용해야 인덱스가 활용된다.

