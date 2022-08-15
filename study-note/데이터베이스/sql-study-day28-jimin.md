sql-study-day28-jimin
# 19강-저장 서브프로그램
## 19-1 저장 서브프로그램
### - 저장 서브 프로그램이란?
- 익명 블록(anonymouse block):
  - 작성한 내용을 단 한 번 실행하는데 사용하는 앞에서 다룬 PL/SQL 블록
  - 이름이 정해져 있지 앟은 PL/SQL 블록
  - 익명 블록은 오라클에 저장되지 않기 때문에 한 번 실행된 뒤에 다시 실행하려면 PL/SQL 블록을 다시 작성하여 실행해야 한다.
  - 매번 이렇게 내용을 작성하는 것은 불편하기 떄문에 실행할 내용을 따로 파일(.sql)에 저장하여 실행하기도 하지만 오라클에 저장되는 것은 아니다.
- 그런데, PL/SQL로 만든 프로그램을 주기적으로 또는 필요할 때마다 여러 번 사용해야 하는 상황이 빈번히 발생한다.
- 이러한 경우, PL/SQL 프로그램을 오라클에 저장해 두면 필요할 때마다 수행할 내용을 작성하지 않고 실행할 수 있으므로 여러모로 편리하다.
- 저장 서브 프로그램(stored subprogram): 
  - 여러번 사용할 목적으로 이름을 지정하여 오라클에 저장해두는 PL/SQL 프로그램
  - 익명 블록과 달리 저장 서브 프로그램은 오라클에 저장하여 공유할 수 있으므로 메모리, 성능, 재사용성등의 여러 면에서 장점이 있다.
- 익명 블록과 저장 서브프로그램의 차이점

||익명 블록|저장 서브프로그램|
|-------|--------|---------------------------|
|이름|이름 없음|이름 지정|
|오라클 저장|저장할 수 없음|저장함|
|컴파일|실행할 때마다 컴파일|저장할 때 한 번 컴파일|
|공유|공유할 수 없음|공유하여 사용 가능|
|다른 응용 프로그램에서의 호출 가능 여부|호출할 수 없음|호출 가능|

- 오라클에서 용도에 따라 저장 서브 프로그램 구현하는 방식 세가지
→ 프로시저, 함수, 패키지, 트리거

|서브프로그램|용도|
|----------|-------------------------|
|저장 프로시저(stored procedure)|일반적으로 특정 처리 작업 수행을 위한 서브프로그램으로 SQL문에서는 사용할 수 없다.|
|저장 함수(stored function)|일반적으로 특정 연산을 거친 결과 값을 반환하는 서브프로그램으로 SQL문에서 사용할 수 있다.|
|패키지(Package)|저장 서브프로그램을 그룹화하는데 사용한다.|
|트리거(trigger)|특정 상황(이벤트)이 발생할 때 자동으로 연달아 수행할 기능을 구현하는 데 사용한다.|

## 19-2 프로시저
- 저장 프로시저(stored procedure): 특정 처리 작업을 수행하는데 사용하는 저장 서브프로그램
  - 용도에 따라 파라미터를 사용할 수도 있고, 사용하지 않을 수도 있다.
### - 파라미터를 사용하지 않는 프로시저
#### -- 프로시저 생성하기
- 작업 수행에 별다른 입력 데이터가 필요하지 않을 경우, 파라미터를 사용하지 않는 프로시저를 사용한다.
- CREATE [OR REPLACE] PROCEDURE를 사용해 만들 수 있다.
- 익명 블록과 마찬가지로 프로시저도 선언부, 실행부, 예외 처리부로 이루어져 있다.
- 프로시저의 기본 형식
```sql
CREATE [OR REPLACE] PROCEDURE 프로시저 이름
IS | AS
	선언부
BEGIN
	실행부
EXCEPTION
	예외 처리부
END [프로시저 이름];
```
- 프로시저 생성하기
```sql

```
#### -- SQL*PLUS로 프로시저 실행하기
- SQL*PLUS에서 실행할 때 다음과 같이 EXECUTE 명령어를 사용한다.
```sql
EXECUTE 프로시저 이름;
```
- 생성한 프로시저 실행하기
```sql
CREATE OR REPLACE PROCEDURE pro_noparam
IS
   V_EMPNO NUMBER(4) := 7788;
   V_ENAME VARCHAR2(10);
BEGIN
   V_ENAME := 'SCOTT';
   DBMS_OUTPUT.PUT_LINE('V_EMPNO : ' || V_EMPNO);
   DBMS_OUTPUT.PUT_LINE('V_ENAME : ' || V_ENAME);
END;
/
```
#### -- PL/SQL 블록에서 프로시저 실행하기
- 특정 PL/SQL블록에서 이미 만들어진 프로시저를 실행한다면, 밑에와 같이 실행부(BEGIN)에 실행할 프로시저 이름을 지정한다.
```sql
SET SERVEROUTPUT ON;

EXECUTE pro_noparam;
```
- 익명 블록에서 프로시저 실행하기
```sql
BEGIN
   pro_noparam;
END;
/
```
#### -- 프로시저 내용 확인하기
- 이미 저장되어 있는 프로시저를 포함하여 서브프로그램의 소스코드 내용을 확인하려면 USER_SOURCE 데이터 사전에서 조회한다.

|USER_SOURCE의 열|설명|
|-------------------------|---------------------------------------------|
|NAME|서브프로그램(생성 객체) 이름|
|TYPE|서브프로그램 종류(PROCEDURE, FUNCTINO 등)|
|LINE|서브프로그램에 작성한 줄 번호|
|TEXT|서브프로그램에 작성한 소스 코드|

- USER_SOURCE를 통해 프로시저 확인하기(토드)
```sql
SELECT *
  FROM USER_SOURCE
 WHERE NAME = 'PRO_NOPARAM';
```
- USER_SOURCE를 통해 프로시저 확인하기(SQL*PLUS)
```sql
SELECT TEXT
  FROM USER_SOURCE
 WHERE NAME = 'PRO_NOPARAM';
```
#### -- 프로시저 삭제하기
- DROP PROCEDURE 명령어로 프로시저를 삭제할 수 있다.
- 프로시저 삭제하기
```sql
DROP PROCEDURE PRO_NOPARAM;
```
- **이미 저장되어 있는 프로시저의 소스 코드를 변경**할 때
→ **CREATE OR REPLACE PROCEDURE** 명령어로 프로시저를 재생성한다.
- ALTER PROCEDURE는 프로시저의 소스 코드 내용을 **재컴파일하는** 명령어이므로 작성한 코드 내용을 **변경하지 않는다**.

### - 파라미터를 사용하는 프로시저
- 프로시저를 실행하기 위해 **입력 데이터가 필요한 경우**, 파라미터를 정의할 수 있다.
- 파라미터는 여러 개 작성할 수 있다.
- 파라미터 작성 기본 형식
```sql
CREATE [OR REPLACE] PROCEDURE 프로시저 이름
[(파라미터 이름1 [modes] 자료형 [ := | DEFUALT 기본값],
  파라미터 이름2 [modes] 자료형 [ := | DEFUALT 기본값],
  ...
  파라미터 이름N [modes] 자료형 [ := | DEFUALT 기본값],
)]
IS | AS
	선언부
BEGIN
	실행부
EXCEPTION
	예외 처리부
END [프로시저 이름];
```
- 파라미터를 지정할 때 사용하는 모드 → IN, OUT, IN OUT

|파라미터 모드|설명|
|--------------------|----------------------------------------|
|IN|지정하지 않으면 기본 값으로 프로시저를 호출할 떄 값을 입력 받는다.(입력, void)|
|OUT|호출할 떄 값을 반환한다.(입력X, return)|
|IN OUT|호출할 때 값을 입력받은 후 실행 결과 값을 반환한다.(입력, return)|

#### -- IN 모드 파라미터
- 프로시저 실행에 필요한 값을 직접 입력 받는 형식의 파라미터를 지정할 때 IN을 사용한다.
- IN은 기본값이기 때문에 생략이 가능하다.
- 프로시저에 파라미터 지정하기
```sql
CREATE OR REPLACE PROCEDURE pro_param_in
(
   param1 IN NUMBER,
   param2 NUMBER,
   param3 NUMBER := 3,
   param4 NUMBER DEFAULT 4
)
IS

BEGIN
   DBMS_OUTPUT.PUT_LINE('param1 : ' || param1);
   DBMS_OUTPUT.PUT_LINE('param2 : ' || param2);
   DBMS_OUTPUT.PUT_LINE('param3 : ' || param3);
   DBMS_OUTPUT.PUT_LINE('param4 : ' || param4);
END;
/
```
- 파라미터를 입력하여 프로시저 사용하기
```sql
EXECUTE pro_param_in(1,2,9,8);
```
- 기본 값이 지정되어 있는 상태라면 호출할 때 값을 지정하지 않아도 실행이 가능하다.
- 기본 값이 지정된 파라미터 입력을 제외하고 프로시저 사용하기
```sql
EXECUTE pro_param_in(1, 2);
```
- 프로시저를 호출 할 때 기본값이 지정되지 않은 파라미터 수보다 적은 수의 값을 지정하면 프로시저 실행은 실패하게 되어 유의해야한다.
- 기본값이 지정된 파라미터와 지정되지 않은 파라미터 순서가 섞여있다면 기본값이 지정되지 않은 파라미터까지 값을 입력해주어야 한다.
- 즉, 밑에와 같이 파라미터가 지정되어 있다면 프로시저를 실행할 때 최소한 세 개 값을 입력해 주어야 한다.
```sql
(
  param1 IN NUMBER,
  param2 NUMBER := 3,
  param3 NUMBER,
  param4 NUMBER DEFAULT 4
)
```
- 하지만, 파라미터 종류나 개수가 많아지면 이러한 방식은 다소 불편할 수 있다.
- 따라서 **=>** 를 사용하여 파라미터 이름에 직접 값을 대입하는 방식도 사용한다.
- 파라미터 이름을 활용하여 프로시저에 값 입력하기
```sql
EXECUTE pro_param_in(param1 => 10, param2 => 20);
```
- 파라미터에 값을 지정할 때의 세가지 지정 방식

|종류|설명|
|-------------------|---------------------------------------------------|
|위치 지정|지정한 파라미터 순서대로 값을 지정하는 방식|
|이름 지정|=> 연산자로 파라미터 이름을 명시하여 값을 지정하는 방식|
|혼합 지정|일부 파라미터는 순서대로 값만 지정하고 일부 파라미터는 => 연산자로 값을 지정하는 방식|

#### -- OUT 모드 파라미터
- OUT 모드를 사용한 파라미터는 프로시저 실행 후 호출한 프로그램으로 값을 반환한다.
- OUT 모드 파라미터 정의하기
  - 변수 두 개를 선언하여 pro_param_out 프로시저의 반환값을 대입하고 있다.
```sql
CREATE OR REPLACE PROCEDURE pro_param_out
(
   in_empno IN EMP.EMPNO%TYPE,
   out_ename OUT EMP.ENAME%TYPE,
   out_sal OUT EMP.SAL%TYPE
)
IS

BEGIN
   SELECT ENAME, SAL INTO out_ename, out_sal
     FROM EMP
    WHERE EMPNO = in_empno;
END pro_param_out;
/
```
- OUT 모드 파라미터 사용하기
```sql
DECLARE
   v_ename EMP.ENAME%TYPE;
   v_sal EMP.SAL%TYPE;
BEGIN
   pro_param_out(7788, v_ename, v_sal);
   DBMS_OUTPUT.PUT_LINE('ENAME : ' || v_ename);
   DBMS_OUTPUT.PUT_LINE('SAL : ' || v_sal);
END;
/
```
#### -- IN OUT 모드 파라미터
- IN OUT 모드로 선언한 파라미터는 IN, OUT으로 선언한 파라미터 기능을 동시에 수행한다.
- 즉, 갑슬 입력받을 때와 프로시저 수행 후 결과 값을 반환할 때 사용한다.
- IN OUT 모드 파라미터 정의하기
```sql
CREATE OR REPLACE PROCEDURE pro_param_inout
(
   inout_no IN OUT NUMBER
)
IS

BEGIN
   inout_no := inout_no * 2;
END pro_param_inout;
/
```
- IN OUT 모드 파라미터 사용하기
```sql
DECLARE
   no NUMBER;
BEGIN
   no := 5;
   pro_param_inout(no);
   DBMS_OUTPUT.PUT_LINE('no : ' || no);
END;
/
```
### - 프로시저 오류 정보 확인하기
- 프로시저를 생성할 때 발생하는 오류를 확인하는 방법
→ 이 방법은 다른 서브프로그램의 오류에도 똑같이 적용할 수 있다.
- 생성할 때 오류가 발생하는 프로시저 알아보기
```sql
CREATE OR REPLACE PROCEDURE pro_err
IS
   err_no NUMBER;
BEGIN
   err_no = 100;
   DBMS_OUTPUT.PUT_LINE('err_no : ' || err_no);
END pro_err;
/
```
#### -- SHOW ERRORS 명령어로 오류 확인하기
```sql
SHOW ERRORS;
```
- SHOW ERRORS 명령어는 줄여서 SHOW ERR로 사용할 수도 있다.
- 만약 최근에 발생한 프로그램의 오류가 아니라 특정 프로그램의 오류 정보를 확인하고 싶다면 다음과 같이 프로그램 종류과 이름을 추가로 지정하면 된다. → 프로시저로 생성한 경우 PROCEDURE를 붙여준다.
```sql
SHOW ERR 프로그램 종류 프로그램 이름;
SHOW ERR PROCEDURE pro_err;
```
#### -- USER_ERRORS로 오류 확인
- 토드 같은 응용 프로그램을 사용한다면, USER_ERRORS 데이터 사전을 조회하여 오류 정보를 확인할 수 있다.
```sql
SELECT *
  FROM USER_ERRORS
 WHERE NAME = 'PRO_ERR';
```
---
## 19-3 함수
- 오라클 함수는 크게 내장 함수(built-in function)와 사용자 정의 함수(user defined function)로 분류할 수 있다.
- 프로시저와 함수의 차이점

|특징|프로시저|함수|
|--------------|---------------------------|-------------------------|
|실행|EXECUTE 명령어 또른 다른 PL/SQL 서브 프로그램 내에서 호출하여 실행|변수를 사용한 EXECUTE 명령어 또는 다른 PL/SQL 서브프로그램에서 호출하여 실행하거나 SQL무네서 직접 실행 가능|
|파라미터 지정|필요에 따라 지정하지 않을 수도 있고, <br>여러 개를 지정할 수도 있으며 IN, OUT, IN OUT 세 가지 모드를 사용할 수 있다.|프로시저와  같게 지정하지 않을 수도 있고 여러 개 지정할 수도 있지만 IN 모드(또는 생략)만 사용|
|값의 반환|실행 후의 값의 반환이 없을 수도 있고, <br>OUT, IN OUT 모드의 파라미터 수에 따라 여러 개 값을 반환할 수 있다.|반드시 하나의 값을 반환해야 하며, <br>값의 반환은 프로시저와 달리 OUT, IN OUT 모드의 파라미터를 사용하는 것이 아니라 RETURN절과 RETURN문을 통해 반환|
- 함수는 프로시저와 달리 SQL문에서도 사용할 수 있다는 특징이 있다.
- 함수는 RETURN절과 RETURN문을 통해 **반드시 하나의 값**을 반환해야 한다.

### - 함수 생성하기
- 함수 생성은 프로시저와 마찬가지로 CREATE [OR REPLACE] 명령어와 FUNCTION 키워드를 명시하여 생성한다.
- 실행부의 RETURN문이 실행되면 함수 실행은 즉시 종료된다.
- 함수 생성 기본 형식
```sql
CREATE [OR REPLACE] FUNCTION 함수 이름
[(파라미터 이름1 [IN] 자료형1,
  파라미터 이름2 [IN] 자료형2,
  ...
  파라미터 이름N [IN] 자료형N
)]
RETURN 자료형
IS | AS
  선언부
BEGIN
  실행부
  RETURN (반환값);
ECXEPTION
  예외 처리부
END [함수 이름];
```
#### -- 한 발 더 나가기! - 함수의 파라미터에 OUT, IN OUT 모드 사용
- 함수에도 OUT, IN OUT 모드의 파라미터를 지정할 수 있다. 하지만 함수에 OUT, IN OUT모드의 파라미터가 지정되어 있으면 SQL문에서는 사용할 수 없는 함수가 된다.
⇒ 즉, 프로시저와 다를 바 없는 함수가 되는 것이다.
- 이러한 이유와 부작용 때문에 오라클에서도 함수 파라미터에 OUT, IN OUT 모드를 사용하지 말라고 권장한다.
- SQL문에 사용할 함수는 반환 값의 자료형을 SQL문에서 사용할 수 없는 자료형으로 지정할 수 없으며 트랜잭션을 제어하는 명령어(TCL, DDL) 또는 DML 명령어도 사용할 수 없다.
- 함수 생성하기
```sql
CREATE OR REPLACE FUNCTION func_aftertax(
   sal IN NUMBER
)
RETURN NUMBER
IS
   tax NUMBER := 0.05;
BEGIN
   RETURN (ROUND(sal - (sal * tax)));
END func_aftertax;
/
```
### - 함수 실행하기
- 생성된 함수는 익명 블록 또는 프로시저 같은 저장 서브 프로그램, SQL문에서 사용할 수 있다.
- PL/SQL로 함수를 실행할 때는, 함수 반환 값을 대입받을 변수를 필요로 한다.
#### -- PL/SQL로 함수 실행하기
- 함수는 실행 후 하나의 값을 반환하므로 PL/SQL로 구현한 프로그램 안에 반환 값을 받기 위한 변수를 선언하여 사용한다.
- PL/SQL에서 함수 사용하기
```sql
DECLARE
   aftertax NUMBER;
BEGIN
   aftertax := func_aftertax(3000);
   DBMS_OUTPUT.PUT_LINE('after-tax income : ' || aftertax);
END;
/
```
#### -- SQL문에서 함수 실행하기
- SQL문에서 제작한 함수를 사용하는 방식은 기존 오라클의 내장 함수와 같다.
- DUAL 테이블에 다음과 같이 값을 직접 입력하여 사용할 수 있다.
- SQL에서 함수사용하기
```sql
SELECT func_aftertax(3000)
  FROM DUAL;
```
- 함수에 테이블 데이터 사용하기
```sql
SELECT EMPNO, ENAME, SAL, func_aftertax(SAL) AS AFTERTAX
  FROM EMP;
```
### - 함수 삭제하기
- DROP FUNCTION 명령어를 사용하여 함수를 삭제한다.
- 함수 삭제하기
```sql
DROP FUNCTION func_aftertax;
```
## 19-4 패키지
- 패키지(package)는 업무나 기능 면에서 연관성이 높은 프로시저, 함수 등 여러 개의 PL/SQL 서브 프로그램을 하나의 논리 그룹으로 묶어 통합, 관리하는 데 사용하는 객체를 뜻한다.
  - 프로시저나 함수 등은 각각 개별 기능을 수행하기 위해 제작 후 따로 저장한다.
- 패키지를 사용하여 서브프로그램을 그룹화했을 때의 장점

|장점|설명|
|---------|---------------------------------------------------------------|
|모듈성|서브프로그램을 포함한 여러 PL/SQL 구성 요소를 모듈화 할 수 있다.<br>모듈성은 잘 묶어둔다는 뜻으로, 프로그램의 이해를 쉽게 하고 패키지 사이의 상호 작용을 더 간편하고 명료하게 해주는 역할을 한다. <br>즉, PL/SQL로 제작한 프로그램의 사용 및 관리에 큰 도움을 준다.|
|쉬운 응용 프로그램 설계|패키지에 포함할 서브 프로그램은 완벽하게 완성되지 않아도 정의가 가능하다.<br>이 때문에 전체 소스 코드를 다 작성하기 전에 미리 패키지에 저장할 서브프로그램을 지정할 수 있으므로 설계가 수월해진다.|
|정보 은닉|제작 방식에 따라 패키지에 포함하는 서브 프로그램의 외부 노출 여부 또는 접근 여부를 지정할 수 있다.<br>즉, 서브프로그램을 사용할 때 보안을 강화할 수 있다.|
|기능성 향상|패키지 내부에서는 서브프로그램 외에 변수, 커서, 예외 등도 각 세션이 유지되는 동안 선언해서 공용(public)으로 사용할 수 있다.<br>예를 들어 특정 커서 데이터는 세션이 종료되기 전까지 보존되므로 여러 서브프로그램에서 사용할 수 있다.|
|성능 향상|패키지를 사용할 때 패키지에 포함된 모든 서브프로그램이 메모리에 한 번에 로딩되는데,<br> 메모리에 로딩된 후의 호출은 디스크 I/O를 일으키지 않으므로 성능이 향상된다.|
⇒ 패키지는 PL/SQL 서브 프로그램의 제작, 사용, 관리, 보안, 성능 등에 좋은 영향을 끼친다.
### - 패키지 구조와 생성
- 패키지는 프로시저, 함수와 달리 보통 두 부분으로 나누어 제작한다.
1. 명세(specification)
2. 본문(body)
#### -- 패키지 명세
- 패키지 명세는 패키지에 포함할 변수, 상수, 예외, 커서 그리고 PL/SQL 서브프로그래을 선언하는 용도로 작성한다.
- 패키지 명세에 선언한 여러 객체는 패키지 내부뿐만 아니라 외부에서도 참조할 수 있다.
- 패키지 명세의 기본형식
```sql
CREATE [OR REPLACE] PACKAGE 패키지 이름
IS | AS
	서브프로그램을 포함한 다양한 객체 선언
END [패키지 이름];
```
- 다음 패키지 명세는 변수 한 개, 함수 한 개, 프로시저 두 개를 선언한다.
- 패키지 선언하기
```sql
CREATE OR REPLACE PACKAGE pkg_example
IS
   spec_no NUMBER := 10;
   FUNCTION func_aftertax(sal NUMBER) RETURN NUMBER;
   PROCEDURE pro_emp(in_empno IN EMP.EMPNO%TYPE);
   PROCEDURE pro_dept(in_deptno IN DEPT.DEPTNO%TYPE);
END;
/
```
- 이미 생성되어 있는 패키지 명세의 코드를 확인하거나 선언한 서브프로그램을 확인하려면 UER_SOURCE 데이터 사전을 조회하거나 DESC 명령어를 활용할 수 있다.
- 패키지 명세 확인하기(USER_SOURCE 데이터 사전으로 조회)
```sql
SELECT TEXT
  FROM USER_SOURCE
 WHERE TYPE = 'PACKAGE'
   AND NAME = 'PKG_EXAMPLE';
```
- 패키지 명세 확인하기(DESC 명령어로 조회)
```sql
DESC pkg_example;
```
#### -- 패키지 본문
- 패키지 본문에는 패키지 명세에서 선언한 서브프로그램 코드를 작성한다.
- 패키지 명세에 선언하지 않은 객체나 서브프로그램을 정의하는 것도 가능하다.
  - 패키지 본문에만 존재하는 프로그램은 패키지 내부에서만 사용할 수 있다.
- 패키지 본문 이름은 패키지 명세 이름과 같게 지정해야 한다.
- 패키지 본문 기본 형식
```sql
CREATE [OR REPLACE] PACKAGE BODY 패키지 이름
IS | AS
	패키지 명세에서 선언한 서브프로그램을 포함한 여러 객체를 정의
    경우에 따라 패키지 명세에 존재하지 않는 객체 및 서브프로그램도 정의 가능
END [패키지 이름];
```
- 패키지 본문 생성하기
```sql
CREATE OR REPLACE PACKAGE BODY pkg_example
IS
   body_no NUMBER := 10;

   FUNCTION func_aftertax(sal NUMBER) RETURN NUMBER
      IS
         tax NUMBER := 0.05;
      BEGIN
         RETURN (ROUND(sal - (sal * tax)));
   END func_aftertax;

   PROCEDURE pro_emp(in_empno IN EMP.EMPNO%TYPE)
      IS
         out_ename EMP.ENAME%TYPE;
         out_sal EMP.SAL%TYPE;
      BEGIN
         SELECT ENAME, SAL INTO out_ename, out_sal
           FROM EMP
          WHERE EMPNO = in_empno;

         DBMS_OUTPUT.PUT_LINE('ENAME : ' || out_ename);
         DBMS_OUTPUT.PUT_LINE('SAL : ' || out_sal);
   END pro_emp;

PROCEDURE pro_dept(in_deptno IN DEPT.DEPTNO%TYPE)
   IS
      out_dname DEPT.DNAME%TYPE;
      out_loc DEPT.LOC%TYPE;
   BEGIN
      SELECT DNAME, LOC INTO out_dname, out_loc
        FROM DEPT
       WHERE DEPTNO = in_deptno;

      DBMS_OUTPUT.PUT_LINE('DNAME : ' || out_dname);
      DBMS_OUTPUT.PUT_LINE('LOC : ' || out_loc);
   END pro_dept;
END;
/
```
#### -- 서브프로그램 오버로드
- 기본적으로 서브프로그램 이름은 중복될 수 없다.
→ 그러나, 몇몇 경우에 중복이 가능하다.
- 서브프로그램 오버로드(subprogram overload): 
  - 같은 패키지에서 사용하는 파라미터의 개수, 자료형, 순서가 다를 경우에 한해서만 이름이 같은 서브프로그램을 정의하는 것
  - 서브프로그램 오버로드는 보통 같은 기능을 수행하는 여러 서브프로그램이 입력 데이터를 각각 다르게 정의할 때 사용한다.
- 서브프로그램 종류가 같아야 오버로드가 가능하다.
⇒ 즉, 특정 프로시저를 오버로드할 때 반드시 이름이 같은 프로시저로 정의해야 한다.
- 프로시저와 이름이 같은 함수를 정의할 수는 없다.
- 서브프로그램 오버로드 기본 형식
```sql
CREATE [OR REPLACE] PACKAGE 패키지 이름
IS | AS
	서브프로그램 종류 서브프로그램 이름(파라미터 정의);
    서브프로그램 종류 서브프로그램 이름(개수나 자료형, 순서가 다른 파라미터 정의);
END [패키지 이름];
```
- 프로시저 오버로드하기
```sql
CREATE OR REPLACE PACKAGE pkg_overload
IS
   PROCEDURE pro_emp(in_empno IN EMP.EMPNO%TYPE);
   PROCEDURE pro_emp(in_ename IN EMP.ENAME%TYPE);
END;
/
```
- 패키지 본문에서 오버로드된 프로시저 작성하기
```sql
CREATE OR REPLACE PACKAGE BODY pkg_overload
IS
   PROCEDURE pro_emp(in_empno IN EMP.EMPNO%TYPE)
      IS
         out_ename EMP.ENAME%TYPE;
         out_sal EMP.SAL%TYPE;
      BEGIN
         SELECT ENAME, SAL INTO out_ename, out_sal
           FROM EMP
          WHERE EMPNO = in_empno;

         DBMS_OUTPUT.PUT_LINE('ENAME : ' || out_ename);
         DBMS_OUTPUT.PUT_LINE('SAL : ' || out_sal);
      END pro_emp;

   PROCEDURE pro_emp(in_ename IN EMP.ENAME%TYPE)
      IS
         out_ename EMP.ENAME%TYPE;
         out_sal EMP.SAL%TYPE;
      BEGIN
         SELECT ENAME, SAL INTO out_ename, out_sal
           FROM EMP
          WHERE ENAME = in_ename;

         DBMS_OUTPUT.PUT_LINE('ENAME : ' || out_ename);
         DBMS_OUTPUT.PUT_LINE('SAL : ' || out_sal);
      END pro_emp;

END;
/
```
### - 패키지 사용하기
- 패키지를 통해 그룹화된 변수, 상수, 예외, 커서 그리고 PL/SQL 서브프로그램은 패키지 이름과 마침표(.)와 사용할 객체 이름으로 사용할 수 있다.
- 패키지에 포함된 서브프로그램 실행하기
```sql
BEGIN
   DBMS_OUTPUT.PUT_LINE('--pkg_example.func_aftertax(3000)--');
   DBMS_OUTPUT.PUT_LINE('after-tax:' || pkg_example.func_aftertax(3000));

   DBMS_OUTPUT.PUT_LINE('--pkg_example.pro_emp(7788)--');
   pkg_example.pro_emp(7788);

   DBMS_OUTPUT.PUT_LINE('--pkg_example.pro_dept(10)--' );
   pkg_example.pro_dept(10);

   DBMS_OUTPUT.PUT_LINE('--pkg_overload.pro_emp(7788)--' );
   pkg_overload.pro_emp(7788);

   DBMS_OUTPUT.PUT_LINE('--pkg_overload.pro_emp(''SCOTT'')--' );
   pkg_overload.pro_emp('SCOTT');
END;
/
```
### - 패키지 삭제하기
- 두 가지 방식을 사용하여 패키지를 삭제할 수 있다.
```sql
패키지 명세와 본문을 한 번에 삭제하기
DROP PACKAGE 패키지 이름;
```
```sql
패키지의 본문만을 삭제
DROP PACKAGE BODY 패키지 이름;
```
- 패키지 명세와 본문을 한 번에 삭제하거나 패키지 본문만 삭제할 수도 있다.
- 하지만, 패키지에 포함된 서브프로그램을 따로 삭제하는 것은 불가능하다.
  - CREATE OR REPLACE문을 활용하여 패키지 안의 객체 또는 서브프로그램을 수정 및 삭제할 수 있다.
---