```
127.0.0.1:6379> SET lecture inflearn-redis
```

```
127.0.0.1:6379> GET lecture
"inflearn-redis"
```

```
127.0.0.1:6379> DEL lecture
(integer) 1

127.0.0.1:6379> GET lecture
(nil)
```

# STRING  
- SET lecture infleran-redis
  - STRING 값 저장하기
- MSET price 100 language ko
  - 다수의 스트링 저장
- MGET lecture price language
  - 타수의 키값 반환
- INCR price
  - 숫자형태의 스트링 연산가능
  - price 1 증가
- INCRBY price 10
  - 숫자형 스트링에 특정 값 더하기
- SET infleran-redis '{"price:100}'
  - json object형태 스트링으로 저장 후 사용할때 json으로 다시 바꿔야됨
- SET inflearn-redis:ko:price 200
  - 레디스 네이밍 컨벤션, 의미별로 키 관리하기

# 숫자형 String 다루기
```
127.0.0.1:6379> SET lecture inflearn-redis
OK
127.0.0.1:6379> MSET price 100 language ko
OK
127.0.0.1:6379> MGET lecture price language
1) "inflearn-redis"
2) "100"
3) "ko"
127.0.0.1:6379> INCR price
(integer) 101
127.0.0.1:6379> INCRBY price 9
(integer) 110
127.0.0.1:6379> 
```
# JSON형식 STRING 다룩 

```
127.0.0.1:6379> SET inflearn-redis '{"price": 100, "laguage" : "ko"'
OK
127.0.0.1:6379> GET inflearn-redis
"{\"price\": 100, \"laguage\" : \"ko\""
```
# Redis 컨벤션
```
127.0.0.1:6379> SET inflearn-redis:ko:price 200
OK
127.0.0.1:6379> GET inflearn-redis:ko:price
"200"
```

# LIST
- String을 양방향 Linked List로 관리
- Queue나 Stack 구현에 사용
- Queue
  - LPUSH queue job1 job2 job3
  - RPOP queue
- Stack
  - LPUSH stack job1 job2 job3
  - LPOP stack 
- Index 이용(message broker)
  - LPUSH queue job1 job2 job3
  - LRANGE queue -2 -1
  - 왼쪽은 -1부터 감소
  - 오른쪽은 0부터 증가
  - LTRIM queue 0 0
    - 0~0만 남기고 삭제

# Queue
```
127.0.0.1:6379> LPUSH queue job1 job2 job3
(integer) 3
127.0.0.1:6379> RPOP queue
"job1"
```
# Stack
```
127.0.0.1:6379> LPUSH stack job1 job2 job3
(integer) 3
127.0.0.1:6379> LPOP stack
```

# 큐 이용해보기
```
127.0.0.1:6379> LPUSH queue job1 job2 job3
(integer) 3
127.0.0.1:6379> RPOP queue
"job1"
127.0.0.1:6379> LPUSH queue job4 job5
(integer) 4
127.0.0.1:6379> LRANGE queue 0 -1
1) "job5"
2) "job4"
3) "job3"
4) "job2"
127.0.0.1:6379> LRANGE queue -2 -1
1) "job3"
2) "job2"
127.0.0.1:6379> LRANGE queue 2 3
1) "job3"
2) "job2"
127.0.0.1:6379> LTRIM queue 0 1
OK
127.0.0.1:6379> LRANGE queue 0 -1
1) "job5"
2) "job4"  
```

# SETs
- Unique String을 저장하는 정렬되지 않은 집합
  - SADD user:1
```

```

```
```

```
```

```
```

```
```


