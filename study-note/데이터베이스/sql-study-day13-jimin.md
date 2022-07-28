# 오라클로 배우는 데이터베이스 입문/9강-SQL문 속 또 다른 SQL문, 서브쿼리
## 09-1 서브쿼리
### - 서브쿼리란?
- **서브쿼리**(SUBQUERY)? SQL문을 실행하는 데 필요한 데이터를 추가로 조회하기 위해 **SQL내부에서 사용하는 SELECT문**을 의미한다.
- **메인쿼리**(MAINQUERY)? 서브 쿼리의 결과 값을 사용하여 기능을 수행하는 영역
- WHERE절의 조건식에 서브쿼리를 사용하는 것이 가장 빈번하게 사용하는 방식이다.
- EMP 테이블에서 JONES보다 급여가 높은 사원을 조회하고 싶은 경우
  - 먼저 필요한 데이터는 JONES의 급여 -> 기준 급여가 얼마인지 알아야 그보다 높은 급여를 받는 사원을 조회할 수 있기 때문이다.
```sql
SELECT SAL
    FROM EMP
WHERE ENAME = 'JONES';
```
![](https://velog.velcdn.com/images/mini_mouse_/post/f3f769b2-53c2-4446-aa1d-66b64d305475/image.png)
위의 결과를 보면 JONES의 급여는 2975이므로 이제 기준 급여 값을 알았으므로 이 급여보다 높은 급여를 받는 사원 데이터를 알 수 있다.
```sql
SELECT *
    FROM EMP
WHERE SAL > 2975;
```
- JONES의 급여보다 높은 급여를 받는 사원을 찾기 위해 먼저 총 **두 개**의 SELECT문을 작성했는데, **서브쿼리**를 사용하면 이 **두 개의 SELECT문을 하나의 SELECT문**으로 합쳐서 사용할 수 있다.
```sql
SELECT *
    FROM EMP
WHERE SAL > (
            SELECT SAL
                FROM EMP
            WHERE ENAME = 'JONES');
```
![](https://velog.velcdn.com/images/mini_mouse_/post/60443c05-04a2-448c-886d-21fcde233db7/image.png)

- 서브 쿼리 작성의 핵심은 주어진 문제를 어떻게 SELECT문으로 나누어 처리할지를 결정하는데에 있다.

### - 서브쿼리의 특징
> 1. 서브쿼리는 연산자와 같은 비교 또는 조회 대상의 **오른쪽**에 놓이며 괄호 ()로 묶어서 사용한다.
2. 특수한 몇몇 경우를 제외한 대부분의 서브쿼리에서는 ORDER BY절을 사용할 수 없다.
3. 서브쿼리의 SELECT절에 명시한 열은 메인쿼리의 비교 대상과 **같은 자료형**과 **같은 개수**로 지정해야 한다.
4. 서브 쿼리에 있는 SELECT문의 결과 행 수는 함께 사용하는 메인쿼리의 **연산자 종류와 호환 가능**해야 한다.

- 서브쿼리는 **메인쿼리의 연산자와 함께 상호작용**하는 방식에 따라 크게 **단일행 서브쿼리**와 **다중행 서브쿼리**로 나뉜다.

## 09-2 실행 결과가 하나인 단일행 서브쿼리
- **단일행 서브쿼리**(SINGLE-ROW SUBQUERY)? **실행 결과가 단 하나의 행**으로 나오는 서브쿼리를 뜻한다.
- 서브쿼리에서 출력되는 **결과가 하나**이므로 메인쿼리와 서브쿼리 결과는 다음과 같이 **단일행 연산자**를 사용하여 비교한다.
- 단일행 연산자

|>|>=|=|<=|<|<>|^=|!=|
|-|-|-|-|-|-|-|-|

- 데이터가 여러 개 존재하는 열의 경우에는 단일행 연산자를 사용할 수 없으므로 주의해야한다.
- 데이터가 여러 개 존재하는 열을 서브쿼리의 결과로 반환하는 경우에는 다중행 서브쿼리를 사용해야한다.

### - 단일행 서브쿼리와 날짜형 데이터
- 단일행 서브쿼리는 서브쿼리 결과 값이 **날짜(DATE)자료형**일 때도 사용할 수 있다.
- EMP 테이블에서 SCOTT보다 빨리 입사한 사원 목록을 조회하려면 다음과 같이 서브쿼리를 활용한 SELECT문을 작성할 수 있다.
```sql
SELECT *
FROM EMP E
WHERE E.HIREDATE < (SELECT HIREDATE
                            FROM EMP
                            WHERE ENAME = 'SCOTT');
```

### - 단일형 서브쿼리와 함수
- 서브쿼리에서 **특정 함수**를 사용한 **결과 값이 하나**일 때 역시 단일행 서브쿼리로서 사용 가능하다.
- 그룹화 함수(다중행 함수)의 결과 비교는 서브쿼리를 이용하여 진행해야한다.
따라서 밑에의 쿼리는 오류가 나게 된다.
```sql
WHERE E.SAL > AVG(SAL) 
```
- 20번 부서에 속한 사원 중 전체 사원의 평균 급여보다 높은 급여를 받는 사원 정보와 소속 부서 정보를 함께 조회하는 경우를 서브쿼리 암에 함수를 사용하여 출력 하기
```sql
SELECT E.EMPNO, E.ENAME, E.JOB, E.SAL, D.DEPTNO, D.LOC
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
    AND E.DEPTNO = 20
    AND SAL > (SELECT AVG(SAL)
                FROM EMP);
```
- 조인과 서브쿼리를 함께 사용하는 SQL문은 실무에서 자주 사용한다.

## 09-3 실행 결과가 여러 개인 다중행 서브쿼리
- **다중행 서브쿼리**(MULTIPLE-ROW SUBQUERY)? **실행 결과** **행이 여러 개**로 나오는 서브쿼리를 가르킨다.
- 단일행 서브쿼리와 달리 서브쿼리 결과가 여러 개이므로 **단일행 연산자는 사용할 수 없고** **다중행 연산자**를 사용해야 메인쿼리와 비교할 수 있다.
- 다중행 연산자의 종류

|다중행 연산자|설명|
|------------|---------------------------|
|**IN**|메인쿼리의 데이터가 서브쿼리의 결과 중 **하나라도** 일치한 데이터가 있다면 TRUE|
|**ANY**, **SOME**|메인쿼리의 조건식을 만족하는 서브쿼리의 **결과가 하나 이상**이면 TRUE|
|**ALL**|메인쿼리의 조건식을 서브쿼리의 결과 **모두가** 만족하면 TRUE|
|**EXISTS**|서브쿼리의 결과가 **존재**하면(즉, 행이 1개 이상일 경우) TRUE|

### IN 연산자
- IN 연산자 사용하기
```sql
SELECT *
     FROM EMP
     WHERE DEPTNO IN(30, 20);
```
- 각 부서별 최고 급여를 받는 사원을 조회하는 경우, 부서별 최고 급여 데이터를 먼저 구하고, 이 데이터와 일치하는 메인쿼리 데이터를 IN 연산자를 통해 선별할 수 있다.
```sql
SELECT *
    FROM EMP
    WHERE SAL IN (
            SELECT MAX(SAL)
                FROM EMP
                GROUP BY DEPTNO);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/2be7460a-3196-4ec2-bb99-446261e499da/image.png)

- SELECT문 결과 값이 밑에와 같이 2850, 3000, 5000이고 IN 연산자를 사용해 메인쿼리에서는 세 값 중 일치하는 값을 가진 행만 출력한다.
```sql
SELECT MAX(SAL)
    FROM EMP
    GROUP BY DEPTNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/6379af92-1972-42f0-85e6-397efe86d14b/image.png)

- 다중행 연산자 중 IN 연산자는 가장 자주 사용하는 연산자이므로 사용법을 반드시 기억해 놓아야 한다!
### - ANY, SOME 연산자
- ANY, SOME 연산자는 서브쿼리가 반환한 여러 결과 값 중 메인쿼리와 조건식을 사용한 결과가 **하나라도 TRUE**라면 **메인쿼리 조건식을 TRUE로 반환**해주는 연산자이다.
- ANY 및 SOME 연산자를 등가 비교 연산자(=)와 함께 사용하면,
IN연산자와 정확히 같은 기능을 수행한다.
  - IN과 같은 효과를 내어야 할 때 사실상 =ANY를 사용하는 경우는 거의 없다.
  IN 연산자가 알아보기도 편하고, 글자 수도 적기 때문에 거의 대부분 IN 연산자를 사용한다.
- ANY연산자 사용하기
```sql
SELECT *
    FROM EMP
    WHERE SAL = ANY(
            SELECT MAX(SAL)
                FROM EMP
                GROUP BY DEPTNO);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/f6eab36a-e6b5-4a1e-9af9-aaef3a893de4/image.png)

- SOME연산자 사용하기
```sql
SELECT *
    FROM EMP
    WHERE SAL = SOME(
            SELECT MAX(SAL)
                FROM EMP
                GROUP BY DEPTNO);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/0c421482-2650-402e-ba5a-e6b8c1235325/image.png)

- 대소 비교 연산자와 ANY연산자를 함께 사용하는 경우
  - 30번 부서 사원들의 최대 급여보다 적은 급여를 받는 사원 정보 출력하기
  ```sql
SELECT *
    FROM EMP
    WHERE SAL < ANY(
            SELECT SAL
                FROM EMP
                WHERE DEPTNO = 30)
ORDER BY SAL, EMPNO;
  ```
![](https://velog.velcdn.com/images/mini_mouse_/post/a06d4b0a-51fe-4e5f-9ea9-1f6001f88344/image.png)

- 위의 결과를 보면, 서브쿼리 결과 값 중 **최대값(SAL=2850)보다 작은 값**은 모두 출력 대상이 된다.
- 따라서 **< ANY** 연산자는 서브쿼리에 **MAX함수를 적용한 값**을 ANY연산자 없이 비교 연산자(**<**)만 사용한 결과와 같은 효과를 낼 수 있다.
>- 즉, < + ANY: 서브쿼리의 결과 값 중, 가장 큰 값보다 작으면 출력
> /> + ANY: 서브쿼리의 결과 값 중, 가장 작은 값보다 작으면 출력
> **< + ANY 연산자**를 사용한 경우 == ** < + MAX 함수**를 사용한 경우

  - **< ANY 연산자**를 사용한 경우
  ```sql
SELECT *
FROM EMP
WHERE SAL < (SELECT MAX(SAL)
            FROM EMP
            WHERE DEPTNO = 30)
ORDER BY SAL, EMPNO;
  ```
  - 서브쿼리에** MAX 함수**를 사용한 경우
  ```sql
SELECT *
FROM EMP
WHERE SAL < (SELECT MAX(SAL)
            FROM EMP
            WHERE DEPTNO = 30)
ORDER BY SAL, EMPNO;
  ```
### - ALL 연산자
- ANY 및 SOME과 달리 ALL연산자는 서브쿼리의 모든 결과가 조건식에 맞아 떨어져야만 메인쿼리의 조건식이 TRUE가 되는 연산자 이다.
- 부서 번호가 30번인 사원들의 최소 급여보다 더 적은 급여를 받는 사원 출력하기
```sql
SELECT *
    FROM EMP
    WHERE 
    SAL < ALL(SELECT SAL
         FROM EMP
         WHERE DEPTNO= 30);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/47dbb432-84ee-467b-a996-514bf2128f96/image.png)

- ALL연산자가 의미하는 것과 같이 서브쿼리의 모든 결과 값(950, 1250, 1500, 1600, 2850)보다 작은 값을 가진 메인쿼리의 행만 TRUE가 되어 출력된다.
- 반대로 > ALL을 사용하는 경우, 최종 결과 값은 서브쿼리의 값(950, 1250, 1500, 1600, 2850) 중 가장 큰 값인 2850과 비교하여 큰 값을 가진 데이터만 출력된다.
>- 즉, < + ALL: 서브쿼리의 결과 값 중, 가장 작은 값보다 작으면 출력
/> + ALL: 서브쿼리의 결과 값 중, 가장 큰 값보다 작으면 출력
- 부서 번호가 30번인 사원들의 최대 급여보다 더 많은 급여를 받는 사원 출력하기
```sql
SELECT *
    FROM EMP
    WHERE 
    SAL > ALL(SELECT SAL
         FROM EMP
         WHERE DEPTNO= 30);
```

### EXISTS 연산자
- EXISTS 연산자는 조금 특이한 연산자로, **서브쿼리에 결과 값이 하나 이상 존재하면 조건식이 모두 TRUE**, **존재하지 않으면 모두 FALSE**가 되는 연산자이다.
- 다음 예시의 경우, 서브쿼리의 결과 값이 존재하기 때문에 EMP의 모든 행이 출력되는 것을 확인할 수 있다.
```sql
SELECT *
    FROM EMP
    WHERE EXISTS ( SELECT DNAME
                    FROM DEPT
                    WHERE DEPTNO = 10);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/d877e96c-027e-4fa4-b9c4-58d1d0f44d1d/image.png)

- 만약 DEPT **테이블에 존재하지 않는 조건**, DEPTNO=50을 서브쿼리로 실행하면 **결과 데이터로 아무 행도 출력되지 않는 것**을 확인할 수 있다.
```sql
SELECT *
    FROM EMP
    WHERE EXISTS ( SELECT DNAME
                    FROM DEPT
                    WHERE DEPTNO = 50);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/875f6a07-8633-4179-b83f-b37ef9d0d08c/image.png)

- EXISTS 연산자는 다른 다중행 연산자에 비해 그리 자주 사용되는 편은 아니지만, **특정 서브쿼리 결과 값의 존재 유무를 통해 메인쿼리의 데이터 노출 여부를 결정**해야 할 때 간혹 사용한다.
- 하지만 일반적으로 많이 사용하는 방식은 아니므로 EXISIS 연산자가 존재한다는 정도만 기억해도 큰 문제는 없다.

## 09-4 비교할 열이 여러 개인 다중열 서브쿼리
- **다중열 서브쿼리**(MULTIPLE-COLUMN SUBQUERY)는 **서브쿼리의 SELECT절에 비교할 데이터를 여러 개 지정**하는 방식이다.
- 다음과 같이 **메인쿼리**에 **비교할 열을 괄호로 묶어 명시**하고 **서스쿼리에서는 괄호로 묶은 데이터와 같은 자료형 데이터**를 **SELECT절에 명시**하여 사용할 수 있다.
- 다중열 서브쿼리는 복수열 서브쿼리라고도 불린다.
- 다중열 서브쿼리 사용하기
```sql
SELECT *
    FROM EMP
    WHERE (DEPTNO, SAL)
        IN (SELECT DEPTNO, MAX(SAL) FROM EMP
            GROUP BY DEPTNO);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/8df65471-6734-49a4-83ce-06556d76a5da/image.png)

- 다중열 서브쿼리는 실무에서 유용하게 쓰이는 경우가 꽤 많다.

## 09-5 FROM절에 사용하는 서브쿼리와 WITH절
- **FROM절**에도 서브쿼리를 사용할 수 있다.
- **FROM절에 사용하는 서브쿼리**를 **인라인 뷰**(INLINE VIEW)라고 부른다.
- 인라인 뷰는 특정 테이블 전체 데이터가 아닌 SELECT문을 통해 **일부 데이터를 먼저 추출**해 온 후 **별칭을 주어** 다음과 같이 이용할 수 있다.
- 인라인 뷰 이용하기
```sql
SELECT *
    FROM (SELECT * FROM EMP WHERE DEPTNO =10) E10,
        (SELECT * FROM DEPT) D
    WHERE E10.DEPTNO = D.DEPTNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/ded484ea-9ffe-49e8-838c-bfee84ae70c2/image.png)

- FROM절에 직접 테이블을 명시하여 사용하기에는,
테이블 내 데이터 규모가 너무 크거나,
현재 작업에 불필요한 열이 너무 많아,
일부 행과 열만 사용하고자 할 때 유용하다.
- 하지만, FROM절에 너무 많은 서브쿼리를 지정하면,
가독성이나 성능이 떨어질 수 있기 때문에 WITH절을 사용하기도 한다.
- WITH 기본 형식
```sql
WITH
[별칭1] AS (SELECT문 1),
[별칭2] AS (SELECT문 2),
...
[별칭N] AS (SELECT문 N),
SELECT
	FROM 별칭1, 별칭2, 별칭3
 ...
```
- WITH절 사용하기
```sql
WITH
E10 AS (SELECT * FROM EMP WHERE DEPTNO =10) ,
D AS (SELECT * FROM DEPT)
SELECT E10.EMPNO, E10.ENAME, E10.DEPTNO, D.DNAME, D.LOC
FROM E10, D
WHERE E10.DEPTNO = D.DEPTNO;
```
- 실제 수행해야 하는 메인쿼리와 서브쿼리를 분류할 때 꽤 유용하게 사용할 수 있다.
#### -- 상호 연관 서브쿼리
- **상호 연관 서브쿼리**(CORRELATED SUBQUERY)? 메인쿼리에 사용한 데이터를 서브쿼리에 사용하고, 서브쿼리의 결과 값을 다시 메인쿼리로 돌려주는 방식
- 성능을 떨어뜨리는 원인이 될 수도 있고, 사용빈도가 높지는 않아 밑의 SELECT문을 이용해 이런식으로 이용할 수 있다 정도만 기억하면 좋다.
```sql
SELECT *
FROM EMP E1
WHERE SAL > (SELECT MIN(SAL)
			FROM EMP E2
            WHERE E2.DEPTNO = E1.DEPTNO)
ORDER BY DEPTNO, SAL;
```
## 09-6 SELECT절에 사용하는 서브쿼리
- 서브쿼리는 **SELECT절**에도 사용할 수 있다.
- 스칼라 서브쿼리 (SCALAR SUBQUERY)라고 부른다.
- SELECT절에 **하나의 열 영역**으로서 결과를 출력할 수 있다.

> - **SELECT절에 명시하는 서브쿼리**는 반드시 **하나의 결과**만 **반환**하도록 작성해 주어야 한다!

- SELECT절에 서브쿼리 사용하기
```sql
SELECT EMPNO, ENAME, JOB, SAL,
    (SELECT GRADE
     FROM SALGRADE
    WHERE E.SAL BETWEEN LOSAL AND HISAL) AS SALGRADE,
    DEPTNO,
    (SELECT DNAME
        FROM DEPT D
    WHERE E.DEPTNO = D.DEPTNO) AS DNAME
FROM EMP E;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/f5809ff5-7f2a-4c47-936b-b8852302750b/image.png)

---
## 9강 실습문제
1.
```sql
SELECT E.JOB, E.EMPNO, E.ENAME, E.SAL,
    D.DEPTNO, D.DNAME
FROM EMP E, DEPT D
WHERE E.JOB = (SELECT JOB FROM EMP WHERE ENAME = 'ALLEN')
    AND E.DEPTNO = D.DEPTNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/5ba502c0-2363-44c2-b7cb-7cef29eefea7/image.png)

2.
```sql
SELECT E.EMPNO, E.ENAME, D.DNAME, E.HIREDATE, D.LOC, E.SAL,
    (SELECT GRADE FROM SALGRADE S WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL) AS GRADE
    FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
    AND SAL > (SELECT AVG(SAL) FROM EMP)
ORDER BY E.SAL DESC, E.EMPNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/6f8ccc26-7797-4132-8fbe-935b3dc0b7dc/image.png)

3.
```sql
SELECT E.EMPNO, E.ENAME, E.JOB, D.DEPTNO, D.DNAME, D.LOC
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
AND E.DEPTNO =10
AND E.JOB NOT IN(SELECT JOB FROM EMP WHERE DEPTNO=30);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/ecb86deb-594c-4eac-88e3-d54250574993/image.png)

4.
- 다중행 함수 사용 X
```sql
SELECT E.EMPNO, E.ENAME, E.SAL, S.GRADE
FROM EMP E, SALGRADE S
WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL
AND SAL > (SELECT MAX(SAL) FROM EMP WHERE JOB = 'SALESMAN')
ORDER BY E.EMPNO;
```
- 다중행 함수 사용 O
```sql
SELECT E.EMPNO, E.ENAME, E.SAL, S.GRADE
FROM EMP E, SALGRADE S
WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL
AND SAL > ALL(SELECT SAL FROM EMP WHERE JOB = 'SALESMAN')
ORDER BY E.EMPNO;

```
![](https://velog.velcdn.com/images/mini_mouse_/post/ea87b054-6143-41ea-a27d-b328bccaf794/image.png)