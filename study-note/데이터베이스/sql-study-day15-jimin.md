# 오라클로 배우는 데이터베이스 입문/11강-트랜잭션 제어와 세션
## 11-1 하나의 단위로 데이터를 처리하는 트랜잭션
### - 트랜잭션이란?
- 트랜잭션의 필요성: 은행 업무를 볼 때, A계좌에서 B계좌로 100만원을 옮기려는데 A계좌에서 돈이 나간 순간 데이터베이스에 오류가나서 B에도 돈이 안들어 오고 A의 계좌 잔액도 0이 되어 버리면 정말 심각한 문제가 발생할 수 있다.
- 이러한 데이터 유실이라는 무시무시한 상황이 일어나는 것을 막으려면 두 가지 방법을 생각해볼 수 있다.
1. 어떤 상황에서든 두 UPDATE문을 모두 완전히 실행하는 것

2. 완전히 실행하는 것이 불가능 하다면, 두 UPDATE문을 실행하기 전의 상태, 
즉 아무 UPDATE문도 실행하지 않는 상태를 유지할 수 있어야 한다.
- **트랜잭션**? **더 이상 분할할 수 없는 최소 수행 단위**
  - 계좌 이체와 같이 하나의 작업 또는 밀접하게 연관된 작업을 수행하기 위해 **한 개 이상의 데이터 조작 명령어(DML)**로 이루어진다.
  - 어떤 기능 한가지를 수행하는 **SQL 덩어리**라고 볼 수 있다.
- 트랜잭션은 하나의 트랜잭션 내에 있는 여러 명령어를 한 번에 수행하여 작업을 **완료**하거나 아예 모두 **수행하지 않는** 상태, 즉 모든 작업을 취소한다.
- 트랜잭션을 **ALL OR NOTHING** 문장으로 설명하기도 한다.
- 트랜잭션을 제어하기 위해 사용하는 명령어를 **TCL**(TRANSACTION CONTROL LANGUAGE)라고 한다.
- 트랜잭션은 데이터베이스 **계정을 통해 접속**하는 동시에 **시작된다**.
- 트랜잭션이 종료되기 전까지 여러 SQL문을 실행하고 **트랜잭션을 제어하는 명령(TCL)을 실행할 때** **기존 트랜잭션이 끝난다**.
- 그 후 다시 **새로운 트랜잭션**이 시작된다.

## 11-2 트랜잭션을 제어하는 명령어
- 하나의 트랜잭션에 묶여있는 데이터 조작어(DML)의 수행상태는 두가지 상태로만 존재할 수 있다.
  1. 모든 명령어가 정상적으로 수행 완료된 상태
  2. 모든 명령어가 수행되지 않아 취소된 상태

>- **트랜잭션 제어 명령어**는 데이터 조작 상태를 이 두 가지 상태 중 하나로 유도하는 명령어를 의미한다.

- 즉, 데이터 조작을 데이터베이스에 **영구히 반영**하거나 **작업 전체를 취소**한다.
- DEPT 테이블을 복사해서 DEPT_TCL 테이블 만들기
```java
CREATE TABLE DEPT_TCL
AS SELECT * FROM DEPT;
```
- DEPT_TCL테이블에 데이터를 입력, 수정, 삭제하기
```java
INSERT INTO DEPT_TCL VALUES(50, 'DATABASE', 'SEOUL');
UPDATE DEPT_TCL SET LOC = 'BUSAN' WHERE DEPTNO = 40;
DELETE FROM DEPT_TCL WHERE DNAME = 'RESEARCH';
SELECT * FROM DEPT_TCL;
```
### - 트랜잭션을 취소하고 싶을 때는 ROLLBACK
- 모든 작업의 수행을 취소하고 싶다면 ROLLBACK 명령어를 사용한다.
- ROLLBACK은** 현재 트랜잭션에 포함된 데이터 조작 관련 명령어의 수행을 모두 취소**한다.
- ROLLBACK으로 명령어 실행 취소하기
```sql
ROLLBACK;
```

### - 트랜잭션을 영원히 반영하고 싶을 때는 COMMIT
>- ROLLBACK과 달리 **지금까지 수행한 트랜잭션 명령어를 데이터베이스에 영구히 반영**할 때는 **COMMIT** 명령어를 사용한다.

- DEPT_TCL 테이블에 데이터를 입력, 수정, 삭제하기
```sql
INSERT INTO DEPT_TCL VALUES(50, 'NETWORK', 'SEOUL');
UPDATE DEPT_TCL SET LOC = 'BUSAN' WHERE DEPTNO = 20;
DELETE FROM DEPT_TCL WHERE DEPTNO=40;
SELECT * FROM DEPT_TCL;
```
- COMMIT 명령어로 반영하기
```sql
COMMIT;
```
>- COMMIT 명령어는 지금까지 **트랜잭션에서 데이터 조작 관련 명령어**를 통해 변경된 데이터를 모두 데이터베이스에** 영구히** 반영한다.

- COMMIT은 트랜잭션 작업이 정상적으로 수행되었다고 확신할 때 사용해야 한다!
- 트랜잭션 시작과 종료 과정
![](https://velog.velcdn.com/images/mini_mouse_/post/457c4106-f8ee-429b-975a-edcba0578c25/image.png)
![](https://velog.velcdn.com/images/mini_mouse_/post/77883770-3f2f-42ee-8850-740edf8099a1/image.png)
![](https://velog.velcdn.com/images/mini_mouse_/post/986b2d5c-69ca-43ba-a2cd-28159aac1c1f/image.png)
![](https://velog.velcdn.com/images/mini_mouse_/post/22fd7239-ab71-4bc8-94f5-553cd4fd72b9/image.png)
![](https://velog.velcdn.com/images/mini_mouse_/post/6cd14753-1fb7-41f6-afb8-a13b839ef755/image.png)
(출처: http://wiki.hash.kr/index.php/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98)

>- **COMMIT, ROLLBACK** 명령어 모두 현재 트랜잭션을 끝내고 **새 트랜잭션**을 시작하게 한다.
→ 종료될 트랜잭션에 작업을 **반영할지 취소할지** 결정한다.

- ROLLBACK을 통해 작업을 취소할 지점을 지정할 때 SAVEPOINT명령어를 사용할 수 있다.
![](https://velog.velcdn.com/images/mini_mouse_/post/d8c052fd-1b79-497f-b19c-0744c0c970d5/image.png)
  - 현재의 트랜잭션을 작게 분할한 명령어
  - 저장된 SAVEPOINT는 ROLLBACK TO SAVEPOINT문을 사용하여 표시한 곳까지 ROLLBACK 할수 있다.
![](https://velog.velcdn.com/images/mini_mouse_/post/6bca3791-4919-44ed-ba8a-afc3caabdfbe/image.png)

출처: https://memory0136.tistory.com/36

## 11-3 세션과 읽기 일관성의 의미
### - 세션이란?
- 세션(SESSION)은 어떤 활동을 위한 시간이나 기간을 뜻한다.

>- 오라클 데이터베이스에서의 세션:
데이터베이스 접속을 시작으로 여러 데이터 베이스에서 관련 작업을 수행한 후 접속을 종료하기 까지의 전체 기간

- 세션은, 웹서비스에서 **로그인해서 로그아웃할 때까지의 기간**과 비슷하다고 생각할 수 있다.

>- 즉, **세션이 여러 개**라는 말은, 현재 **오라클 데이터 베이스에 접속하여 사용 중인 연결이 여러 개** 있다는 뜻이다.

- 같은 계정으로 접속해도 새션은 각각으로 생성된다.
#### -- 트랜잭션과 세션의 관계
![](https://velog.velcdn.com/images/mini_mouse_/post/9fe6170b-9b3b-4994-81f0-477133ec175d/image.png)

- **세션**
  - 데이터베이스에 접속한 후 종료하기까지의 과정
- **트랜잭션**
  - 데이터 조작 명령어가 모인 하나의 작업 단위
- 세션 내부에는 하나 이상의 트랜잭션이 존재한다.
  → 세션이 유지되는 동안 여러 COMMIT, ROLLBACK작업이  진행된다.
⇒ 세션이 트랜잭션보다 큰 범위의 개념이다.

### - 읽기 일관성의 중요성
- **데이터베이스는** 여러곳(여러 사용자, 여러 응용 프로그램)에서 **동시에** 접근하여 데이터를 관리, 사용하는 것이 목적이기 때문에, 대부분 **수많은 세션이 동시에 연결**되어 있다.

>- **읽기 일관성**?
어떤 **특정 세션**에서 **테이블의 데이터를 변경 중**일 때, 그 외 **다른 세션**에서는 데이터의 변경이 확정되기 전까지 변경 사항을 알 필요가 없으므로, 데이터를 변경 중인 세션을 제외한 나머지 세션에서는 **현재 진행 중인 변경과 무관한 본래의 데이터를 보여 주는 특성**을 의미한다.

#### -- 실습
- 토드와 SQL*PLUS로 세션 알아보기
```sql
-- 세션 A
SELECT * FROM DEPT_TCL;
-- 세션 B
SELECT * FROM DEPT_TCL;
```
- 토드와 SQL*PLUS로 세션 알아보기
```sql
-- 세션 A
DELETE FROM DEPT_TCL WHERE DEPTNO=50;
SELECT * FROM DEPT_TCL;
-- 세션 B
--세션 A의 DELETE 명령 후,
SELECT * FROM DEPT_TCL;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/112f5260-b72a-4c70-be6e-ea816eba8893/image.png)

![](https://velog.velcdn.com/images/mini_mouse_/post/d90fc922-e43d-4544-93d6-10f4819353d3/image.png)

>- 어떤 데이터 조작이 포함된 **트랜잭션이 완료(COMMIT, ROLLBACK)되기 전까지** 데이터를 직접 조작하는 세션 외, **다른 세션에서는 데이터 시작 전 상태의 내용이 일관적으로 조회, 출력, 검색되는 특성**을 **읽기 일관성**(READ CONSISTENCY)라고 한다.

- 토드와 SQL*PLUS로 세션 알아보기
```sql
-- 세션 A
COMMIT;
SELECT * FROM DEPT_TCL;
-- 세션 B
--세션 A의 COMMIT 명령 후,
SELECT * FROM DEPT_TCL;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/0c2313c6-5852-4704-b60e-497b7f7ec5b6/image.png)

- 데이터를 **직접 변경 중인 해당 세션을 제외한 모든 세션**은 다른 세션의 데이터 변경과 상관없이 **이미 확정된 데이터만 검색됨**으로써 **읽기 일관성을 보장**할 수 있다.

## 11-4 수정 중인 데이터 접근을 막는 LOCK
### - LOCK이란?
- 특정 세션에서 조작 중인 데이터는 **트랜잭션이 완료**(COMMIT, ROLLBACK)**되기 전까지 다른 세션에서 조작할 수 없는 상태**가 된다.
⇒ 즉, **데이터가 잠기는(LOCK) 것**이다.
- LOCK은 잠금, 잠금현상으로도 표현된다.

>- **LOCK은** 조작 중인 데이터를 다른 세션은 조작할 수 없도록 **접근을 보류**시키는 것을 뜻한다.

- 비유: 화장실 사용
  - 화장실 칸(DATA)
  - 화장실에 칸에 먼저 들어간 사람(SESSION)
  - 화장실 칸에 먼저 들어간 사람이 문을 잠금(LOCK)
  - 화장실 기다리는 사람들(다른 세션들)
⇒ DATA에 먼저 접속한 SESSION이 DATA를 LOCK해서 다른 SESSION들은 먼저 접속한 SESSION이 작업을 완료할 때까지 대기해야 한다.

### - LOCK 개념 살펴보기
- 토드와 SQL*PLUS로 세션 알아보기
```sql
-- 세션 A
SELECT * FROM DEPT_TCL;
-- 세션 B
SELECT * FROM DEPT_TCL;
```
- 토드와 SQL*PLUS로 세션 알아보기
```sql
-- 세션 A
UPDATE DEPT_TCL SET LOC = 'SEOUL'
WHERE DEPTNO = 30;
-- 세션 B
-- 세션 A의 UPDATE명령이 끝날 때까지 기다리기
SELECT * FROM DEPT_TCL;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/063d3d4e-7cc7-4b7f-ba49-4de173bf3f1c/image.png)
![](https://velog.velcdn.com/images/mini_mouse_/post/4b635c3a-04ae-46f7-9c5e-9d77e386d88c/image.png)
- 토드와 SQL*PLUS로 세션 알아보기
```sql
-- 세션 A
작업X
-- 세션 B
UPDATE DEPT_TCL SET DNAME = 'DATABASE'
WHERE DEPTNO = 30;
```
- 위의 명령문을 입력하면, 세션 B는 아무런 동작이 일어나지 않는다. 화면이 멈춘듯 가만히 있는 것을 확인할 수 있다.

>- 이렇게 **특정 세션에서 데이터 조작이 완료될 때까지 다른 세션에서 해당 데이터 조작을 기다리는 현상**을 **HANG**(행)이라고 한다.

- 세션 B의 UPDATE문은 세션 A의 현재 트랜잭션이 종료되기 전까지는 수행되지 못한다.
즉, 세션 A에서 COMMIT으로 데이터 변경을 확정하여 반영하거나, ROLLBACK으로 세션 A의 UPDATE문 실행을 취소해야만 30번 부서 데이터의 LOCK이 풀린다.
- 세션 A의 데이터 변경이 확정되어 데이터의 LOCK이 풀리면, 그 즉시 세션 B는 UPDATE문을 실행한다.
- 토드와 SQL*PLUS로 세션 알아보기
```sql
-- 세션 A
COMMIT;
-- 세션 B
세션 A의 COMMIT 명령어가 실행되는 순간의 변화 확인 후 UPDATE문 실행
```
- 토드와 SQL*PLUS로 세션 알아보기
```sql
-- 세션 A
SELECT * FROM DEPT;
-- 세션 B
SELECT * FROM DEPT;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/fff05e1c-0846-4365-9dc0-c8d46075d939/image.png)
![](https://velog.velcdn.com/images/mini_mouse_/post/f0bd9c23-4930-4539-8ab2-467b8dc5beb9/image.png)
- 토드와 SQL*PLUS로 세션 알아보기
```sql
-- 세션 A

-- 세션 B
COMMIT;
```

### - LOCK의 종류
- **LOCK은** **하나의 데이터**를 **여러 곳에서 동시에 조작**하려 할 때 발생할 수 있는 **혼란을 최소화**하기 위한 중요한 요소이다.

>- **행 레벨 록**(ROW LEVEL LOCK)
SQL문으로 조작하는 대상 데이터가 테이블의 특정 행 데이터일 경우, 해당 행만 LOCK 이 발생한다는 의미

- 만약 **WHERE절을 지정하지 않은** UPDATE, DELETE문일 경우, 테이블의 **모든 행**에 영향을 주는 명령어이므로, 
이 경우는 테이블에 저장되어 있는 **전체 행이 LOCK** 상태가 된다.
⇒ 다른 세션에서는 해당 테이블에 이미 저장되어 있는 행에 UPDATE, DELETE 명령을 수행하기 위해서는 대기를 해야한다.
↔ 테이블 **전체 행이 LOCK 상태**여도 **INSERT문의 수행은 가능**하다.

>- **테이블 레벨 록**(TABLE LEVEL LOCK)
테이블에 변경되는 행의 수와는 상관없이,** 데이터 조작 명령어**(INSERT, DELETE, UPDATE, SELECT)를 사용하여 데이터가 변경 중인 테이블은 테이블 단위 잠금, 테이블 레벨 록이 걸릭 된다.

⇒ 데이터를 변경 중인 세션 외 다른 세션에서 데이터 정의어(DDL)을 통한 테이블의 구조를 변경할 수 없다.

_- DML시, DML에 따라 행, 또는 행 전체에 행 레벨 락 걸려 해당 행 DML 불가_
- DML시, 테이블 레벨 락 걸려, DDL 불가
- **데이터 조작 관련 SQL문(DML)**을 어떤 방식으로 작성하느냐에 따라 **테이블의 일부 데이터만 LOCK**이 될 수도 있고 **테이블 전체가 LOCK**이 될 수도 있다.