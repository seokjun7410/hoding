# ArrayList, LinkedList 결론

- linkedList는 기본적으러 더블링 링크드리스트를 제공함으로 get/set을 제외한 연산에서 높은 성능을 보입니다.
- get/set의 의존유무로 선택하면 됩니다.
- 주의점
    - 이러한 연산이 실행시간에 뚜렷한 영향을 미친다.
    - 작업하는 리스트가 성능에 영향을 줄 만큼 크다.
    - ArrayList는 메모리에 순서대로 저장되어 낭비되는 공간이 비교적 적고 캐시히트로 인해 속도가 빠를 수 있다.

- get/set에 의존적이고 데이터가 크다면 ArrayList
- 아닐경우 linked를 추천한다.