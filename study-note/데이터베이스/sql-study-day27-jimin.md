sql-study-day27-jimin
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