# inventory-system-lecture

## Synchronized 해결
Spring 의 transactional 어노테이션이 있을 경우 동시성 이슈 발생  
transactional 없이 하면 통과되지만 성능적으로 좋지 않고, 여러 서버에서 접근 시에는 Race Condition 발생

## Lock 해결
### Pessimistic Lock
실제로 데이터에 Lock을 걸어서 정합성을 맞춤  
Exclusive lock 을 걸게되면 다른 트랜잭션에서는 lock이 해제되기 전에 데이터를 가져갈 수 없음
충돌이 많을 때 추천
성능 감소가 있고, 데드락이 걸릴 수 있기 때문에 주의해서 사용해야 함

### Optimistic Lock
실제로 DB Lock 을 이용하지 않고 버전을 이용해서 정합성을 맞춤  
먼저 데이터를 읽은 후 update를 수행할 때 현재 내가 읽은 버전이 맞는지 확인하여 업데이트 함  
내가 읽은 버전에서 수정 사항이 생겼을 경우 application에서 다시 읽은 후 작업 수행
만약 업데이트가 실패한다면 예외를 던지므로 재시도 로직을 개발자가 직접 작성해야함  
롤백 과정이 없기 때문에 롤백 또한 직접 작성해야하고, 외부 API를 사용하는 로직에 적절하지 못함 

### Named Lock
이름은 가진 metadata locking  
이름을 가진 lock을 획득 후 해제할 때까지 다른 세션은 이 lock을 획득할 수 없도록 함  
주의할 점은 transaction이 종료될 때 lock이 자동으로 해제되지 않음  
별도의 명령어로 해제를 수행해주거나 선점 시간이 끝나야 해제됨
