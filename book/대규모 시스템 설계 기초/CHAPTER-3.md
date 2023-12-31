# 대략적인 규모 추정 
madeBy hoding  
date 23.12.26  
reference [가상 면접 사례로 배우는 대규모 시스템 설계 기초](https://product.kyobobook.co.kr/detail/S000001033116)    

## 처리율 제한 장치
- Dos 공격에 의한 자원고갈을 방지할 수 있다.
- 대부분 대형 IT기업들이 공개한 API는 어떤 형태로든 처리율 제한 장치를 가지고 있다.

## 처리율 제한 장치를 어디에 둘 것인가?
- 클라이언트 측 : 클라이언트 요청은 쉽게 위변조가 가능함으로 적절치 않다.
- 서버/미들웨어 측 : 현재 기술 스택이나 엔지니어링 인력, 우선순위, 목표에 따라 유동적으로 선택한다.

## 처리율 제한 알고리즘

###  `토큰 버킷 알고리즘 `

<img src="https://daeakin.github.io//images/large-system/token-bucket.png" width="300" height="300">

출처 : [가상 면접 사례로 배우는 대규모 시스템 설계 기초](https://product.kyobobook.co.kr/detail/S000001033116)


- 버킷 크기, 토큰 공급률 파라미터를 받는다.
- 버킷에 일정 토큰이 주기적으로 공급되고 요청마다 토큰을 사용한다.
- 토큰이 고갈 된 시점에 요청이 들어오면 요청은 반려된다.
- 장점 : 구현이 쉽다, 메모리 효율적이다. 짧은시간에 집중되는 트래픽도 처리가능하다.
- 단점 : 버킷 크기와 토큰 공급률 값을 적절하게 튜닝하는 것이 까다롭다.

### `누출 버킷 알고리즘`

<img src="https://daeakin.github.io//images/large-system/leaky-bucket.png" height=300, width=700>

출처 : [가상 면접 사례로 배우는 대규모 시스템 설계 기초](https://product.kyobobook.co.kr/detail/S000001033116)



- 요청 처리율이 고정되어 있다.
- 보통 큐로 구현한다.
- 지정된 시간마다 큐에서 요청을 꺼내어 처리한다.
- 큐가 가득 차있을 때 요청은 반려된다.
- 장점 : 큐의 크기가 제한되어 있어 메모리 사용량 측면에서 효율적이다. 고정된 처리율을 갖고 있기 때문에 안정적 출력이 필요한 경우 적합하다.
- 단점 : 단시간에 많은 트래픽이 몰리는 경우 쿠에는 오래된 요청들이 쌓이게 되고 그 요청들을 제때 처리 못하면 최신요청들을 버려지게 된다. 두개 인자를 적절하게 튜닝하기 까다롭다

### `고정 윈도 알고리즘`

<img src="https://daeakin.github.io//images/large-system/fixed-window-counter.png" height=300, width=500>

출처 : [가상 면접 사례로 배우는 대규모 시스템 설계 기초](https://product.kyobobook.co.kr/detail/S000001033116)



- 설정된 임계치에 도달하면 새로운 요청은 새 윈도가 열릴 때까지 버려진다.
- 윈도의 경계 부근에 순간적으로 많은 트래픽이 집중될 경우 윈도에 할당된 양보다 더 많은 요청이 처리도리 수 있다.
- 장점 : 이해하기 쉽다. 메모리 효율이 좋다.
- 단점 : 기대한 처리량보다 많은 양의 요청을 처리하게 될 수 있다.

### `이동 윈도 로깅 알고리즘`

<img src="https://daeakin.github.io//images/large-system/sliding-window-log.png" height=300, width=400>

출처 : [가상 면접 사례로 배우는 대규모 시스템 설계 기초](https://product.kyobobook.co.kr/detail/S000001033116)



- 요청의 타임스탬프를 추적한다.
- 타임스탬프는 보통 레디스의 정렬집합(sorted SET)같은 캐시에 보관한다.
- 요청이 들어올 때마다 시간을 로깅한다.
- 요청이 들어온 시간으로 부터 지정된시간 전까지 범위에 요청수와 허용한도를 비교한다.
- 장점 : 어느 순간의 윈도를 보더라도 허용되는 요청의 개수는 시스템의 처리율 한도를 넘지 않는다.
- 단점 : 거부된 요청의 타임스탬프도 보관하기 때문에 다량의 메모리를 사용한다.

### `이동 윈도 카운터 알고리즘`

<img src="https://daeakin.github.io//images/large-system/sliding-window-counter.png" height=300, width=400>

출처 : [가상 면접 사례로 배우는 대규모 시스템 설계 기초](https://product.kyobobook.co.kr/detail/S000001033116)



- 고정 윈도 카운터 알고리즘과 이동 윈도 로깅 알고리즘을 결합
- 윈도우가 1분일 때 현재 1분간 요청 수 + 직전 1분간의 요청수 X 윈도우에 포함된 직전 1분 비율
- 현재 1분이 30퍼가 지났을 때 직전 1분갯수X30퍼 + 현재 요청수를 계산하면 된다.
- 장점 : 이전 시간대의 평균 처리율에 따라 현재 윈도 상태를 계산하므로 짧은 시간에 몰리는 트래픽에도 잘 대응하며 메모리 효율이 좋다.
- 단점 : 비율로 계산하기 때문에 허용한도가 다소 느슨하다.

## 카운터는 어디에 보관할 것인가?
- 메모리상에서 동작하는 캐시가 바람직하다
- 레디스는 INCR,EXPIRE(카운터 타임아웃) 명령을 지원하기 때문에 좋다 

#### 처리 순서
- 처리율 제한 장치는 레디스의 지정 버킷에서 카운터를 가져와서 한도에 도달했는지 검사한다.
  - 한도에 도달했다면 요청은 거부된다.
- 한도에 도달하지 않았다면 요청은 API 서버로 전달된다.
- 카운터 값을 증가시킨후 레디스에 저장한다.

#### 허용한도 넘은 요청 처리하기
- 경우 따라서 한도 제한에 걸리 메시지를 나중에 처리하기 위해 큐에 보관할 수 있다.

## 클라이언트에서의 처리율 제한
- 클라이언트는 HTTP헤더를 통해 아래 정보를 확인할 수 있다.
- X-RateLimit-Remaining: 윈도 내에 남은 처리 가능 요청의 수
- X-RateLimit-Limit : 윈도마다 클라이언트가 전송할 수 있는 요청의 수
- X-RateLimit-Retry-After : 제한에 걸리지 않으려면 몇 초 뒤에 요청을 다시 보내야 하는지
`사용자가 너무 많은 요청을 보내면 429 too many request 오류를 Retry-After 헤더와 함께 반환하도록 한다.`

## 분산 환경에서의 주의점
`경쟁 조건 , 동기화`

### 경쟁 조건
- 병행성이 심한 환경에서 카운터가 제대로 증가하지 않을 수 있다.
- 락으로 해결하면 성능에 영향을 줄 수 있기 때문에 레디스에 정렬 집합자료구조를 사용하면 좋다.

### 동기화
- 많은 사용자를 지원할 때 한대의 처리율 제한 장치로 충분하지 않을 수 있다.
- 분산 처리율 저한 장치를 구성 한다면, 레디스를 이용해 중앙 집중형 저장소를 구축하자




