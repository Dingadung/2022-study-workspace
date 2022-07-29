# 12강 데이터 정의어
## 12-1 객체를 생성, 변경, 삭제하는 데이터 정의어
>- **데이터 정의어**(**DDL**: Data Definition Language)?
데이터베이스 데이터를 보관하고 관리하기 위해 제공되는 여러 객체(object)의 **생성, 변경, 삭제** 관련 기능을 수행한다.

### - 데이터 정의어를 사용할 때 유의할 점
- 데이터 정의어는 데이터 조작어(DML, INSERT, UPDATE, DELETE, SELECT)와 달리 명령어를 수행하자마자 데이터 베이스에 수행한 내용이 바로 반영되는 특성이 있다.
⇒ **데이터 정의어**를 실행하면 **자동으로 COMMIT** 되기 때문에 이전에 사용한 데이터 조작어는 영구히 데이터베이스에 반영된다.
⇒ ROLLBACK을 통한 실행 취소가 불가하다는 것을 의미하므로, DML을 사용할 때는 주의를 기울여야 한다.
- 트랜잭션 시작 → DML들 → DDL 사용과 동시에 COMMIT 효과, ROLLBACK 불가 → 새로운 트랜잭션 시작
- 데이터 정의어의 종류
1. CREATE
   - 객체를 생성
2. ALTER
   - 생성된 객체를 변경
3. DROP
   - 객체를 삭제

## 12-2 테이블을 생성하는 CREATE
>- **CREATE문은** 오라클 **데이터베이스 객체를 생성**하는데 사용하는 명령어이다.

- CREATE TABLE 명령어는 테이블을 만들 때 사용하는 대표적인 데이터 정의어이다.
- 제약 조건(Constraint) 테이블을 생성할 때 각 열에 저장되는 데이터 특징을 더 구체적으로 지정할 수 있다.
- 기본 형식
  - 소유 계정 이름은 생략할 수 있다.
  → 계정 이름을 생략히게 되면, 현재 접속해 있는 계정 소유의 테이블이 만들어 진다.
```sql
CREATE TABLE 소유 계정.테이블 이름(
	열1 이름 열1 자료형,
    열2 이름 열2 자료형,
    ...
    열N 이름 열N 자료형
);
```
- 테이블 이름은 반드시 다음 규칙대로 지정해 주어야 한다.
  - 기본적으로 테이블 이름을 지정할 때는 대, 소문자 구별을 하지 않는데, 대, 소문자를 구별하여 테이블 이름을 지정할 때는 테이블 이름에 큰 따옴표(" ")를 사용한다.
  1. 테이블 이름은 문자로 시작해야 한다.
  2. 테이블 이름은 30BYTE 이하여야 한다.
  3. 같은 사용자 소유의 테이블 이름은 중복될 수 없다.
  4. 테이블 이름은 영문자(한글), 숫자(0-9), 특수문자 $, #, _을 사용할 수 있다.
  5. SQL키워드는 테이블 이름으로 사용할 수 없다.
- 열 이름 생성 규칙
  1. 열 이름은 문자로 시작해야 한다.
  2. 열 이름은 30BYTE 이하여야 한다.
  3. 한 테이블의 열 이름은 중복될 수 없다.
  4. 열 이름은 영문자(한글), 숫자(0-9), 특수문자 $, #, _을 사용할 수 있다.
  5. SQL키워드는 열 이름으로 사용할 수 없다.
  
### - 자료형을 각각 정의하여 새 테이블 생성하기
- CREATE 명령어를 사용하여 EMP 테이블과 같은 열 구조를 가지는 EMP_DDL 테이블 생성하기
- DESC 명령어를 사용하여 EMP_DDL 테이블과 EMP 테이블의 열 구조를 확인할 수 있다.
- 모든 열의 각 자료형을 정의해서 테이블 생성하기
```sql
CREATE TABLE EMP_DDL(
    EMPNO   NUMBER(4),
    ENAME   VARCHAR2(10),
    JOB     VARCHAR2(9),
    MGR     NUMBER(4),
    HIREDATE DATE, -- 길이 지정이 필요 없는 자료형이라 소괄호 사용X
    SAL     NUMBER(7, 2), -- 7: 총 자리수, 2: 소수점 자리수
    COMM    NUMBER(7, 2),
    DEPTNO  NUMBER(2)
);
```

### - 기존 테이블 열 구조와 데이터를 복사하여 새 테이블 생성하기
- 특정 테이블과 같은 열 구조로 테이블을 만들 때, 위와 같이 일일이 작성하지 않고, CREATE문에 서브쿼리를 활용하여 테이블을 생성하는 방법을 많이 사용한다.
- **CREATE문에** **서브쿼리를** 사용할 때 **AS** 키워드를 함께 사용한다.
- CREATE문은 DEPT 테이블과 같은 열 구조를 가지면서 DEPT 테이블의 데이터까지 그대로 저장한 DEPT_DDL 테이블을 만든다.
```sql
CREATE TABLE DEPT_DDL
    AS SELECT * FROM DEPT;
```

### - 기존 테이블 열 구조와 일부 데이터만 복사하여 새 테이블 생성하기
- 특정 테이블과 열 구조는 같되, 테이블 전체 데이터가 아닌 **일부 데이터**만 복사하여 만들어야 한다면, **서브쿼리에 WHERE절**을 사용하여 생성 테이블에 저장될 데이터를 조건식으로 지정할 수 있다.
- EMP테이블에서 30번 부서인 사원 데이터만 저장한 테이블을 생성하기
```sql
CREATE TABLE EMP_DDL_30
    AS SELECT * FROM EMP WHERE DEPTNO = 30;
```

### - 기존 테이블의 열 구조만 복사하여 새 테이블 생성하기
- 특정 테이블과 열 구성이 같되, 저장 데이터가 없는 빈 테이블을 생성하려면 WHERE절 조건식의 결과 값이 언제나 FALSE가 나오는 방법을 사용할 수 있다.
- CREATE문에 서브쿼리를 사용하는 방식은 자주 쓰인다.
- CREATE문에서 여러 테이블을 조인한 SELECT문을 이용할 수 있다.
- EMP테이블과 DEPT 테이블을 조인한 결과로 테이블을 생성하는 CREATE문
```sql
CREATE TABLE EMPDEPT_DDL
    AS SELECT E.EMPNO, E.ENAME, E.JOB, E.MGR, E.HIREDATE,
        E.SAL, E.COMM, D.DEPTNO, D.DNAME, D.LOC
    FROM EMP E, DEPT D
    WHERE 1<>1;
```
- 위의 결과 결과 테이블은 서브쿼리에서 지정한 열 구조를 가지지만, 데이터는 저장되지 않는다.
- 숫자 1과 숫자 1이 다른 값인가의 결과는 항상 FALSE이기 때문에 EMP테이블과 DEPT테이블을 조인한 모든 결과 행이 출력 대상에서 제외된다.

## 12-3 테이블을 변경하는 ALTER
>- **ALTER명령어**는 이미 생성된 오라클 데이터 베이스 객체를 **변경**할 때 사용한다.
- 테이블에 새 열을 **추가** 또는 **삭제**하거나, **열의 자료형** 또는 **길이**를 **변경**하는 등 **테이블 구조 변경**과 관련된 기능을 수행한다.

- 실습을 위해 EMP테이블 복사하여 EMP_ALTER테이블 생성하기
```sql
CREATE TABLE EMP_ALTER
    AS SELECT * FROM EMP;
```

### - 테이블에 열 추가하는 ADD
>- **ALTER TABLE 명령어와 ADD 키워드, 추가할 열 이름과 자료형을 명시**하면 테이블에 **새 열을 추가**할 수 있다.

- ALTER 명령어로 HP 열 추가하기
```sql
ALTER TABLE EMP_ALTER
    ADD HP VARCHAR(20);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/81f39fb1-fa3b-43d3-8265-8bbe881f80c9/image.png)

### - 열 이름을 변경하는 RENAME
- ALTER 명령어에 RENAME 키워드를 사용하면 테이블의 열 이름을 변경할 수 있다.
- ALTER 명령어로 HP 열 이름을 TEL로 변경하기
```sql
ALTER TABLE EMP_ALTER
    RENAME COLUMN HP TO TEL;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/4755bf3d-8335-4dea-9dac-be8933cab18b/image.png)

### - 열의 자료형을 변경하는 MODIFY
- 테이블의 특정 열의 자료형이나 길이를 변경할 때는 다음과 같이 MODIFY 키워드를 사용한다.
- **ALTER명령어로 열의 자료형과 길이를 변경**하는 것은 **테이블에 저장된 데이터에 문제가 생기지 않는 범위 내**에서만 허용된다.
- 즉, 길이를 늘이는 것은 괜찮지만, 길이를 줄이거나 기존 열의 자료형을 다른 자료형으로 변경하는 것은 **저장된 데이터 상태**에 따라 결정되는 것이다.
- ALTER 명령어로 EMPNO 열 길이 변경하기
```sql
ALTER TABLE EMP_ALTER
MODIFY EMPNO NUMBER(5);
```

### - 특정 열을 삭제할 때 사용하는 DROP
- 테이블의 특정 열을 삭제할 때 DROP 키워드를 사용한다.
- 열 삭제 → 해당 열의 데이터 삭제 => 신중해야함
- ALTER 명령어로 TEL 열 삭제하기
```sql
ALTER TABLE EMP_ALTER
DROP COLUMN TEL;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/ecda40e7-f018-4836-8e23-27696fd93368/image.png)

## 12-4 테이블 이름을 변경하는 RENAME
- 테이블 이름을 변경할 때는 RENAME명령어를 사용한다.
- 앞에서 생성한 EMP_ALTER 테이블 이름을 EMP_RENAME으로 변경하기
```sql
RENAME EMP_ALTER TO EMP_RENAME;
```

## 12-5 테이블의 데이터를 삭제하는 TRUNCATE
- TRUNCATE 명령어는 특정 테이블의 모든 데이터를 삭제한다.
- 데이터만 삭제하므로 테이블 구조에는 영향을 주지 않는다.
- EMP_RENAME 테이블의 데이터를 TRUNCATE 명령어로 삭제하기
```sql
TRUNCATE TABLE EMP_RENAME;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/5fc978bf-92f8-442c-85db-8c8d20acb0c0/image.png)
![](https://velog.velcdn.com/images/mini_mouse_/post/3b50e5e6-b9d1-4f84-965e-5285a4856af6/image.png)

#### --- TRUNCATE 명령어를 사용할 때 유의할 점
- 테이블의 데이터 삭제는 데이터 조작어 중 **WHERE절을 명시하지 않은 DELETE문**의 수행으로도 가능하다.
- 하지만, TRUNCATE는 데이터 정의어이기 때문에 ROLLBAACK이 되지 않는다는 점에서 DELETE문과 다르다.
⇒ 즉, 삭제 이후 복구할 수 없다.

## 12-6 테이블을 삭제하는 DROP
- DROP 명령어는 데이터베이스 객체를 삭제하는데 사용한다.
- 테이블이 삭제되므로 테이블에 저장된 데이터도 모두 삭제된다.
- EMP_RENAME 테이블 삭제하기
```sql
DROP TABLE EMP_RENAME;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/6ad69299-27f2-48a9-bb31-046215fb0f00/image.png)

- FLASHBACK 기능을 사용하여 DROP명령어로 삭제된 테이블을 복구할 수 있다.
(하지만 매우 특별한 상황에서만 사용되는 명령어임)

---
# 잊기 전에 한 번 더!
1. 다음 열 구조를 가지는 EMP_HW 테이블을 만들어 보세요
```sql
CREATE TABLE EMP_HW(
    EMPNO   NUMBER(4),
    ENAME   VARCHAR2(10),
    JOB     VARCHAR2(9),
    MGR     NUMBER(4),
    HIREDATE    DATE,
    SAL     NUMBER(7, 2),
    COMM    NUMBER(7, 2),
    DEPTNO  NUMBER(2)
);

```
2. EMP+HW 테이블에 BIGO 열을 추가해 보세요. BOGO  열의 자료형은 가변형 문자열이고 길이는 20입니다.
```sql
ALTER TABLE EMP_HW
ADD BIGO VARCHAR(20);
```
3. EMP -HW 테이블의 BIGO 열 크기를 30으로 변경해보세요
```sql
ALTER TABLE EMP_HW
MODIFY BIGO VARCHAR(30);
```
4. EMP_HW 테이블의 BIGO 열 이름을 REMARK로 변경해보세요
```sql
ALTER TABLE EMP_HW
RENAME COLUMN BIGO TO REMARK;
```
**5. EMP_HW 테이블에서 EMP 테이블의 데이터를 모두 저장해보세요. 단 REMARK 열은 NULL로 삽입합니다.**
```sql
INSERT INTO EMP_HW
    SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO, NULL FROM EMP;
```
6. 지금까지 사용한 EMP_HW 테이블을 삭제해보세요.
```sql
DROP TABLE EMP_HW;
```

---
---
---
# 프로그래머스
## STRING, DATE
1.
```sql
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
WHERE NAME IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
ORDER BY ANIMAL_ID;
```
2.
```sql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE UPPER(NAME) LIKE UPPER('%el%')
AND ANIMAL_TYPE = 'Dog'
ORDER BY NAME;
```
3.
```sql
SELECT ANIMAL_ID, NAME,
CASE
      WHEN SEX_UPON_INTAKE LIKE '%Intact%' THEN 'X'
      ELSE 'O' 
END AS 중성화
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;
```
4.
```sql
SELECT ANIMAL_ID, NAME
FROM (SELECT I.ANIMAL_ID, I.NAME
        FROM ANIMAL_INS I, ANIMAL_OUTS O
        WHERE I.ANIMAL_ID = O.ANIMAL_ID
        ORDER BY O.DATETIME - I.DATETIME DESC)
WHERE ROWNUM <=2;
```
5.
```sql
SELECT ANIMAL_ID, NAME, TO_CHAR(DATETIME, 'YYYY-MM-DD') AS 날짜
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;
```
## JOIN
1.
```sql
SELECT O.ANIMAL_ID, O.NAME
	FROM ANIMAL_INS I, ANIMAL_OUTS O
WHERE I.ANIMAL_ID(+) = O.ANIMAL_ID
	AND I.ANIMAL_ID IS NULL
ORDER BY O.ANIMAL_ID, O.NAME;
```
2.
```sql
SELECT O.ANIMAL_ID, O.NAME
	FROM ANIMAL_INS I, ANIMAL_OUTS O
WHERE I.ANIMAL_ID = O.ANIMAL_ID
	AND I.DATETIME > O.DATETIME
ORDER BY I.DATETIME;
```
3.
```sql
SELECT NAME, DATETIME
FROM(
    SELECT I.NAME, I.DATETIME
    FROM ANIMAL_INS I, ANIMAL_OUTS O
    WHERE I.ANIMAL_ID = O.ANIMAL_ID(+)
        AND O.ANIMAL_ID IS NULL
    ORDER BY I.DATETIME)
WHERE ROWNUM <=3;
```
4.
```sql
SELECT I.ANIMAL_ID, I.ANIMAL_TYPE, I.NAME 
FROM ANIMAL_INS I, ANIMAL_OUTS O
    WHERE I.ANIMAL_ID = O.ANIMAL_ID
    	AND I.SEX_UPON_INTAKE LIKE '%Intact%'
    	AND O.SEX_UPON_OUTCOME NOT LIKE '%Intact%'
ORDER BY I.ANIMAL_ID;
```