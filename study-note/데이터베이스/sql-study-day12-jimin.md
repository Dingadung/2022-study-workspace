## 08-1 조인
### - 집합 연산자와 조인의 차이점
- **조인(JOIN)**? **두 개 이상의 테이블**을 연결하여 **하나의 테이블**처럼 출력할 때 사용하는 방식
- 집합연산자를 사용한 결과와 비슷하다.
- 조인과 집합연산자의 차이점?
  - **집합연산자**: 두 개 이상의 SELECT문의 결과를 **세로**로 연결
  **↕**
  - **조인**: 두 개 이상의 테이블 데이털르 **가로**로 연결
 ** ↔**

### - 여러 테이블을 사용할 때의 FROM절
- FROM절에는 여러 개의 테이블을 지정하는 것이 가능하다.
- 테이블이 아니더라도, 테이블 형태, 즉 열과 행으로 구성된 데이터 집합이면 모두 FROM절에 지정이 가능하다.
→ 뷰(VIEW), 서브쿼리(SUBQUERY)등이 이에 해당된다.

- 지금까지의 SQL 문법 순서
```sql
SELECT 열1, 열2, ..., 열N
	FROM 테이블1, 테이블2, ..., 테이블 N
   WHERE 조건식
  GROUP BY 그룹식
  	HAVING 그룹조건식
  ORDER BY 정렬식
```

- 사원 정보와 더불어 근무 부서 이름 또는 부서 위치 정보 등을 한 번에 조회하고 싶다면, EMP테이블과 DEPT 테이블을 **조인**해서 출력해야 한다.
⇒ **FROM절에** EMP테이블과 DEPT테이블을 함께 명시해 주어야 한다.
```sql
SELECT *
    FROM EMP, DEPT
ORDER BY EMPNO;
```
- 그러나, 위에와 같이 **조인조건을 지정해 주지 않으면** 너무 많은 양의 데이터가 출력되게 된다.
  - 이는 FROM절에 명시한 각 테이블을 구성하는 행이 모든 경우의 수로 조합되어 출력되기 때문이다.
  - 이렇게 각 집합을 이루는 모든 원소의 순서쌍을 데카르트 곱(카테시안 곱: CARTESIAN PRODUCT)이라고 한다.
  - 조인 이름으로는 크로스 조인(CROSS JOIN) 또는 교차 조인이라고 한다.
  - 위의 예시에서는 EMP테이블의 14개 행 하나하나에 DEPT 테이블에 저장된 4개 행이 가로로 조합되어 출력된다.
  즉, 14*4, 총 56개의 행이 출력된다.

### - 조인 조건이 없을 때의 문제점
- 합치려는 테이블끼리의 데이터가 서로 정확히 맞아떨어지지 않는 경우에도 데이터가 함께 출력되게 된다.
- 명시한 테이블의 데이터를 가로로 연결하기 위해 조인을 사용하지만, **어떤 데이터를 가로로 정확히 연결해야 하는지의 기준**은 데이터베이스가 아닌 SQL문을 작성하는 프로그래머, **내가 정해주어야 한다**.
- 조인을 사용한 데이터 출력은 조인 대상 테이블이 많을수록 조합데이터 중 정확한 데이터만을 뽑아내기 위해 많은 고민을 해야 한다.
  - 이때, 출력 행을 선정하는 조건식을 명시하는 WHERE절이 중요한 역할을 한다.
- 서로 다른 테이블끼리의 같은 이름을 구별하는 방법은, 테이블 이름을 명시하여 특정 열이 어느 테이블에 속한 열인지 구별하는 방식이다.
```sql
테이블 이름.열 이름
```
- 열 이름을 비교하는 조건식으로 조인하기
```sql
SELECT *
    FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO
ORDER BY EMPNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/9d7f0b31-493a-44d2-b394-bddd42d2380b/image.png)

### - 테이블의 별칭 설정
- FROM절에 지정한 테이블에는 SELECT절의 열에 사용한 것처럼 별칭을 지정할 수 있다.
- 테이블에 별칭을 지정할 때는 명시한 테이블 이름에서 한 칸 띄운 후 지정한다.
```sql
FROM 테이블 이름1 별칭1, 테이블 이름2 별칭2 . . .
```
- 지정한 별칭은 테이블의 열을 지칭하는데 사용할 수 있다.
- 별칭은 출력 결과에 영향을 주지 않는다.
- 테이블 이름을 별칭으로 표현하기
```sql
SELECT *
    FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
ORDER BY EMPNO;
```

### - SELECT 절의 * 사용
- 모든 열을 노출하고 싶은 경우 * 을 사용하여 간편하게 모든 열을 출력할 수 있다.
- 그러나, 데이터베이스를 사용하는 웹 서비스, 모바일 앱 등 여러 응용 프로그램을 제작하는 프로그래밍 문장에서 SQL문을 사용할 때는 각 테이블의 모든 열을 출력할지라도 대부분 * 을 사용하지 않고 출력할 열을 하나하나 직접 명시해준다.
- SELECT절에서 출력할 열을 * 로 표현하면 어떤 열이 어떤 순서로 출력될 지 명확히 알 수 없을뿐만 아니라 특정 열이 새로 생기거나 삭제되거나 또는 어떤 이유로 인해 수정되었을 경우에 그 변화의 감지 및 변화에 따른 프로그램 수정이 쉽지 않을 수도 있기 때문이다.
- 따라서, SQL문은 급하게 데이터를 바로 조회하려는 경우가 아닌 이상, 데이터베이스를 사용하는 프로그램 내부에서는 다음과 같이 출력할 각 열을 하나하나 열거하여 표시한다.
```sql
SELECT E.EMPNO, E.ENAME, E.JOB, E.MGR, E.HIREDATE,
E.SAL, E.COMM, E.DEPTNO, 
D.DNAME, D.LOC
    FROM EMP E, DEPT D
WHERE D.DEPTNO = E.DEPTNO;
```

## 08-2 조인 종류
- 두 개 이상의 테이블을 하나의 테이블처럼 가로로 늘어뜨려 출력하기 위해 사용하는 조인은 **대상 데이터를 어떻게 연결**하느냐에 따라 등가조인, 비등가 조인, 자체 조인, 외부 조인 등으로 구분한다.
### - 등가 조인
- **등가조인(EQUI JOIN)**? 테이블을 연결한 후 **출력 행을 각 테이블의 특정 열에 일치한 데이터를 기준**으로 선정하는 방식
- 등가조인은 **내부조인(INNER JOIN)** 또는 **단순조인(SIMPLE JOIN)**으로 부르기도 한다.
- 등가조인은 일반적으로 가장 많이 사용되는 조인 방식이다.
- 따라서, 외부조인(OUTER JOIN)과 같이 이름을 특별히 명시하지 않으면, '조인을 사용한다'는 의미는 대부분 등가 조인, 즉 특정 열값이 일치한 출력 결과를 사용하는 방식이라고 보면 된다.
#### -- 여러 테이블의 열 이름이 같을 때 유의점
- 등가 조인을 사용할 때 조인 조건이 되는 각 테이블의 열 이름이 같을 경우, 해당 열 이름을 테이블 구분 없이 명시하면 다음과 같이 오류가 발생하여 실행되지 못한다.
- 두 테이블에 부서 번호가 똑같은 열 이름으로 포함되어 있을 때
```sql
SELECT EMPNO, ENAME, DEPTNO, DNAME, LOC
    FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/19a3cccf-c286-4454-a0cf-363bd6e99706/image.png)
- DEPTNO열은 두 테이블에 모두 존재하는 열이므로 어느 테이블에 속해 있는 열인지 반드시 명시해야 한다.
- 실무에서 SQL문을 사용할 때는 다소 번거롭더라도 **테이블끼리 겹치지 않는 열 이름이라도 대부분 테이블이나 별칭을 명시**한다.
-> 조인 테이블의 개수가 열 개를 넘기도 하고 각 테이블의 열 개수가 몇 십 개를 넘는 경우도 흔하기 때문이다.

#### -- WHERE절에 조건식을 추가하여 출력 범위 설정하기
- 출력 행을 더 제한하고 싶다면 WHERE절에 조건식을 추가로 지정해 줄 수 있다.
```sql
SELECT E.EMPNO, E.ENAME, E.DEPTNO, D.DNAME, D.LOC
    FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
    AND E.SAL >= 3000;
```

#### -- 조인 테이블 개수와 조건식 개수의 관계
- 조인에 대해 처음 설명할 때 조인 조건을 제대로 지정하지 않으면 데카르트 곱 때문에 정확히 연결이 되지 않아서 필요 없는 데이터까지 모두 조합되어 출력되는 경우가 있었다.
- **기본적으로 데카르트 곱 현상이 일어나지 않게 하는데 필요한 조건식의 최소 개수는 조인 테이블 개수에서 하나를 뺀 값이다.**
> WHERE절의 조건식을 사용해 테이블을 조인할 때,
반드시 각 테이블을 정확히 연결하는 조건식이 최소한 전체 테이블 수보다 하나 적은 수 많큼은 있어야 한다.
⇒ WHERE {조건식*(전체 테이블 수 -1)}

### - 비등가 조인
- **비등가 조인**(NON-EQUI JOIN)? 등가 조인 방식 외의 방식
- SALGRADE 테이블은 각 급여 등급의 기준이 되는 최소 금액 및 최대 금액을 저장하고 있는데,
사용하는 열의 일치 여부를 기준으로 테이블을 조인하는 등가 조인방식은
SALGRADE테이블과 EMP테이블을 연결하는데 적합하지 않다.
⇒ 급여 등급을 맞춰 주려면 사원의 급여 금액이 일치하는 것이 아니라 최소급여와 최대급여 사이에 있어야 하기 때문이다.
⇒ BETWEEN A AND B 연산자를 사용하여 조인을 처리한다.
```sql
SELECT E.EMPNO, E.ENAME, E.JOB, E.MGR, E.HIREDATE, E.SAL, E.COMM, E.DEPTNO, S.GRADE, S.LOSAL, S.HISAL
    FROM EMP E, SALGRADE S
WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/d3502195-2a3d-4b27-a4fe-d08bc8a20718/image.png)

- 비등가 조인 방식은 등가 조인 방식에 비해 그리 자주 사용하진 않지만, 조인 조건이 특정 열의 일치 여부를 검사하는 방식 외에 다른 방식도 사용할 수 있음을 기억해 놓는 것이 좋다.

### - 자체 조인
- **자체조인(SELF JOIN)**? **하나의 테이블**을 여**러 개의 테이블 처럼 활용**하여 조인하는 방식으로,
물리적으로 동일한 테이블 여러 개를 사용할 때 발생할 수 있는 문제점을 해결한다.
- 자체조인은 FROM절에 같은 테이블을 여러 번 명시하되, 테이블의 **별칭만 다르게** 지정하는 방식으로 사용한다.
  - 별칭을 각각 달리주어 논리적으로 다른 테이블인 것처럼 명시하여 두 테이블을 조인한다.
- EMP 테이블의 사원 정보와 해당 사원의 직속 상관의 사원 번호를 나란히 함께 출력해야 하는 경우, 자체 조인을 사용한다.
```sql
SELECT E.EMPNO,E.ENAME, E.MGR,
    E2.EMPNO AS MGR_EMPNO,
    E2.ENAME AS MGR_ENAME
    FROM EMP E, EMP E2
WHERE E.MGR = E2.EMPNO
ORDER BY E.EMPNO;
```
- 결과에 MGR열이 NULL인 KING은 제외되어 출력된다.
![](https://velog.velcdn.com/images/mini_mouse_/post/fc4326aa-2ead-44f8-b818-203132668de0/image.png)

### - 외부 조인
- **외부 조인**(**OUTER JOIN**)? 두 테이블 간 조인 수행에서 조인 기준 열의 **어느 한쪽이 NULL**이어도 **강제로 출력**하는 방식
- 조인 조건 데이터 중 어느 한쪽이 NULL임에도 결과를 출력해야 할 때 외부 조인을 이용한다.
- 외부 조인은 **좌우를 따로 나누어** 지정하는데 WHERE절에 조인 기준 열 중 한쪽에 (+) 기호를 붙여준다.
- ** (+) 붙인 열이 NULL이어도 출력해라 라는 뜻으로 이해하면 좋다.**
- 왼쪽 외부 조인: 왼쪽 열을 기준으로 오른쪽 열의 데이터 존재 여부에 상관 없이 출력하라
- 오른쪽 외부 조인: 오른쪽 열을 기준으로 왼쪽 열의 데이터 존재 여부에 상관 없이 출력하라

|왼쪽 외부 조인(LEFT OUTER JOIN)|WHERE TABLE1.COL1 = TABLE2.COL1(+)    |
|------------------------------|-------------------------------------------------|

|오른쪽 외부 조인(RIGHT OUTER JOIN)|WHERE TABLE2.CO1(+) = TABLE2.COL1|
|------------------------------|-------------------------------------------------|

- 왼쪽 외부 조인 사용하기: 직속 상관이 없어도 출력해라
```sql
SELECT E.EMPNO,E.ENAME, E.MGR,
    E2.EMPNO AS MGR_EMPNO,
    E2.ENAME AS MGR_ENAME
    FROM EMP E, EMP E2
WHERE E.MGR = E2.EMPNO(+)
ORDER BY E.EMPNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/80d9be57-74d1-4507-8ecd-45bfb40c03ec/image.png)

- 오른쪽 외부 조인 사용하기: 직속 부하 직원이 없어도 출력해라(제일 낮은 직급의 사원들도 출력하라)
```sql
SELECT E.EMPNO,E.ENAME, E.MGR,
    E2.EMPNO AS MGR_EMPNO,
    E2.ENAME AS MGR_ENAME
    FROM EMP E, EMP E2
WHERE E.MGR(+)= E2.EMPNO
ORDER BY E.EMPNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/0033730e-570b-4e9a-927a-7b7ff9c1586b/image.png)
- 그때 그때 (+)의 위치를 바꿔가며 지정하고 그 결과 값을 비교하여 의도한 결과가 나오는 쪽을 찾는 것도 한가지 방법이다.
- 외부 조인은 조인 기준 열의 NULL을 처리하는 것을 목적으로 자주 사용하는 조인 방식이다.
- 하지만, (+) 기호를 붙이는 외부 조인 방식으로는 양쪽 모든 열이 조인되는 전체 외부 조인(FULL OUTER JOIN) 사용은 불가능하다.
⇒ UNION 집합 연산자를 이용하면 FULL OUTER JOIN 효과를 낼 수는 있다.
![](https://velog.velcdn.com/images/mini_mouse_/post/af30ec53-5782-49fe-8f35-8ca15552b87d/image.png)

#### -- 외부조인, 내부조인 이름에 대해서
- 외부 조인을 사용하지 않는 등가, 자체 조인은 조인 조건에 해당하는 데이터가 존재할 경우에만 출력하기 때문에 외부 조인과 반대 의미로 '내부 조인'이라고 부른다.
- 외부 조인은 영문 그대로 OUTER JOIN이라고 더 많이 부른다.
- 면접 질문으로 많이 나오니 개념과 사용법에 유의하자.

## 08-3 SQL-99 표준 문법으로 배우는 조인
### - NATURAL JOIN
- **NATURAL JOIN**? **등가 조인**을 **대신**해서 사용할 수 있는 조인 방식
- 조인 대상이 되는 두 테이블에 **이름과 자료형이 같은 열**을 찾은 후, 그 열을 **기준**으로 등가 조인을 해주는 방식이다.
- NATURAL JOIN을 사용하여 조인하기
```sql
SELECT E.EMPNO, E.ENAME, E.JOB, E.MGR, 
	E.HIREDATE, E.SAL, E.COMM,
    DEPTNO, 
    D.DNAME, D.LOC
    FROM EMP E NATURAL JOIN DEPT D
ORDER BY DEPTNO, E.EMPNO;
```
- EMP테이블과 DEPT테이블은 공통 열 DEPTNO를 가지고 있으므로 NATURAL JOIN을 사용할 때 **자동**으로 DEPTNO열을 기준으로 등가 조인이 된다.

> 
- 기존 등가 조인과 다르게 **조인 기준열**을 SELECT절에 명시할 때 **테이블 이름**을 **붙이면 안되는** 특성이 있다.
- 조인 조건이 WHERE절에 있는 기존 조인 방식과 달리, SQL-99방식은 **FROM절**에 조인 키워드를 사용하는 형태로 작성한다.

### - JOIN ~ USING
- JOIN ~ USING 키워드? 기존 등가 조인을 대신하는 조인 방식
- NATURAL JOIN과 다르게 USING 키워드 () 안에 조인 기준으로 사용할 열을 명시하여 사용하낟.
```sql
FROM TABLE1 JOIN TABLE2 USING (조인에 사용한 기준열)
```
- 조인된 결과 행을 추가로 제한할 때, WHERE절에 조건식을 추가하여 함께 사용할 수 있다.

>- NATURAL JOIN과 마찬가지로 **조인 기준 열**로 명시된 열은 SELECT 절에서 **테이블 이름을 붙이지 않고** 작성한다.

- JOIN ~ USING을 사용하여 조인하기
```sql
SELECT E.EMPNO, E.ENAME, E.JOB, E.MGR, E.HIREDATE, E.SAL, E.COMM,
    DEPTNO, D.DNAME, D.LOC
    FROM EMP E JOIN DEPT D USING(DEPTNO)
    WHERE SAL >= 3000
ORDER BY DEPTNO, E.EMPNO;
```

### - JOIN ~ ON
- JOIN ~ ON? 가장 범용성 있는 조인 방식
- 기존 WHERE절에 있는 조인 조건식을 ON 키워드 옆에 작성한다.
- 조인 조건식은 ON에 명시하고, 그 밖의 출력행을 걸러내기 위하여 WHERE 조건식을 따로 사용하는 방식이다.
```sql
FROM TABLE1 JOIN TABLE2 ON (조인 조건식)
```
- JOIN ~ ON으로 등가 조인하기
```sql
SELECT E.EMPNO, E.ENAME, E.JOB, E.MGR, 
		E.HIREDATE, E.SAL, E.COMM, E.DEPTNO,
    	D.DNAME, D.LOC
       FROM EMP E JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
    WHERE SAL <= 3000
    ORDER BY E.DEPTNO, EMPNO;
```

### - OUTER JOIN
- **OUTER JOIN**? 외부 조인에 사용한다.
- 다른 SQL-99 방식의 조인과 마찬가지로 WHERE절이 아닌 **FROM절**에서 외부 조인을 선언한다.
- 기존 방식과의 차이점
  - **왼쪽** 외부 조인(**LEFT OURTER JOIN**)
    - 기존
    WHERE TABLE1.CO1 = TABLE2.COL1**(+)**
    - SQL-99
    FROM TABLE1 **LEFT** OUTER JOIN TABLE2 ON (조인 조건식)
  - **오른쪽** 외부 조인(**RIGHT OURTER JOIN**)
    - 기존
    WHERE TABLE1.CO1**(+)** = TABLE2.COL1
    - SQL-99
    FROM TABLE1 **RIGHT** OUTER JOIN TABLE2 ON (조인 조건식)
  - **전체** 외부 조인(**FULL OURTER JOIN**)
    - 기존
    X, 기본 문법 없음, UNION 집합 연산자 활용 가능
    - SQL-99
    FROM TABLE1 **FULL** OUTER JOIN TABLE2 ON (조인 조건식)
- 왼쪽 외부 조인을 SQL-99로 작성하기
```sql
SELECT E1.EMPNO, E1.ENAME, E1.MGR,
        E2.EMPNO AS MGR_EMPNO,
        E2.ENAME AS MGR_ENAME
    FROM EMP E1 LEFT OUTER JOIN EMP E2 ON (E1.MGR = E2.EMPNO)
ORDER BY E1.EMPNO;
```
- 오른쪽 외부 조인을 SQL-99로 작성하기
```sql
SELECT E1.EMPNO, E1.ENAME, E1.MGR,
        E2.EMPNO AS MGR_EMPNO,
        E2.ENAME AS MGR_ENAME
    FROM EMP E1 RIGHT OUTER JOIN EMP E2 ON (E1.MGR = E2.EMPNO)
ORDER BY E1.EMPNO;
```
- 전체 외부 조인은 SQL-99로 작성하기
```sql
SELECT E1.EMPNO, E1.ENAME, E1.MGR,
        E2.EMPNO AS MGR_EMPNO,
        E2.ENAME AS MGR_ENAME
    FROM EMP E1 FULL OUTER JOIN EMP E2 ON (E1.MGR = E2.EMPNO)
ORDER BY E1.EMPNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/58598fa9-9eba-4d4a-b3f9-c8460f5dd950/image.png)

⇒ **조인 조건식**과 **출력 행을 선정하는 조건식**을 **구별**할 수 있으므로 여러 테이블을 조인해야 하는 복잡한 SELECT문에서 SQL-99조인의 장점이 드러난다.

### - SQL-99 조인 방식에서 세 개 이상의 테이블을 조인할 때
- 기존 조인 방식
```sql
FROM TABLE1, TABLE2, TABLE3
WHERE 
	TABLE1.COL = TABLE2.COL
    AND
    TABLE2.COL = TABLE3.COL
```
- SQL-99 조인 방식
```sql
FROM TABLE1
			JOIN TABLE2 ON (조건식)
            JOIN TABLE3 ON (조건식)
```

---
## 8강 실습 문제
1.
- 기존 방식
```sql
SELECT D.DEPTNO, D.DNAME, E.EMPNO, E.ENAME, E.SAL
    FROM EMP E, DEPT D
    WHERE D.DEPTNO = E.DEPTNO
        AND SAL > 2000
ORDER BY D.DEPTNO;
```
- SQL-99 방식
```sql
SELECT DEPTNO, D.DNAME, E.EMPNO, E.ENAME, E.SAL
    FROM EMP E JOIN DEPT D USING(DEPTNO)
    WHERE SAL > 2000
ORDER BY DEPTNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/1b0ccd7a-40bf-4ca2-b7ec-c63c29fe6185/image.png)

2.
- 기존 방식
```sql
SELECT D.DEPTNO, D.DNAME, TRUNC(AVG(E.SAL)), 
	MAX(E.SAL), MIN(E.SAL), COUNT(*) AS CNT
    FROM EMP E, DEPT D
    WHERE E.DEPTNO = D.DEPTNO 
GROUP BY D.DEPTNO, D.DNAME
ORDER BY D.DEPTNO;
```
- SQL-99 방식
```sql
SELECT DEPTNO, D.DNAME, TRUNC(AVG(E.SAL)), 
	MAX(E.SAL), MIN(E.SAL), COUNT(*) AS CNT
    FROM EMP E NATURAL JOIN DEPT D
GROUP BY DEPTNO, D.DNAME
ORDER BY DEPTNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/91d97343-5cc8-4a40-897e-94a5144ba666/image.png)

3.
- 기존 방식
```sql
SELECT D.DEPTNO, D.DNAME, E.EMPNO, E.ENAME, E.JOB, E.SAL
    FROM EMP E, DEPT D
WHERE E.DEPTNO(+) = D.DEPTNO
ORDER BY E.DEPTNO, E.ENAME;
```
- SQL-99 방식
```sql
SELECT D.DEPTNO, D.DNAME, E.EMPNO, E.ENAME, E.JOB, E.SAL
    FROM EMP E RIGHT OUTER JOIN DEPT D ON E.DEPTNO = D.DEPTNO
ORDER BY E.DEPTNO, E.ENAME;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/034102dc-e3f3-430e-9d1d-55a4d06a87ac/image.png)

4.
- 기존 방식
```sql
SELECT D.DEPTNO, D.DNAME, 
        E.EMPNO, E.ENAME, E.MGR, E.SAL, E.DEPTNO, 
        S.LOSAL, S.HISAL, S.GRADE, 
        E.MGR AS MGR_EMPNO, E2.ENAME AS MGR_ENAME
    FROM EMP E ,DEPT D,
            EMP E2, SALGRADE S
    WHERE 
        E.DEPTNO(+) = D.DEPTNO
        AND
        E.SAL BETWEEN S.LOSAL(+) AND S.HISAL(+)
        AND 
        E.MGR = E2.EMPNO(+)
    ORDER BY D.DEPTNO, E.EMPNO;
```
- SQL-99 방식
```sql
SELECT D.DEPTNO, D.DNAME, 
        E.EMPNO, E.ENAME, E.MGR, E.SAL, E.DEPTNO, 
        S.LOSAL, S.HISAL, S.GRADE, 
        E.MGR AS MGR_EMPNO, E2.ENAME AS MGR_ENAME
    FROM EMP E 
            RIGHT OUTER JOIN DEPT D ON(E.DEPTNO = D.DEPTNO)
            LEFT OUTER JOIN EMP E2 ON(E.MGR = E2.EMPNO)
            LEFT OUTER JOIN SALGRADE S ON 
            			(E.SAL BETWEEN S.LOSAL AND S.HISAL)
    ORDER BY D.DEPTNO, E.EMPNO;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/32542fe6-ea8b-4a1c-a0e1-ae1a857ff774/image.png)

---
# 프로그래머스 문제
## SUM, MAX, MIN
1. 가장 최근에 들어온 동물은 언제 들어왔는지 조회하는 SQL 문을 작성해주세요.
```sql
SELECT MAX(DATETIME) AS "시간"
FROM ANIMAL_INS;
```
2. 동물 보호소에 가장 먼저 들어온 동물은 언제 들어왔는지 조회하는 SQL 문을 작성해주세요
```sql
SELECT MIN(DATETIME)
FROM ANIMAL_INS;
```
3. 동물 보호소에 동물이 몇 마리 들어왔는지 조회하는 SQL 문을 작성해주세요.
```sql
SELECT COUNT(*)
FROM ANIMAL_INS;
```
4. 동물 보호소에 들어온 동물의 이름은 몇 개인지 조회하는 SQL 문을 작성해주세요. 이때 이름이 NULL인 경우는 집계하지 않으며 중복되는 이름은 하나로 칩니다.
```sql
SELECT COUNT(DISTINCT NAME) AS COUNT
FROM ANIMAL_INS;
```

## GROUP BY
1. 고양이와 개는 몇 마리 있을까
```sql
SELECT ANIMAL_TYPE, COUNT(*)
FROM ANIMAL_INS
GROUP BY ANIMAL_TYPE
ORDER BY ANIMAL_TYPE;
```
2. 동명 동물 수 찾기
```sql
SELECT NAME, COUNT(*) AS COUNT
FROM ANIMAL_INS 
WHERE NAME IS NOT NULL
GROUP BY NAME HAVING COUNT(*) >=2
ORDER BY NAME;
```
3. 보호소에서는 몇 시에 입양이 가장 활발하게 일어나는지 알아보려 합니다. 09:00부터 19:59까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회하는 SQL문을 작성해주세요. 이때 결과는 시간대 순으로 정렬해야 합니다.
```sql
SELECT TO_CHAR(DATETIME, 'HH24') AS HOUR, COUNT(*) AS COUNT
FROM ANIMAL_OUTS
WHERE TO_CHAR(DATETIME, 'HH24') BETWEEN 9 AND 19
GROUP BY TO_CHAR(DATETIME, 'HH24')
ORDER BY TO_CHAR(DATETIME, 'HH24');
```
4. 보호소에서는 몇 시에 입양이 가장 활발하게 일어나는지 알아보려 합니다. 0시부터 23시까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회하는 SQL문을 작성해주세요. 이때 결과는 시간대 순으로 정렬해야 합니다.
```sql
SELECT H.HOUR, NVL(CORE.COUNT, 0)
FROM (SELECT (TO_CHAR(DATETIME, 'HH24')) AS HOUR, 
        COUNT(*) AS COUNT
    FROM ANIMAL_OUTS
    GROUP BY (TO_CHAR(DATETIME, 'HH24'))
    ORDER BY (TO_CHAR(DATETIME, 'HH24'))
) CORE,
    (SELECT LEVEL-1 AS HOUR FROM dual CONNECT BY LEVEL<=24) H
WHERE CORE.HOUR(+) = H.HOUR
ORDER BY H.HOUR;
```