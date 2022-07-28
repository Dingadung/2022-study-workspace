## 10-1 테이블에 데이터 추가하기
### - 테이블 생성하기
- 특정 테이블에 데이터를 새로 추가할 때 INSERT문을 사용한다.
- 실습 진행을 위한 테이블 → 기존 DEPT 테이블 복사해서 만들기
```sql
CREATE TABLE DEPT_TEMP
    AS SELECT * FROM DEPT;
```
- 위의 코드는, DEPT 테이블과 같은 열 구성으로 DEPT 테이블의 모든 행을 복사하여 DEPT_TEMP 테이블을 생성하라는 명령어 이다.
- CREATE문은 오라클의 구성요소, 즉 오브젝트를 만드는데 사용하는 DDL(DATA DEFINITION LANGUAGE) 명령어이다. (나중에 12강에서 다시 등장)

### - INSERT문 실습 전 유의점
#### -- 테이블을 잘못 만들었을 때
- 테이블을 잘못 만들었거나 지워야 할 경우에는 다음 명령어를 사용한다.
```sql
DROP TABLE 테이블 이름;
```
#### -- 실습하는 중에 프로그램이 종료되었을 때
- 만약 실습 도중 프로그램을 종료하면 경고창이 나타날 것인데, 이 경우에 COMMIT 버튼을 클릭하고 끝내면 된다.

### - 테이블에데이터를 추가하는 INSERT문
- 테이블에 데이터를 추가하는 데 사용하는 INSERT문은 다음과 같은 기본 형태로 작성한다.
```sql
INSERT INTO 테이블 이름[(열1, 열2, ..., 열N)]
VALUES(열1에 들어갈 데이터, 열2에 들어갈 데이터, ..., 열N에 들어갈 데이터);
```
- DEPT_TEMP 테이블에 데이터 추가하기
```sql
INSERT INTO DEPT_TEMP (DEPTNO, DNAME, LOC)
                VALUES (50, 'DATABASE', 'SEOUL');
```
#### -- INSERT문에 오류가 발생했을 때
- 만약 **INSERT문에서 지정한 열 개수**와 **각 열에 입력할 데이터 개수가 일치하지 않거나** **자료형이 맞지 않는 경우** 또는 **열 길이를 초과하는 데이터**를 지정하는 경우에는 INSERT문에 오류가 발생하여 실행이 되지 않게되므로 유의하여야 한다.
#### -- INSERT문으로 데이터 입력하기(열 지정을 생략할 때)
- INSERT문에 지정하는 **열은 생략할 수 있다**.
- 열을 생략하면 **해당 테이블을 만들 때 설정한 열 순서대로** 모두 나열되어 있다고 가정하고 데이터를 작성해야 한다.
- **열 개수나 자료형 및 길이**는 반드시 맞춰 줘야 한다.
- INSERT문에 열 지정 없이 데이터 추가하기
```sql
INSERT INTO DEPT_TEMP
 VALUES(60, 'NETWORK', 'BUSAN');
```

### - 테이블에 NULL 데이터 입력하기
- INSERT문으로 새로운 데이터를 추가할 때 특정 열에 들어갈 데이터가 확정되지 않았거나 굳이 넣을 필요가 없는 데이터인 경우에는 NULL을 사용한다.
- NULL을 INSERT문에 지정하는 방법
  1. NULL을 직접 명시적으로 입력해주는 방법
  2. 대상 열을 생략하여 암시적으로 NULL이 입력되도록 유도하는 방식
#### -- NULL을 직접 명시적으로 입력해주는 방법
- NULL을 지정하여 입력하기
```sql
INSERT INTO DEPT_TEMP
 VALUES(70, 'WEB', NULL);
```
- 해당 열의 자료형이 문자열 또는 잘짜형일 경우 밑에와 같이 빈공백 문자열을 사용해도 NULL을 입력할 수 있다.
- 빈 공백 문자열로 NULL을 입력하기
```sql
INSERT INTO DEPT_TEMP
 VALUES(80, 'MOBILE', '');
```
#### 실무에서는 NULL을 어떻게 입력할까?
- 실무에서는 NULL을 직접 명시해야 하는 경우, 공백 문자열인 작은따옴표를 사용하여 NULL을 입력하는 방식보다,
**NULL이란 단어를 정확히 입력하는 방식을 대부분 선호**한다.
→ 데이터베이스에 익숙하지 않은 개발자가 보았을 때 공백 문자열이 NULL로 들어가는 건지 헷갈릴 수 있기 때문이다.
- 또한, INSERT문에 열을 지정할 때 열을 생략하는 방식보다 **모든 열을 직접 명시하는 방법을 선호**한다.
→ INSERT문을 처음 작성할 때 조금 귀찮더라도, 명시적으로 모든 열을 작성해 놓으면 여러 개발자들이 따로 자료를 찾지 않고, INSERT문만 보아도 테이블에 포함된 열의 내용을 한눈에 알아볼 수 있어 이해하기 쉽기 때문이다.
#### -- NULL의 암시적 입력
- NULL의 암시적 입력 방식은 INSERT문에 NULL이 들어가야 할 열 이름을 아예 입력하지 않는 것이다.
- 밑의 예시와 같이 DNAME 열을 INSERT문에서 제외시키면, 자동으로 DNAME열은 NULL이 입력된다.
- 열 데이터를 넣지 않는 방식으로 NULL 데이터 입력받기
```sql
INSERT INTO DEPT_TEMP(DEPTNO, LOC)
 VALUES (90, 'INCHON');
```
### 테이블에 날짜 데이터 입력하기
- 실습에 필요한 테이블을, EMP 테이블을 복사해서 만들기
```sql
CREATE TABLE EMP_TEMP
    AS SELECT * FROM EMP
    WHERE 1<> 1;
```
---
#### -- 급하게 테이블을 복사해야 할 때
- 위에서 사용한 CREATE 명령어는 EMP 테이블과 같은 열 구조를 가지지만, 데이터는 복사하고 싶지 않을 때 종종 사용한다.
- WHERE절의 조건이 1<>1이기 때문에 각 행을 조건식에 대입한 결과값은 항상 FALSE가 되어 결과적으로 행은 만들어지지 않는다.
- 약간 억지스럽지만, **급하게 테이블 열 구조만 같은 테이블을 만들어야 할 경우에 유용하게 사용할 수 있다.**
---
- INSERT문으로 날짜 데이터 입력하기(날짜 사이에 / 입력)
  - YYYY/MM/DD 형식의 문자열 데이터로 입력
```sql
INSERT INTO EMP_TEMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
        VALUES (9999, '홍길동', 'PRESIDENT', NULL, '2001/01/01',
                5000, 1000, 10);
```
- INSERT문으로 날짜 데이터 입력하기(날짜 사이에 - 입력)
  - YYYY-MM-DD 형식의 문자열 데이터로 입력
```sql
INSERT INTO EMP_TEMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
    VALUES( 1111, '성춘향', 'MANAGER', 9999, '2001-01-05', 4000, NULL, 20);
```
- 날짜 데이터 형식을 반대로 했을 때
  - 년/월/일 순서와 반대로 일/월/년 순서로 데이터를 입력하면 오류가 발생하고 데이터가 입력되지 않는다.
```sql
INSERT INTO EMP_TEMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
    VALUES( 2111, '이순신', 'MANAGER', 9999, '07/01/2001', 4000, NULL, 20);
```
- 오류가 발생하는 이유: 오라클은 설치되어 있는 운영체제(OS)의 종류나 사용하는 기본 언어군에 따라 날짜 표기방식이 다르기 때문이다.
>⇒ 날짜 데이터를 INSERT문으로 입력할 때는 문자열로 날짜를 입력하지 않고, 다음 예제와 같이 TO_DATE 함수를 사용하는 것이 좋다.

- TO_DATE 함수를 사용하여 날짜 데이터 입력하기
```sql
INSERT INTO EMP_TEMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
    VALUES( 2111, '이순신', 'MANAGER', 9999, TO_DATE('07/01/2001', 'DD/MM/YYYY'), 4000, NULL, 20);
```
#### -- SYSDATE를 사용하여 날짜 데이터 입력하기
- **현재 시점으로 날짜를 입력**할 경우에는 다음과 같이 **SYSDATE**를 지정하여 간단히 처리할 수 있다.
- **SYSDATE방식은 데이터 입력 시점을 정확히 입력**할 수 있어 자주 사용하므로 기억해야한다!
- SYSDATE를 사용하여 날짜 데이터 입력하기
```sql
INSERT INTO EMP_TEMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
    VALUES( 3111, '심청이', 'MANAGER', 9999, SYSDATE, 4000, NULL, 30);
```
### - 서브쿼리를 사용하여 한 번에 여러 데이터 추가하기
- INSERT문에 서브쿼리를 사용하면 SELECT문으로 한 번에 여러 행의 데이터를 추가할 수 있다.
- 예시; EMP테이블에서 SALGRADE테이블을 참조하여 급여 등급이 1인 사원만을 EMP_TEMP테이블에 집어 넣고 싶을 때, 서브쿼리를 포함한 INSERT문을 다음과 같이 사용할 수 있다.
- 서브쿼리로 여러 데이터 추가하기
```sql
INSERT INTO EMP_TEMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
    SELECT E.EMPNO, E.ENAME, E.JOB, E.MGR, E.HIREDATE, E.SAL, E.COMM, E.DEPTNO
    FROM EMP E, SALGRADE S
    WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL
    AND S.GRADE = 1;
```
- INSERT문에서 서브쿼리를 사용할 때 유의할 점
  1. VALUES절은 사용하지 않는다.
  2. 데이터 추가되는 테이블의 열 개수와 서브쿼리의 열 개수가 일치해야한다.
  3. 데이터 추가되는 테이블의 자료형 개수와 서브쿼리의 자료형 개수가 일치해야한다.
  - INSERT 대상이 되는 테이블의 열 개수와 자료형만 맞춰준다면, INSERT문에서 사용하는 서브쿼리는 여러 개의 테이블을 **조인**한 결과일지라도 열 이름에 상관없이 데이터 추가가 가능하다.
- INSERT문은 지금까지 작성한 것 이외에도 ALL또는 FIRST등의 옵션으로 한 번에 여러 테이블을 대상으로 데이터를 추가하거나 특정 조건에 따라 다른 테이블에 데이터를 추가하는 등 다양하게 사용할 수 있다.
- MERGE문을 사용하면 같은 열 구조를 가지는 여러 테이블 또는 서브쿼리의 결과 데이터를 한 테이블에 병합하여 추가할 수 있다.

## 10-2 테이블에 있는 데이터 수정하기
- 오라클에서는 특정 테이블에 저장되어 있는 데이터 내용을 수정할 때 UPDATE문을 사용한다.
- DEPT 테이블을 복사해서 DEPT_TEMP2 테이블 만들기
```sql
CREATE TABLE DEPT_TEMP2
    AS SELECT * FROM DEPT;
```
### - UPDATE문의 기본 사용법
- 기본 형식
```sql
UPDATE [변경할 테이블]
SET [변경할 열1]=[데이터], [변경할 열2]=[데이터], ..., [변경할 열N]=[데이터]
[WHERE 데이터를 변경할 대상 행을 선별하기 위한 조건];
```
### - 데이터 전체 수정하기
- DEPT_TEMP2 테이블 업데이트 하기
```sql
UPDATE DEPT_TEMP2
    SET LOC = 'SEOUL';
```
- 위의 UPDATE문은 DEPT_TEMP2 테이블의 LOC열의 데이터를 모두 SEOUL로 수정하라는 내용이다.
- 하지만, UPDATE문을 사용하여 테이블에 저장되어 있는 모든 데이터의 특정 열 데이터를 위의 코드와 같이 일괄적으로 변경하는 경우는 흔치 않다.
- 대부분의 경우, 테이블에 저장되어 있는 몇몇 행만 선정하여 데이터를 수정하는 방식을 사용한다.
### - 수정한 내용을 되돌리고 싶을 때
- 실수로 UPDATE문을 실행했을 때 UPDATE문 실행을 취소하기 위해 ROLLBACK 명령문을 사용할 수 있다.
- ROLLBACK 실습을 실행하면 DEPT_TEMP2 테이블이 UPDATE 명령어를 실행한 이전 상태로 돌아간다.
- ROLLBACK으로 테이블 내용 이전 상태로 되돌리기
```sql
ROLLBACK;
```
- ROLLBACK 명령어
  - **TCL**(TRANSACTION CONTROL LANGUAGE)명령어 중 하나이다.
  - 정해진 시점 이후에 실행된, **DML** 명령어, INSERT, UPDATE, DELELTE의 **실행을 취소**하는 명령어 이다.
  - COMMIT 명령어와는 반대 의미이다.
### - 데이터 일부분만 수정하기
- UPDATE문에서는 **수정 대상 행을 선별하기 위해 WHERE절과 조건식**을 사용한다.
- DEPT_TEMP2 테이블에서 만약 40번 부서의 부서 이름을 DATABASE로 수정하고 지역을 SEOUL로 수정해야 한다면 다음과 같이 UPDATE문에 WHERE절을 추가할 수 있다.
- 테이블 데이터 중 일부분만 수정하기
```sql
UPDATE DEPT_TEMP2
    SET DNAME = 'DATABASE',
        LOC = 'SEOUL'
    WHERE DEPTNO = 40;
SELECT * FROM DEPT_TEMP2;
```

### - 서브쿼리를 사용하여 데이터 수정하기
#### -- 여러 열을 한 번에 수정하는 경우
- UPDATE문에서도 서브쿼리를 사용할 수 있다.
- 서브쿼리로 데이터 일부분 수정하기
```sql
UPDATE DEPT_TEMP2
    SET(DNAME, LOC) = (SELECT DNAME, LOC FROM DEPT WHERE DEPTNO=40)
    WHERE DEPTNO=40;
```
- 위의 UPDATE문은 서브쿼리인 열의 결과로서 메인쿼리의 DNAME, LOC 두 개 열 값을 한 번에 변경했다.
#### -- 열 하나하나를 수정하는 경우
- 열 하나하나에 서브쿼리를 적용하는 것도 가능하다.
- 콤마(,)로 구분한다.
```sql
UPDATE DEPT_TEMP2
    SET DNAME = (SELECT DNAME FROM DEPT WHERE DEPTNO=40),
        LOC = (SELECT LOC FROM DEPT WHERE DEPTNO=40)
WHERE DEPTNO=40;
```
- 주의할 점은 변경 열의 개수에 따라 서브쿼리에서 지정한 열 개수도 변하고 있다는 것이다.
- 즉, 당연히 서브쿼리에 나열한 열과 UPDATE문으로 변경할 열 개수나 자료형은 일치해야한다.
#### -- WHERE절에 서브쿼리를 사용하여 데이터를 수정하는 경우
- UPDATE문의 WHERE절에 서브쿼리 사용하기
```sql
UPDATE DEPT_TEMP2
    SET LOC = 'SEOUL'
    WHERE DEPTNO = (SELECT DEPTNO FROM DEPT_TEMP2 WHERE DNAME='OPERATIONS');
SELECT * FROM DEPT_TEMP2;
```
### - UPDATE문 사용할 때 유의점
- UPDATE문과 DELETE문은 테이블에 이미 존재하는 데이터를 수정하거나 삭제하는 기능을 수행하므로, SELECT문이나 INSERT문에 비해 위험성이 큰 명령어이다.
- 따라서, UPDATE문을 실행하기 전에, 
**UPDATE문의 WHERE절을 검증**하는 작업이 반드시 필요하다.
→ 변경해야 하는 행만 정확하게 선정해서 수정하는지 확인하는 것이다.
- 이를 위해, 해당 WHERE절을 UPDATE문에 넣어 실행하기 전에, **SELECT문에서 먼저 사용해 보는 것**만으로도 간단하게 확인할 수 있다.
- 예시;
```sql
UPDATE DEPT_TEMP2
SET DNAME = 'DATABASE',
    LOC = 'SEOUL'
WHERE DEPTNO = 40;
```
```sql
SELECT *
FROM DEPT_TEMP2
WHERE DEPTNO=40;
```
> 반드시, UPDATE문과 DELETE문을 실행하기 전에, 
SELECT문으로 WHERE절의 조건식이 정확한지 확인하는 습관을 길러야한다.

## 10-3 테이블에 있는 데이터 삭제하기
- DELETE문은 테이블에 있는 데이터를 삭제할 때 사용한다.
- EMP 테이블을 복사해서 EMP_TEMP2테이블 만들기
```sql
CREATE TABLE EMP_TEMP3
AS SELECT * FROM EMP;
```
- DELETE문의 기본 형식
```sql
DELETE [FROM] [테이블 이름]
[WHERE 삭제할 대상 행을 선별하기 위한 조건식];
```
- DELETE문에서 WHERE절을 사용하지 않으면 테이블의 전체 데이터가 모두 삭제된다.
⇒ 특정 행 데이터를 삭제하고 싶으면 WHERE절에 적절한 조건식을 명시해 주어야 한다.
### - 데이터를 일부부만 삭제하기
- WHERE절을 사용하여 직책이 MANAGER인 사원들만 삭제하기
```sql
DELETE EMP_TEMP3
WHERE JOB = 'MANAGER';
SELECT * FROM EMP_TEMP3;
```
### - 서브쿼리를 사용하여 데이터 삭제하기
- DELETE문 역시 WHERE절에 서브쿼리를 사용하는 것이 가능하다.
- WHERE절에 서브쿼리를 사용하여 데이터 일부만 삭제하기
```sql
DELETE FROM EMP_TEMP3
WHERE EMPNO 
IN (SELECT E.EMPNO 
FROM EMP_TEMP2 E, SALGRADE S 
WHERE E.SAL BETWEEN S.LOSAL 
		AND S.HISAL 
		AND S.GRADE=3
		AND DEPTNO=30);
```
### - 데이터 삭제하기
- **WHERE절이 없다는 것**은 해당 테이블에 삭제 대상 데이터를 특정 짓지 않기 때문에 **테이블의 모든 데이터가 삭제**된다.
- 특별한 경우를 제외하면, 이렇게 모든 데이터를 지우는 경우는 흔치 않다.
```sql
DELETE FROM EMP_TEMP3;
```

---
## 10강 실습문제
Q1.
```sql
INSERT INTO DPET2
(DEPTNO, DNAME, LOC)
VALUES(50, 'ORACLE', 'BUSAN');
INSERT INTO DPET2
(DEPTNO, DNAME, LOC)
VALUES(60, 'SQL', 'ILSAN');
INSERT INTO DPET2
(DEPTNO, DNAME, LOC)
VALUES(70, 'SELECT', 'INCHEON');
INSERT INTO DPET2
(DEPTNO, DNAME, LOC)
VALUES(80, 'DML', 'BUNDANG');
SELECT * FROM DPET2;
```
Q2.
```sql
INSERT INTO EMP2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
            VALUES(7201, 'TEST_USER1', 'MANAGER', 7788, '2016-01-02', 4500, NULL, 50);
            
INSERT INTO EMP2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
            VALUES(7202, 'TEST_USER2', 'CLERK', 7201, '2016-02-21', 1800, NULL, 50);
INSERT INTO EMP2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
            VALUES(7203, 'TEST_USER3', 'ANALYST', 7201, '2016-04-11', 3400, NULL, 60);
            
INSERT INTO EMP2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
            VALUES(7204, 'TEST_USER4', 'SALESMAN', 7201, '2016-05-31', 2700, 300, 60);
            
INSERT INTO EMP2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
            VALUES(7205, 'TEST_USER5', 'CLERK', 7201, '2016-07-20', 2600, NULL, 70);
            
INSERT INTO EMP2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
            VALUES(7206, 'TEST_USER6', 'CLERK', 7201, '2016-09-08', 2600, NULL, 70);
INSERT INTO EMP2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
            VALUES(7207, 'TEST_USER7', 'LECTURER', 7201, '2016-10-28', 2300, NULL, 80);
            
INSERT INTO EMP2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
            VALUES(7208, 'TEST_USER8', 'STUDENT', 7201, '2016-03-09', 1200, NULL, 80);
```
Q3.
```sql
UPDATE EMP2
SET DEPTNO = 70
WHERE SAL > (SELECT AVG(SAL) FROM EMP2 WHERE DEPTNO = 50 );
```
Q4.
```sql
UPDATE EMP2
SET SAL = SAL*1.1,
    DEPTNO = 80
WHERE HIREDATE > (SELECT MIN(HIREDATE) FROM EMP2 WHERE DEPTNO = 60);
```
Q5.
```sql
DELETE FROM EMP2 E1
WHERE E1.EMPNO IN (SELECT E.EMPNO
FROM EMP2 E, SALGRADE2 S
WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL
AND S.GRADE = 5);
```