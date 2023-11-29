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

# EXPLAIN 실습
### 예제
![스크린샷 2023-11-29 오후 2 56 06](https://github.com/seokjun7410/hoding/assets/47974623/79d7993f-f0a6-4c43-b42d-6943b1da83fe)

# WHERE 절
## PK를 통한 조회
![스크린샷 2023-11-29 오후 2 45 44](https://github.com/seokjun7410/hoding/assets/47974623/a205485f-0899-4fc5-8825-8af5386115ec)
- type : const
  -  테이블의 레코드 건수에 관계없이 쿼리가 프라이머리키나 유니크키 칼럼을 이용하는 WHERE 조건절을 가지며, 반드시 1건을 반환하는 쿼리의 처리방식
- possible_keys: PRIMARY
  - 사용할 수 있는 인덱스
- key: PRIMARY
  - 사용할 인덱스 이름
- ref : const
  -  상수조건

## PK이용 및 범위 WHERE
![스크린샷 2023-11-29 오후 2 53 08](https://github.com/seokjun7410/hoding/assets/47974623/4938280c-5ccf-41ce-88e6-ca51ef6c4130)
- type : range
  -  범위스캔 = 인덱스 특정 범위의 행에 접근한다
- possible_keys: PRIMARY
  - 사용할 수 있는 인덱스
- key: PRIMARY
  - 사용할 인덱스 이름
- ref : NULL
  -  상수조건도 아니고 조인도 아니다
- Extra : Using where
  - MySQL 엔진에서 스토리지 엔진으로부터 받은 레코드에 별도의 가공을 해서 필터링 작업을 처리하는 경우

## 세컨더리 인덱스 조회
![스크린샷 2023-11-29 오후 3 00 17](https://github.com/seokjun7410/hoding/assets/47974623/fa457984-203f-4576-8994-dd74361b6efa)
- type : ref
  -  고유 키가아닌 인덱스에 대한 등가비교, 여러 개 행에 접근할 가능성이 있다.
- possible_keys: idx_a_b
  - 사용할 수 있는 인덱스
- key: idx_a_b
  - 사용할 인덱스 이름
- ref : const
  -  상수조건
- Extra : Using index condition
  - 인덱스 푸시 다운(Index condition pushdown) 최적화를 사용할 때 표시된다.
  - 인덱스를 비교하는 작업은 InnoDB 스토리지 엔진이 수행하지만 조건을 비교하는 작업은 MySQL 엔진에서
    수행하는데 인덱스를 범위 제한 조건으로 사용하지 못하는 조건은 스토리지 엔진으로 전달해주지 않는데
    이러한 비효율을 없애기 위해 인덱스를 범위 제한 조건으로 사용하지 못하더라도 인덱스에 포함된 칼럼의 조건이
    있다면 스토리지 엔진으로 전달해 주는 최적화이다.

## 세컨더리 인덱스 조회 2
![스크린샷 2023-11-29 오후 2 55 10](https://github.com/seokjun7410/hoding/assets/47974623/ef209001-2dec-4ea2-a195-268e4cd2aaca)
- type : ref
  -  고유 키가아닌 인덱스에 대한 등가비교, 여러 개 행에 접근할 가능성이 있다.
- possible_keys: idx_a_b
  - 사용할 수 있는 인덱스
- key: idx_a_b
  - 사용할 인덱스 이름
- ref : const,const
  -  상수조건,상수조건
- Extra : Using index condition
  - 인덱스 푸시 다운(Index condition pushdown) 최적화를 사용할 때 표시된다.


## 인덱스 조회와 인덱싱되지 않은 열 조회
![스크린샷 2023-11-29 오후 3 06 40](https://github.com/seokjun7410/hoding/assets/47974623/eb511177-2581-4ccc-80eb-b4d7092a14ea)
- type : ref
  -  고유 키가아닌 인덱스에 대한 등가비교, 여러 개 행에 접근할 가능성이 있다.
- possible_keys: idx_a_b
  - 사용할 수 있는 인덱스
- key: idx_a_b
  - 사용할 인덱스 이름
- ref : const
  -  상수조건
- Extra : Using index condition, using where
  - 인덱스 푸시 다운(Index condition pushdown) 최적화를 사용할 때 표시된다.
  - 인덱스를 사용하여 a열의 조건에 대한 행을 조회하고 c열에 일치하는 행을 찾는다

## 맨 왼쪽 접두사 없는 where
![스크린샷 2023-11-29 오후 3 12 46](https://github.com/seokjun7410/hoding/assets/47974623/3f75cace-1ca0-4147-8855-1c9e4a34b3d1)
- type : ALL
  -  풀 테이블 스캔
- possible_keys: NULL
  - 사용할 수 있는 인덱스
- key: Null
  - 사용할 인덱스 이름
- ref : NULL
  -  상수조건
- Extra :using where
  - b ='Be 조건을 사용해 일치하지 않는 행을 필터링한다.

# GROUP BY
## a열 값의 개별 그룹들
```
+----+----+
| a  | b  |
+----+----+
| Ag | B  |
| Ag | B  |
| Al | B  |
| Al | B  |
| Al | Br |
| Ar | B  |
| Ar | Br |
| Ar | Br |
| At | Bi |
| Au | Be |
+----+----+
```
- Mysql은 인덱스 순서에 따라 암묵적으로 그룹화 된다.
- idx_a_b(a,b)에 대해 a 열값은 5개의 그룹이 있다.

## groupBy a
```
mysql> EXPLAIN SELECT a, COUNT(*) FROM elem GROUP BY a\G;
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: elem
   partitions: NULL
         type: index //인덱스 스캔
possible_keys: idx_a_b //인덱스 사용 
          key: idx_a_b //인덱스 사용 
      key_len: 16
          ref: NULL
         rows: 10
     filtered: 100.00
        Extra: Using index // 인덱스 a열만 읽고 PK를 이용한 전체행 읽기 x
1 row in set, 1 warning (0.00 sec)
```
## 동일한 열에대해 groupBy a + where
```
mysql> EXPLAIN SELECT a, COUNT(*) FROM elem WHERE a != 'Ar' GROUP BY a\G;
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: elem
   partitions: NULL
         type: range//범위스캔 a!='Ar' = (a > Ar AND a < Ar)
possible_keys: idx_a_b //인덱스 사용 
          key: idx_a_b //인덱스 사용
      key_len: 8
          ref: NULL
         rows: 7
     filtered: 100.00
        Extra: Using where; Using index // using where (a!='Ar')
1 row in set, 1 warning (0.00 sec)
```
## 다른 열에대해 groupBy a + where
```
mysql> EXPLAIN SELECT a,b FROM elem WHERE b = 'B' GROUP BY a\G;
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: elem
   partitions: NULL
         type: range //
possible_keys: idx_a_b
          key: idx_a_b
      key_len: 16
          ref: NULL
         rows: 6
     filtered: 100.00
        Extra: Using where; Using index for group-by
1 row in set, 1 warning (0.01 sec)
```
- GROUP BY 처리를 위해 MySQL 서버는 그루핑 기준 칼럼을 이용해 정렬하고 결과를 그루핑한다.
  이러한 작업은 인덱스 없이 진행되면 매우 고부하 작업이 된다.
  이 때 GROUP BY 처리를 위해 인덱스가 사용되는 방법을 "루스 인덱스 스캔"이라고 하는데 이런 경우에 해당 문구가 노출된다.

## 맨 왼쪽 접두사가 없는 gorup by
```
mysql> EXPLAIN SELECT b, COUNT(*) FROM elem GROUP BY b\G;
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: elem
   partitions: NULL
         type: index //인덱스 스캔
possible_keys: idx_a_b 
          key: idx_a_b // 인덱스 사용!
      key_len: 16
          ref: NULL
         rows: 10
     filtered: 100.00
        Extra: Using index; Using temporary
1 row in set, 1 warning (0.01 sec)
```

# ORDER BY

## 다른 인덱스 열들의 order by와 where
```
mysql> EXPLAIN SELECT a, b FROM elem WHERE a = 'Ar' ORDER BY b\G;
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: elem
   partitions: NULL
         type: ref
possible_keys: idx_a_b
          key: idx_a_b
      key_len: 8
          ref: const
         rows: 3
     filtered: 100.00
        Extra: Using where; Using index
1 row in set, 1 warning (0.00 sec)
```
- a = 'Ar'로 이동하고 b열값을 순서대로 읽는다.
## ORDER BY ID
```
mysql> EXPLAIN SELECT * FROM elem WHERE a = 'Al' AND b = 'B' ORDER BY id\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: elem
   partitions: NULL
         type: ref
possible_keys: idx_a_b
          key: idx_a_b
      key_len: 16
          ref: const,const
         rows: 2
     filtered: 100.00
        Extra: Using index condition
1 row in set, 1 warning (0.01 sec)
```
- pk는 인덱스 정의 우측에 숨겨져 있다.
# 맨 왼쪽 접두사 없는 order by
```
mysql> EXPLAIN SELECT * FROM elem WHERE a = 'Al' ORDER BY id\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: elem
   partitions: NULL
         type: ref
possible_keys: idx_a_b
          key: idx_a_b
      key_len: 8
          ref: const
         rows: 3
     filtered: 100.00
        Extra: Using index condition; Using filesort
1 row in set, 1 warning (0.01 sec)
```
- 인덱스 컨디션 푸시다운 : 스토리지 엔진이 인덱스를 사용하여 where 조건과 일치하는 행을 찾는다.