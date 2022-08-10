sql-study-day26-jimin
# 18강-커서와 예외처리
## 18-1 특정 열을 선택하여 처리하는 커서
### - 커서란?
- 커서(cursor)는 SELECT문 또는 데이터 조작어 같은 SQL문을 실행했을 때 해당 SQL문을 처리하는 정보를 저장한 메모리 공간을 말한다.
  - 메모리 공간은 Private SQL Area라고 부른다.
  - 커서는 이 메모리의 포인터를 말한다.
- 커서를 사용하면 실행된 SQL문의 결과 값을 사용할 수 있다.
  - 예를 들면, SELECT문의 결과 값이 여러 행으로 나왔을 때 각 행별로 특정 작업을 수행하도록 기능을 구현하는 것이 가능하다.
  ⇒ 오라클에 저장된 데이터 활용을 극대화할 수 있는 강력한 기능이다.
- 커서는 사용 방법에 따라 명시적 커서와 묵시적 커서(암시적 커서)로 나뉜다.

### - SELECT INTO 방식
- SELECT INTO문은 조회되는 데이터가 단 하나의 행일 떄 사용 가능한 방식이다.
- 커서는 결과 행이 하나이든 여러 개이든 상관없이 사용할 수 있다.
- SELECT INTO 방식의 기본 형식
```sql
SELECT 열1, 열2, ..., 열n INTO 변수1, 변수2, ..., 변수n
FROM ...
```
- SELECT INTO를 사용한 단일행 데이터 저장하기
```sql
DECLARE
   V_DEPT_ROW DEPT%ROWTYPE;
BEGIN
   SELECT DEPTNO, DNAME, LOC INTO V_DEPT_ROW
     FROM DEPT
    WHERE DEPTNO = 40;
   DBMS_OUTPUT.PUT_LINE('DEPTNO : ' || V_DEPT_ROW.DEPTNO);
   DBMS_OUTPUT.PUT_LINE('DNAME : ' || V_DEPT_ROW.DNAME);
   DBMS_OUTPUT.PUT_LINE('LOC : ' || V_DEPT_ROW.LOC);
END;
/
```
- 데이터 조회의 결과 값은 하나인 경우보다 여러 개인 경우가 더 흔하며 결과 행이 하나일지 여러 개일지 알 수 없는 경우도 존재하므로 대부분 커서를 활용한다.

### - 명시적 커서
- 명시적 커서는 사용자가 직접 커서를 선언하고 사용하는 커서를 뜻한다.
- 명시적 커서의 사용 단계
1. 커서 선언(declaration)
사용자가 직접 이름을 지정하여 사용할 커서를 SQL문과 함께 선언한다.
2. 커서 열기(open)
커서를 선언할 때 작성한 SQL문을 실행한다. 이때 실행한 SQL문에 영향을 받는 행을 active set이라 한다.
3. 커서에서 읽어온 데이터 사용(fetch)
실행된 SQL문의 결과 행 정보를 하나씩 읽어 와서 변수에 저장한 후, 필요한 작업을 수행한다.
각 행별로 공통 작업을 반복해서 실행하기 위해 여러 종류의 LOOP문을 함께 사용할 수 있다.
4. 커서 닫기(close)
모든 행의 사용이 끝나고 커서를 종료한다.
- 위 단계에 따라 PL/SQL문에서 명시적 커서를 작성하는 기본 형식은 다음과 같다.
```sql
DECLARE
	CURSOR 커서이름 IS SQL문; -- 커서 선언(Declaration)
BEGIN
		OPEN 커서 이름;		-- 커서 열기(Open)
        FETCH 커서 이름 INTO 변수 -- 커서로부터 읽어온 데이터 사용(Fetch)
        CLOSE 커서 이름;	-- 커서 닫기(Close)
END;
```
- 커서에 여러 행이 조회되는 SELECT문을 사용할 때 필요에 따라 다양한 LOOP문을 활용하여 각 행에 필요한 작업을 반복 수행할 수 있다.

#### -- 하나의 행만 조회되는 경우
- 하나의 행만이 조회되는 SELECT문을 커서로 지정하여 사용할 경우 SELECT INTO문을 사용할 때보다 복잡한 여러 단계를 작성해야 하므로 다소 번거로워 보일 수 있다.
→ 커서의 효용성은 조회되는 행이 여러 개일 때 극대화된다.
- 단일행 데이터를 저장하는 커서 사용하기
```sql
DECLARE
   -- 커서 데이터를 입력할 변수 선언
   V_DEPT_ROW DEPT%ROWTYPE;

   -- 명시적 커서 선언(Declaration)
   CURSOR c1 IS
      SELECT DEPTNO, DNAME, LOC
        FROM DEPT
       WHERE DEPTNO = 40;

BEGIN
   -- 커서 열기(Open)
   OPEN c1;

   -- 커서로부터 읽어온 데이터 사용(Fetch)
   FETCH c1 INTO V_DEPT_ROW;

   DBMS_OUTPUT.PUT_LINE('DEPTNO : ' || V_DEPT_ROW.DEPTNO);
   DBMS_OUTPUT.PUT_LINE('DNAME : ' || V_DEPT_ROW.DNAME);
   DBMS_OUTPUT.PUT_LINE('LOC : ' || V_DEPT_ROW.LOC);

   -- 커서 닫기(Close)
   CLOSE c1;

END;
/
```
#### -- 여러 행이 조회되는 경우 사용하는 LOOP문
- 커서에 지정한 SELECT문이 여러 행을 결과 값을 가질 경우에 여러 방식의 LOOP문을 사용할 수 있다.
- 여러 행의 데이터를 커서에 저장하여 사용하기(LOOP문 사용)
```sql
DECLARE
   -- 커서 데이터를 입력할 변수 선언
   V_DEPT_ROW DEPT%ROWTYPE;

   -- 명시적 커서 선언(Declaration)
   CURSOR c1 IS
      SELECT DEPTNO, DNAME, LOC
        FROM DEPT;

BEGIN
   -- 커서 열기(Open)
   OPEN c1;

   LOOP
      -- 커서로부터 읽어온 데이터 사용(Fetch)
      FETCH c1 INTO V_DEPT_ROW;

      -- 커서의 모든 행을 읽어오기 위해 %NOTFOUND 속성 지정
      EXIT WHEN c1%NOTFOUND;

      DBMS_OUTPUT.PUT_LINE('DEPTNO : ' || V_DEPT_ROW.DEPTNO
                        || ', DNAME : ' || V_DEPT_ROW.DNAME
                        || ', LOC : ' || V_DEPT_ROW.LOC);
   END LOOP;

   -- 커서 닫기(Close)
   CLOSE c1;

END;
/
```
- 커서에 설정할 수 있는 속성

|속성|설명|
|-------------------------|------------------------------------|
|커서 이름%NOTFOUND|수행된 FETCH문을 통해 추출된 행이 있으면 false, 없으면 true를 반환한다.|
|커서 이름%FOUND|수행된 FETCH문을 통해 추출된 행이 있으면 true, 없으면 false를 반환한다.|
|커서 이름%ROWCOUNT|현재까지 추출된 행 수를 반환한다.|
|커서 이름%ISOPEN|커서가 열려(open) 있으면 true, 닫혀(close) 있으면 false를 반환한다.|

#### -- 여러 개의 행이 조회되는 경우(FOR LOOP문)
- 커서에서 FOR LOOP문을 사용하는 기본 형식
```sql
FOR 루프 인덱스 이름 IN 커서 이름 LOOP
		결과 행별로 반복 수행할 작업;
END LOOP;
```
- 루프 인덱스(loop index)는 커서에 저장된 각 행이 저장되는 변수를 뜻하여 '.'을 통해 행의 각 필드에 접근할 수 있다.
- 커서에 FOR LOOP문을 사용허묜, OPEN, FETCH, CLOSE문을 작성하지 않는다.
- FOR LOOP를 통해 각 명령어를 자동으로 수행하므로 커서 사용 방법이 간단하다는 장점이 있다.
- FOR LOOP문을 활용하여 커서 사용하기
```sql
DECLARE
   -- 명시적 커서 선언(Declaration)
   CURSOR c1 IS
   SELECT DEPTNO, DNAME, LOC
     FROM DEPT;

BEGIN
   -- 커서 FOR LOOP 시작 (자동 Open, Fetch, Close)
   FOR c1_rec IN c1 LOOP
      DBMS_OUTPUT.PUT_LINE('DEPTNO : ' || c1_rec.DEPTNO
                      || ', DNAME : ' || c1_rec.DNAME
                      || ', LOC : ' || c1_rec.LOC);
   END LOOP;

END;
/
```

#### -- 커서에 파라미터 사용하기
- 고정값이 아닌 직접 입력한 값 또는 상황에 따라 여러 값을 번갈아 사용하려면 다음과 같이 커서에 파라미터를 지정할 수 있다.
- 기본형식
```sql
CURSOR 커서 이름(파라미터 이름 자료형, ...) IS
SELECT ...
```
- 파라미터를 사용하는 커서 알아보기
```sql
DECLARE
   -- 커서 데이터를 입력할 변수 선언
   V_DEPT_ROW DEPT%ROWTYPE;
   -- 명시적 커서 선언(Declaration)
   CURSOR c1 (p_deptno DEPT.DEPTNO%TYPE) IS
      SELECT DEPTNO, DNAME, LOC
        FROM DEPT
       WHERE DEPTNO = p_deptno;
BEGIN
   -- 10번 부서 처리를 위해 커서 사용
   OPEN c1 (10);
      LOOP
         FETCH c1 INTO V_DEPT_ROW;
         EXIT WHEN c1%NOTFOUND;
         DBMS_OUTPUT.PUT_LINE('10번 부서 - DEPTNO : ' || V_DEPT_ROW.DEPTNO
                                     || ', DNAME : ' || V_DEPT_ROW.DNAME
                                     || ', LOC : ' || V_DEPT_ROW.LOC);
      END LOOP;
   CLOSE c1;
   -- 20번 부서 처리를 위해 커서 사용
   OPEN c1 (20);
      LOOP
         FETCH c1 INTO V_DEPT_ROW;
         EXIT WHEN c1%NOTFOUND;
         DBMS_OUTPUT.PUT_LINE('20번 부서 - DEPTNO : ' || V_DEPT_ROW.DEPTNO
                                     || ', DNAME : ' || V_DEPT_ROW.DNAME
                                     || ', LOC : ' || V_DEPT_ROW.LOC);
      END LOOP;
   CLOSE c1;
END;
/
```
- 만약 커서 실행에 필요한 파라미터 값을 사용자에게 직접 입력 받고 싶다면 & 기호와 치환 변수를 사용할 수 있다.
- 커서에 사용할 파라미터 입력받기
```sql
DECLARE
   -- 사용자가 입력한 부서 번호를 저장하는 변수선언
   v_deptno DEPT.DEPTNO%TYPE;
   -- 명시적 커서 선언(Declaration)
   CURSOR c1 (p_deptno DEPT.DEPTNO%TYPE) IS
      SELECT DEPTNO, DNAME, LOC
        FROM DEPT
       WHERE DEPTNO = p_deptno;
BEGIN
   -- INPUT_DEPTNO에 부서 번호 입력받고 v_deptno에 대입
   v_deptno := &INPUT_DEPTNO;
   -- 커서 FOR LOOP 시작. c1 커서에 v_deptno를 대입
   FOR c1_rec IN c1(v_deptno) LOOP
      DBMS_OUTPUT.PUT_LINE('DEPTNO : ' || c1_rec.DEPTNO
                      || ', DNAME : ' || c1_rec.DNAME
                      || ', LOC : ' || c1_rec.LOC);
   END LOOP;
END;
/
```
- 명시적 커서는 여기에 쓴 것 외에도, 여러가지가 있다.
  - FOR UPDATE절: 커서 안의 행이 다른 세션에 의해 변경되지 않도록 잠금(lock) 기능을 제공
  - WHERE CURRENT OF절: 커서를 통해 추출한 행에 DML명령어를 사용


### - 묵시적 커서
- 묵시적 커서는 별 다른 선언 없이 SQL문을 사용했을 때 오라클에서 자동으로 선언되는 커서를 말한다.
- 따라서 사용자가 OPEN, FETCH, CLOSE를 지정하지 않는다.
- PL/SQL문 내부에서 DML명령어나 SELECT INTO문 등이 실행될 때 자동으로 생성 및 처리된다.
  - 여러 행의 결과를 가지는 커서는 명시적 커서로만 사용 가능하다.
- 자동으로 생성되어 실행되는 묵시적 커서는 별 다른 PL/SQL문을 작성하지 않아도 되지만, 다음 묵지적 커서의 속성을 사용하면 현재 커서의 정보를 확인할 수 있다.
- 커서가 자동으로 생성되므로 커서 이름을 지정하지 않고 SQL키워드로 속성을 지정하며, 명시적 커서의 속성과 유사한 기능을 갖는다.
- 속성의 종류
1. SQL%NOTFOUND
묵시적 커서 안에 추출한 행이 있으면 false, 없으면 true를 반환한다.
DML 명령어로 영향을 받는 행이 없을 경우에도 true를 반환한다.
2. SQL%FOUND
묵시적 커서 안에 추출한 행이 있으면 true, 없으면 false를 반환한다.
DML명령어로 영향을 받는 행이 있다면 true를 반환한다.
3. SQL%ROWCOUNT
묵시적 커서에 현재까지 추출한 행 수 또는 DML 명령어로 영향받는 행 수를 반환한다.
4. SQL%ISOPEN
묵시적 커서는 자동으로 SQL문을 실행한 후 CLOSE하므로 이 속성은 항상 false를 반환한다.
- 묵시적 커서의 각 속성을 사용하여 현재 커서의 상태 확인하기
```sql
BEGIN
   UPDATE DEPT SET DNAME='DATABASE'
    WHERE DEPTNO = 50;

   DBMS_OUTPUT.PUT_LINE('갱신된 행의 수 : ' || SQL%ROWCOUNT);

   IF (SQL%FOUND) THEN
      DBMS_OUTPUT.PUT_LINE('갱신 대상 행 존재 여부 : true');
   ELSE
      DBMS_OUTPUT.PUT_LINE('갱신 대상 행 존재 여부 : false');
   END IF;

   IF (SQL%ISOPEN) THEN
      DBMS_OUTPUT.PUT_LINE('커서의 OPEN 여부 : true');
   ELSE
      DBMS_OUTPUT.PUT_LINE('커서의 OPEN 여부 : false');
   END IF;

END;
/
```
---
## 18-2 오류가 발생해도 프로그램이 비정상 종료되지 않도록 하는 예외 처리
### - 오류란?
- 오류(error): 오라클에서 SQL, PL/SQL이 정상 수행되지 못하는 상황
- 오류의 종류
1. 컴파일 오류(compile error)
   - 문법 오류(syntax error)라고도 한다.
   - 문법이 잘못되었거나 오타로 인한 오류
2. 런타임 오류
   - 실행 오류(execute error)라고도 한다.
   - 명령문의 실행 중 발생한 오류
- 오라클에서는 위의 두가지 오류 중 후자,
즉, 프로그램이 실행되는 도중 발생하는 오류를 예외(exception)이라고 한다.
- 예외가 발생하는 PL/SQL
  - 밑의 예시는 문자열 데이터를 숫자 자료형 변수에 대입하려고 했기 때문에 런타임 오류가 발생하고 비정상 종료된다.
```sql
DECLARE
   v_wrong NUMBER;
BEGIN
   SELECT DNAME INTO v_wrong
     FROM DEPT
    WHERE DEPTNO = 10;
END;
/
```

>- 예외처리:
PL/SQL 실행 중 예외가 발생했을 때 프로그램이 비정상 종료되는 것을 막기 위해 특정 명령어를 PL/SQL문 안에 작성하는 것
- 예외처리는 PL/SQL문 안에서 EXCEPTION 영역에 필요 코드를 작성하는 것을 뜻한다.

- 예외를 처리하는 PL/SQL(예외 처리 추가)
  - 밑의 예시는 예외 처리부를 작성하였기 때문에 PL/SQL문을 실행할 때 오류가 발생하여도 프로그램이 비정상 종료되지 않는다.
```sql
DECLARE
   v_wrong NUMBER;
BEGIN
   SELECT DNAME INTO v_wrong
     FROM DEPT
    WHERE DEPTNO = 10;
EXCEPTION
   WHEN VALUE_ERROR THEN
      DBMS_OUTPUT.PUT_LINE('예외 처리 : 수치 또는 값 오류 발생');
END;
/
```
>- 예외 처리부 또는 예외 처리절:
EXCEPTION 키워드 뒤에 예외 처리를 위해 작성된 코드 부분
- 예외 처리부가 실행되면 예외가 발생한 코드 이후의 내용은 실행되지 않는다.

- 예외 발생 후의 코드 실행 여부 확인하기
```sql
DECLARE
   v_wrong NUMBER;
BEGIN
   SELECT DNAME INTO v_wrong
     FROM DEPT
    WHERE DEPTNO = 10;

   DBMS_OUTPUT.PUT_LINE('예외가 발생하면 다음 문장은 실행되지 않습니다');

EXCEPTION
   WHEN VALUE_ERROR THEN
      DBMS_OUTPUT.PUT_LINE('예외 처리 : 수치 또는 값 오류 발생');
END;
/
```

### - 예외 종류
- 오라클 예외 종류
1. 내부 예외(internal excaptions)
   - 오라클에서 미리 정의한 예외를 의미한다.
   - 내부 예외의 종류
     - 사전 정의된 예외(predefined name exceptions): 이름이 정의되어 있는 예외
     - 이름이 정해지지 않은 예외
2. 사용자 정의 예외(user-defined exceptions)
   - 사용자가 필요에 따라 추가로 정의한 예외를 의미한다.


|예외 종류||설명|
|---------------|------------------------|-----------------------------------------------|
|내부 예외|사전 정의된 예외|내부 예외 중 예외 번호에 해당하는 이름이 존재하는 예외|
|내부 예외|이름이 없는 예외|내부 예외 중 이름이 존재하지 않는 예외(사용자가 필요에 따라 이름을 지정할 수 있음)|
|사용자 정의 예외||사용자가 필요에 따라 직접 정의한 예외|

- 사전 정의되 예외는 비교적 자주 발생하는 예외에 이름을 붙여 놓은 것이다.
- 이름이 없는 예외는 ORA-XXXXXXX식으러 예외번호는 있지만 이름이 정해져 있지 않은 예외를 뜻한다.
  - 이름이 없는 예외는 예외 처리부에서 사용하기 위해 이름을 직접 붙여서 사용한다.
  
### - 예외 처리부 작성
- 예외 처리부는 EXCEPTION절에서 필요한 코드를 사용하여 작성한다.
  - 여러 예외를 명시하여 작성할 수 있다.
- 예외 핸들러(exception handler): WHEN으로 시작하는 절
  - 발생한 예외 이름과 일치하는 WHEN절의 명령어를 수행한다.
  - IF THEN문처럼 여러 예외 핸들러 중 일치하는 하나의 예외 핸들러 명령어만 수행한다.
- 수행할 명령어는 PL/SQL 실행부와 마찬가지로 여러 문법을 사용할 수 있다.
- OTHERS는 먼저 작성한 어느 예외와도 일치하는 예외가 없을 경우에 처리할 내용을 작성한다.
  - IF 조건문의 ELSE와 비슷하다.
- 예외 처리부 기본 형식
```sql
EXCEPTION
	WHEN 예외 이름 1 [OR 예외 이름2 - ] THEN
    	예외 처리에 사용할 명령어;
	WHEN 예외 이름 3 [OR 예외 이름4 - ] THEN
    	예외 처리에 사용할 명령어;
    ...
	WHEN OTHERS THEN
    	예외 처리에 사용할 명령어;
```
#### -- 사전 정의된 예외 사용
- 예외 핸들러에 사전 정의된 예외만을 사용할 때는 앞에서 살펴본 작성 방식대로 발생할 수 있는 예외를 명시한다.
- 사전 정의된 예외 사용하기
```sql
DECLARE
   v_wrong NUMBER;
BEGIN
   SELECT DNAME INTO v_wrong
     FROM DEPT
    WHERE DEPTNO = 10;

   DBMS_OUTPUT.PUT_LINE('예외가 발생하면 다음 문장은 실행되지 않습니다');

EXCEPTION
   WHEN TOO_MANY_ROWS THEN
      DBMS_OUTPUT.PUT_LINE('예외 처리 : 요구보다 많은 행 추출 오류 발생');
   WHEN VALUE_ERROR THEN
      DBMS_OUTPUT.PUT_LINE('예외 처리 : 수치 또는 값 오류 발생');
   WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('예외 처리 : 사전 정의 외 오류 발생');
END;
/
```
#### -- 이름 없는 예외 사용
- 이름이 없는 내부 예외를 사용해야한다면 이름을 직접 지정해주어야 예외 처리부에서 사용할 수 있다.
- 이름을 직접 지어 줄 때 밑에 기본 형식과 같이 선언부에 오라클 예외 번호와 함께 이름을 붙인다.
- 이렇게 이름이 정해진 예외는 사전 정의된 예외를 사용할 때와 마찬가지로 예외 처리부에서 지정한 이름으로 예외 핸들러에 작성한다.
#### -- 사용자 정의 예외 사용
- 사용자 정의 예외는 오라클에 정의되어 있지 않은 특정 상황을 직접 오류로 정의하는 방식이다.
- 밑의 기본형식과 같이 예외 이름을 정해주고 실행부(BEGIN)에서 직접 정의한 오류 상황이 생겼을 때 RAISE 키워드를 사용하여 예외를 직접 만들 수 있다.
- 이렇게 직접 만든 예외 역시 앞의 예외 처리와 마찬가지로 예외 처리부에서 예외 이름을 통해 수행할 내용을 작성해 줌으로써 처리한다.
#### -- 오류 코드와 오류 메시지 사용
- 오류 처리부가 잘 작성되어 있다면 오류가 발생해도 PL/SQL은 정상 종료된다.
- PL/SQL문의 정상 종료 여부와 상관없이 발생한 오류 내역을 알고 싶을 때 SQLCODE, SQLERRM 함수를 사용한다.

  |함수|설명|
  |--------|-------------|
  |SQLCODE|오류 번호를 반환하는 함수|
  |SQLERRM|오류 메시지를 반환하는 함수|
  
- 오류 코드와 오류 메시지 사용하기
```sql
DECLARE
   v_wrong NUMBER;
BEGIN
   SELECT DNAME INTO v_wrong
     FROM DEPT
    WHERE DEPTNO = 10;

   DBMS_OUTPUT.PUT_LINE('예외가 발생하면 다음 문장은 실행되지 않습니다');

EXCEPTION
   WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('예외 처리 : 사전 정의 외 오류 발생');
      DBMS_OUTPUT.PUT_LINE('SQLCODE : ' || TO_CHAR(SQLCODE));
      DBMS_OUTPUT.PUT_LINE('SQLERRM : ' || SQLERRM);
END;
/
```
---
## 잊기 전에 한 번 더!
1. Q1.
```sql
-- 18-1
--①
DECLARE
   -- 커서 데이터가 입력될 변수 선언
   V_EMP_ROW EMP%ROWTYPE;
   -- 명시적 커서 선언(Declaration)
   CURSOR c1 IS
      SELECT *
        FROM EMP;
BEGIN
   -- 커서 열기(Open)
   OPEN c1;
   LOOP
       -- 커서로부터 읽어온 데이터 사용(Fetch)
       FETCH c1 INTO V_EMP_ROW;
       -- 커서의 모든 행을 읽어오기 위해 %NOTFOUND 속성지정
       EXIT WHEN c1%NOTFOUND;
       DBMS_OUTPUT.PUT_LINE('EMPNO : '    || V_EMP_ROW.EMPNO
                       || ', ENAME : '    || V_EMP_ROW.ENAME
                       || ', JOB : '      || V_EMP_ROW.JOB
                       || ', SAL : '      || V_EMP_ROW.SAL
                       || ', DEPTNO : '   || V_EMP_ROW.DEPTNO		
		       );
   END LOOP;
   -- 커서 닫기(Close)
   CLOSE c1;
END;
/

--②
DECLARE
   -- 명시적 커서 선언(Declaration)
   CURSOR c1 IS
      SELECT *
        FROM EMP;
BEGIN
   -- 커서 FOR LOOP 시작 (자동 Open, Fetch, Close)
   FOR c1_rec IN c1 LOOP
      DBMS_OUTPUT.PUT_LINE('EMPNO : '    || c1_rec.EMPNO
                      || ', ENAME : '    || c1_rec.ENAME
                      || ', JOB : '      || c1_rec.JOB
                      || ', SAL : '      || c1_rec.SAL
                      || ', DEPTNO : '   || c1_rec.DEPTNO);
   END LOOP;
END;
/

```
2. Q2.
```sql
-- 18-2
DECLARE
   v_wrong DATE;
BEGIN
   SELECT ENAME INTO v_wrong
     FROM EMP
    WHERE EMPNO = 7369;

    DBMS_OUTPUT.PUT_LINE('예외가 발생하면 다음 문장은 실행되지 않습니다');

EXCEPTION
   WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('오류가 발생하였습니다.' 
                        || TO_CHAR(SYSDATE, '[YYYY"년"MM"월"DD"일" HH24"시"mm"분"SS"초"]'));
                
      DBMS_OUTPUT.PUT_LINE('SQLCODE : ' || TO_CHAR(SQLCODE));
      DBMS_OUTPUT.PUT_LINE('SQLERRM : ' || SQLERRM);
END;
/
```