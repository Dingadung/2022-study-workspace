## 13-1 데이터베이스를 위한 데이터를 저장한 데이터 사전
### - 사전이란?
- 오라클 데이터베이스 테이블은 사용자 테이블(user table)과 데이터 사전(data dictionary)으로 나뉜다.
- **사용자 테이블**: 데이터베이스를 통해 관리할 데이터를 저장하는 테이블
  - EMP, DEPT, SALGRADE 테이블
  - Normal Table
- **데이터 사전**: 데이터베이스를 구성하고 운영하는데 필요한 모든 정보를 저장하는 특수한 테이블
  - 데이터 베이스가 생성되는 시점에 **자동**으로 만들어진다.
  - Base Table
- 데이터 사전에는 데이터베이스 메모리, 성능, 사용자, 권한, 객체 등 **오라클 데이터베이스** **운영**에 중요한 데이터가 보관되어 있다.
- 컴퓨터 윈도우로 비유하기
  - 사용자 테이블: 윈도우에서 사용할 응용 프로그램, 윈도우 동작 자체와는 무관한 프로그램이 저장된 Program Files
  - 데이터 사전: 윈도우 구동을 위한 파일들로 이루어진 Windows 폴더
- 따라서, 오라클 데이터베이스는 사용자가 데이터 사전 정보에 직접 접근하거나 작업하는 것을 허용하지 않는다.
- 그 대신, 데이터 사전 뷰(data dictionary view)를 제공하여 SELECT문으로 정보 열람을 할 있게 해 두었다.

#### -- VIEW(뷰)는 어떤 목적을 위해 테이블 일부 또는 전체 데이터 열람을 주 목적으로 사용하는 객체를 뜻한다.
- **데이터 사전 뷰**는 용도에 따라 이름 앞에 다음과 같은 접두어를 지정하여 분류한다.
  - **USER_XXXX**
    - **현재** 데이터베이스에 **접속한** 사용자가 소유한 **객체 정보**
  - **ALL_XXXX**
     - 현재 데이터베이스에 접속한 사용자가 **소유한** 객체 또는 다른 사용자가 소유한 객체 중 **사용 허가**를 받은 객체, 즉 사용 가능한 모든 객체 정보
  - **DBA_XXXX**
     - 데이터베이스 **관리**를 위한 정보(데이터베이스 관리 권한을 가진 SYSTEM, SYS 사용자만 열람 가능)
  - V$_XXXX
     - 데이터베이스 성능 관련 정보(X$_XXXX 테이블의 뷰)
- 사용 가능한 데이터 사전을 알고 싶다면 DICTIONARY 또는 DICT를 조회한다.
  - 종류가 많으므로 자주 사용하는 몇 개 정도만 알아두고 그 밖의 것들은 필요할 때 찾아서 사용하면 된다.
- SCOTT 계정에서 사용 가능한 데이터 사전 살펴보기(DICT 사용)
```sql
SELECT * FROM DICT;
```
- SCOTT 계정에서 사용 가능한 데이터 사전 살펴보기(DICTIONARY 사용)
```sql
SELECT * FROM DICTIONARY;
```
### - USER_ 접두어를 가진 데이터 사전
- USER_ 접두어로 시작하는 이름의 데이터 사전에는 **현재** 오라클에 **접속해** 있는 사용자가 **소유한** 객체 정보가 보관되어 있다.
- SCOTT 계정이 소유하는 테이블 정보는 USER_TABLES를 사용한다.
- SCOTT 계정이 가지고 있는 객체 정보 살펴보기(USER_ 접두어 사용)
```sql
SELECT TABLE_NAME
    FROM USER_TABLES;
```
- 위의 코드는 SCOTT 계정이 가지고 있는 테이블 이름을 알고 싶을 때 유용하다.
- SCOTT 계정이 처음부터 소유한 테이블과 데이터 정의어를 사용하면서 생성한 테이블 정보를 조회한다.

>- 접두어 USER_, ALL_, DBA_ 뒤에 복수형으로 단어를 구성한다.

### - ALL_ 접두어를 가진 데이터 사전
- ALL_ 접두어를 가진 데이터 사전은 오라클 데이터베이스에 접속해 있는 사용자가 소유한 객체 및 다른 사용자가 소유한 객체 중 사용이 허락되어 있는 객체 정보를 가지고 있다.
- SCOTT 계정으로 접속하여 ALL_TABLES를 조회하면 SCOTT 계정이 사용할 수 있는 테이블 정보를 모두 보여준다.
- ALL_ 접두어의 데이터 사전 역시 뒤에 객체를 명시할 때 복수형 단어를 사용한다.
- SCOTT 계정이 사용할 수 있는 객체 정보 살펴보기(ALL_ 접두어 사용)
```sql
SELECT OWNER, TABLE_NAME
    FROM ALL_TABLES;
```
- ALL_TABLES에는 USER_TABLES와 달리 OWNER 열이 하나 더 있다.
  - 이 열은, 테이블을 소유한 사용자를 명시한다.
  - 앞 강에서 사용한 DUAL 테이블은 오라클 관리 계정 SYS소유이고, SCOTT 계정은 이 테이블의 사용을 허가 받았을 뿐다.
  - OWNER 열 이외의 열은 USER_TABLES와 ALL_TABLES 모두 동일한 열 구조를 가진다.
- 다음은 USER_TABLES와 ALL_TABLES의 열 일부이다.
  - OWNER: 테이블을 소유한 사용자, ALL_TABLE에만 존재
  - TABLE_NAME: 테이블 이름
  - TABLESPACE_NAME: 테이블 스페이스 이름
  - NUM_ROWS: 테이블에 저장된 행 수
### - DBA_ 접두어를 가진 데이터 사전
- DBA_ 접두어를 가진 데이터 사전은 데이터베이스 **관리 권한**을 가진 사용자만 조회할 수 있는 테이블로서 SCOTT 계정으로는 조회가 불가능하다.
- SCOTT계정으로 DBA_ 접두어 사용하기
```sql
SELECT * FROM DBA_TABLES;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/2f79f79d-bcde-4e2e-8039-bc80ce9d6c30/image.png)
- 데이터베이스에 존재하는데 존재하지 않는다고 출력되는 이유?
  - 사용 권한이 없는 사용자는 해당 개체의 존재 여부조차 확인할 수 없음을 의미한다.
  
>⇒ 오라클 데이터베이스에서 어떤 사용자가 사용 권한이 없는 정보 열람을 시도할 경우에 오라클 데이터베이스는 해당 개체가 존재하지 않는다고 알려준다.

- 데이터베이스 권한이 있는 SYSTEM 사용자(비밀번호 oracle로 지정)로 접속하면 DBL_TABLES 조회가 가능하다.
- 데이터베이스에 존재하는 모든 테이블이 출력되며 열 구성은 ALL_TABLES와 같다.
- SYSTEM 계정으로 DBA_ 접두어 사용하기(SYSTEM 계정으로 접속했을 때)
```sql
SELECT * FROM DBL_TABLES;
```
#### -- DBA_USERS로 사용자 정보 살펴보기
- 오라클 데이터베이스에 등록된 사용자 정보는 DBA_USERS에 있다.
- SCOTT 사용자 정보를 보려면 USERNAME열을 WHERE 조건으로 지정하여 사용하면 된다.
- DBA_USERS를 사용하여 사용자 정보를 알아보기(SYSTEM 계정으로 접속했을 때)
```sql
SELECT * FROM DBA_USERS
WHERE USERNAME = 'SCOTT';
```
- DBA_접두어가 붙은 데이터 사전은 오라클 데이터베이스 운영과 관련된 여러 정보를 보관한다.
- 데이터베이스 자체를 관리하는 목적 외에 오라클 데이터베이스를 사용하여,
데이터를 보관하고 관리하는 업무를 진행할 때는 그리 자주 사용하지 않는다.
- 데이터 사전 뷰 == 데이터 사전

> - 데이터 사전
  - 오라클 데이터베이스를 구성하고 운영하는 데이터를 저장하는 특수한 테이블
  - 오라클 사용자가 직접 접근할 수 없다.
  - SELECT문으로 데이터를 열람할 수 있도록 데이터 사전 뷰(데이터 사전)을 제공한다.

>- 대표적인 데이터 사전 뷰 중 현재 접속한 사용자가 소유하는 테이블 목록을 보기 위해서는 **USER_TABLES**를 사용한다.

>- 사용자가 소유하는 테이블을 포함해 다른 사용자가 소유한 테이블 중 현재 사용자에게 사용 허가가 되어 있는 테이블을 보기 위해서는 **ALL_TABLES**를 사용한다.

## 13-2 더 빠른 검색을 위한 인덱스
### - 인덱스란?
- 책에서 특정 단어 또는 내용이 있는 페이지를 찾으려면 두 가지 방법을 생각할 수 있다.
  1. 책을 처음부터 끝까지 읽어보며 찾기
  2. 책의 목차나 색인을 통해 찾으려는 단어가 있는 페이지 바로 찾기
- 목인이나 색인을 활용하여 찾는 것이 빠르다.

>- 색인이라는 뜻의 인덱스(INDEX)는 책 내용을 찾는 것과 마찬가지로 오라클 데이터베이스에서 데이터 검색 성능의 향상을 위해 테이블 열에 사용하는 객체를 뜻한다.

- 테이블에 보관된 특정 행 데이터의 주소, 즉 위치 정보를 책 페이지처럼 목록으로 만들어 놓은 것
- 인덱스는 테이블 열을 여러 가지 분석을 통해 선정하여 설정할 수 있다.
- 인덱스 사용 여부에 따라 데이터 검색 방식을 TABLE FULL SCAN, INDEX SCAN으로 구분한다.
  - TABLE FULL SCAN: 테이블 데이터를 처음부터 끝까지 검색하여 원하는 데이터를 찾는 방식
  ⇒ 다 읽기
  - INDEX SCAN: 인덱스를 통해 데이터를 찾는 방식
  ⇒ 목차 이용해서 읽기
- 인덱스도 오라클 데이터 베이스 객체이므로 소유 사용자와 사용 권한이 존재한다.
⇒ 즉, **객체에는 소유 사용자와 사용 권한이 존재**한다.
- SCOTT 소유의 인덱스 정보를 열람할 때 USER_INDEXES, USER_IND_COLUMNS와 같은 데이터 사전을 이용한다.
- TABLE_NAME 열에서 인덱스가 속한 테이블을 확인할 수 있다.
- INDEX_NAME 열에서 인덱스를 지정한 열을 알 수 있다.
- SCOTT 계정이 소유한 **인덱스 정보** 알아보기(SCOTT 계정일 때)
```sql
SELECT * FROM USER_INDEXES;
```
- SCOTT 계정이 소유한 **인덱스 컬럼 정보** 알아보기(SCOTT 계정일 때)
```sql
SELECT * FROM USER_IND_COLUMNS;
```
- 인덱스는 사용자가 직접 특정 테이블의 열에 지정할 수도 있지만, 열이 기본키(Primary key) 또는 고유키(Unique key)일 경우 자동으로 생성된다.
  - 고유키는 열 데이터의 중복을 허용하지 않는 제약 조건이다.

### - 인덱스 생성
- 오라클 데이터베이스에서 자동으로 생성해 주는 인덱스 외에 사용자가 직접 인덱스를 만들때는 CREATE문을 사용한다.
- CREATE문에서는 인덱스를 생성할 테이블 및 열을 지정하며 열은 하나 또는 여러 개 지정할 수 있다.
- 지정한 각 열별로 인덱스 정렬 순서(오름차순 또는 내림차순)를 정할 수도 있다.
- 기본 형식
```sql
CREATE INDEX 인덱스 이름
	ON 테이블 이름(열 이름1 ASC or DESC,
    			 열 이름2 ASC or DESC,
                 ...				);
```
- EMP 테이블의 SAL 열에 인덱스 생성하기
```sql
CREATE INDEX IDX_EMP_SAL
    ON EMP(SAL);
SELECT * FROM USER_IND_COLUMNS;
```
- 생성된 인덱스 살펴보기(USER_IND_COLUMNS 사용)
```sql
SELECT * FROM USER_IND_COLUMNS;
```
- 인덱스의 정렬 옵션을 지정하지 않으면 기본값은 오름차순으로 지정된다.
- 인덱스가 걸린 SAL열을 WHERE의 검색 조건으로 하여 EMP 테이블을 조회하면 출력속도가 빨라질 것이라 예상할 수 있다.
- 그러나, 인덱스를 지정할 열의 선정은 데이터의 구조 및 데이터의 분포도 등 여러 조건을 고려해서 이루어져야 한다.
- 인덱스를 지정하면 데이터 조회를 반드시 빠르게 한다고 보장하기는 어렵다.
- 목적에 따라 여러 방식으로 생성할 수 있는 인덱스의 종류
  - 단일 인덱스
  - 복합 인덱스, 결합 인덱스
     - 두 개 이상 열로 만들어지는 인덱스
     - WHERE절의 두 열이 AND 연산으로 묶이는 경우
  - 고유 인덱스
     - 열에 중복 데이터가 없을 때 사용
     - UNIQUE 키워드를 지정하지 않으면 비고유 인덱스가 기본값
  - 함수 기반 인덱스
     - 열에 산술식 같은 데이터 가공이 진행된 결과로 인덱스 생성
  - 비트맵 인덱스
     - 데이터 종류가 적고 같은 데이터가 많이 존재할 때 주로 사용
### - 인덱스 삭제
- 인덱스 삭제는 DROP명령어를 사용한다.
- 기본 형식
```sql
DROP INDEX 인덱스 이름;
```
- EMP 테이블의 SAL 열에 생성한 IDX_EMP_SAL 인덱스 삭제하기
```sql
DROP INDEX IDX_EMP_SAL;
```
- 생성된 인덱스 살펴보기(USER_IND_COLUMNS 사용)
```sql
SELECT * FROM USER_IND_COLUMNS;
```
- 인덱스는 데이터 접근 및 검색 속도 향상을 위해 사용하는 객체이다.
- 하지만 인덱스 생성이 항상 좋은 결과로 이어지지는 않는다.
- 정확한 데이터 분석에 기반을 두지 않은 인덱스의 무분별한 생성은 오히려 성능을 떨어뜨리는 원인이 되기도 한다.
- 인덱스는 데이터 종류, 분포도, 조회하는 SQL의 구성, 데이터 조작 관련 SQL문의 작업 빈도, 검색 결과가 전체 데이터에서 차지하는 비중 등 많은 요소를 고려하여 생성한다.
- 인덱스 사용 및 생성과 관련하여 더 자세한 내용은 SQL 튜닝(TUNNING) 관련 서적을 참고하자.

## 13-3 테이블처럼 사용하는 뷰
### - 뷰란?
>가상 테이블(virtual table)로 부르는 뷰(view)는 하나 이상의 테이블을 조회하는 SELECT문을 저장한 객체를 뜻한다.

- SELECT문을 저장하기 때문에 물리적 데이터를 따로 저장하지는 않는다.
- 뷰를 SELECT문의 FROM절에 사용하면 특정 테이블을 조회하는 것과 같은 효과를 얻을 수 있다.
- 뷰는 서브쿼리를 사용한 것과 비슷한 효과를 낸다.

|뷰|서브쿼리|
|:--:|:-----:|
|SELECT * FROM VW_EMP20;|SELECT * <br>FROM(SELECT EMPNO, ENAME, JOB, DEPTNO<br>FROM EMP<br>WHERE DEPTNO = 20);|

### - 뷰의 사용 목적
1. 편리성: SELECT문의 복잡도를 완화하기 위해
2. 보안성: 테이블의 특정 열을 노출하고 싶지 않을 경우

### 뷰 생성
- 뷰는 CREATE문으로 생성할 수 있다.
- SCOTT계정은 뷰 생성 권한이 없으므로 SYSTEM 계정으로 접속한 후 다음 명령어를 사용하여 SCOTT 계정에 권한을 부여해준다.
- 뷰를 생성하기 위해 접속 계정을 변경하여 접속하기(SQL*PLUS)
```sql
SQLPLUS SYSTEM/oracle
GRANT CREATE VIEW TO SCOTT;
```
- 뷰를 만드는 CREATE문의 기본 형식
```sql
CREATE [OR REPLACE] [FORCE | NOFOECE] VIEW 뷰 이름 (열 이름1, 열 이름2, ...)
	AS (저장할 SELECT문)
[WITH CHECK OPTION [CONSTRAINT 제약 조건]]
[WITH READ ONLY [CONSTRAINT 제약 조건]];
```

|요소|설명|
|-------------|----------------------------|
|OR REPLACE|같은 이름의 뷰가 이미 존재할 경우에 현재 생성할 뷰로 대체하여 생성(선택)|
|FORCE|뷰가 저장할 SELECT문의 기반 테이블이 존재하지 않아도 강제로 생성(선택)|
|NOFORCE|뷰가 저장할 SELECT문의 기반 테이블이 존재할 경우에만 생성(기본값)(선택)|
|뷰 이름|생성할 뷰 이름을 지정(필수)|
|열 이름|SELECT문에 명시된 이름 대신 사용할 열 이름 지정(생략 가능)(선택)|
|저장할 SELECT문|생성할 뷰에 저장할 SELECT문 지정(필수)|
|WITH CHECK OPTION|지정한 제약 조건을 만족하는 데이터에 한해 DML 작업이 가능하도록 뷰 생성(선택)|
|WITH READ ONLY|뷰의 열람, 즉 SELECT만 가능하도록 뷰 생성(선택)|
- 뷰 생성하기
```sql
CREATE VIEW VW_EMP20
AS (SELECT EMPNO, ENAME, JOB, DEPTNO
    FROM EMP
    WHERE DEPTNO = 20);
```
- USER_VIEWS를 사용하여 생성한 뷰 확인하기
```sql
SELECT * FROM USER_VIEWS;
```
- 뷰는 여러 테이블을 조인하거나 서브쿼리를 사용한 조금 더 복합적인 SELECT문도 저장할 수 있다.
- 생성한 뷰 조회하기
```sql
SELECT * FROM VW_EMP20;
```

### - 뷰 삭제
- 뷰 삭제하기
```sql
DROP VIEW VW_EMP20;
```
- 뷰는 실제 데이터가 아닌 SELECT문만 저장하므로 뷰를 삭제해도 테이블이나 데이터가 삭제되는 것은 아니다.

#### -- 뷰와 데이터 조작어
- 뷰는 SELECT문만 저장하는 객체이기에 데이터 삽입, 수정, 삭제 같은 데이터 조작어 사용이 불가능할 것이라고 생각할 수 있지만, 뷰에도 데이터 조작어를 직접 사용할 수 있는 경우가 있다.
- 하지만 뷰를 통한 테이블 데이터 조작이 가능하려면 여러가지 조건을 만족해야 하고 테이블을 설계할 때 누락된 내용이 있으면 뷰를 통한 데이터 조작으로 인해 적합하지 않은 데이터가 생길수도 있으므로 자주 사용하는 편은 아니다.
- 뷰의 주 목적은 물리적 데이터를 저장하지 않고, SELECT문만 저장함으로써 테이블의 데이터를 열람하는 것이다.
- 데이터를 따로 저장하는 것이 허용되는 구체화 뷰(materialized view)가 존재한다.

### - 인라인 뷰를 사용한 TOP-N SQL문

>CREATE문을 통해 객체로 만들어지는 뷰 외에, SQL문에서 일회성으로 만들어서 사용하는 뷰를 인라인 뷰(inline view)라고 한다.

- SELECT문에서 사용되는 서브쿼리, WITH절에서 미리 이름을 정의해 두고 사용하는 SELECT문 등이 이에 해당한다.
- 인라인 뷰와 ROWNUM을 사용하면 ORDER BY절을 통해 정렬된 결과 중 최상위 몇개 데이터만 출력하는 것이 가능하다.
- ROWNUM을 추가로 조회하기
```sql
SELECT ROWNUM, E.*
    FROM EMP E;
```

#### -- ROWNUM
- ROWNUM은 의사열(PSEDO COLUMN)이라고 하는 특수 열이다.
- 의사 열은 데이터가 저장되는 실제 테이블에 존재하지는 않지만, 특정 목적을 위해 테이블에 저장되어 있는 열처럼 사용 가능한 열을 뜻한다.
- ROWNUM 열 데이터 번호는 테이블에 저장된 행이 조회된 순서대로 매겨진 일련번호이다.
- EMP 테이블을 SAL 열기준으로 정렬하기
```sql
SELECT ROWNUM, E.*
    FROM EMP E
ORDER BY SAL DESC;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/50e64450-f4e9-4d74-88d2-97c16b25934a/image.png)
- 급여 기준으로 정렬했지만, ROWNUM은 앞에서 사용한 SELECT문의 행 번호와 같은 번호로 매겨져 있음을 알 수 있다.
- ROWNUM은 데이터를 하나씩 추가할 때 매겨지는 번호이므로 ORDER BY절을 통해 정렬해도 유지되는 특성이 있다.
- 이러한 특성을 인라인 뷰에서 적용하면 정렬된 SELECT문의 결과 순번을 매겨서 출력할 수 있다.
- 인라인 뷰(서브쿼리사용)
```sql
SELECT ROWNUM, E.*
 FROM (SELECT * FROM EMP E ORDER BY SAL DESC) E;
```
- 인라인 뷰(WITH절 사용)
```sql
WITH E AS (SELECT * FROM EMP E ORDER BY SAL DESC)
SELECT ROWNUM, E.*
    FROM E;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/e2aa28de-d660-4f9a-88fb-0f7223767ff0/image.png)
- 인라인뷰를 사용한 TOP-N 추출은 활용 빈도가 높으니 기억해 두는 것이 좋다.
- 인라인 뷰로 TOP-N 추출하기(서브쿼리 사용)
```sql
SELECT ROWNUM, E.*
FROM (SELECT * FROM EMP E
        ORDER BY SAL DESC) E
WHERE ROWNUM <= 3;
```
- 인라인 뷰로 TOP-N 추출하기(서브쿼리 사용)
```sql
WITH E AS (SELECT * FROM EMP ORDER BY SAL DESC)
SELECT ROWNUM, E.*
FROM E
WHERE ROWNUM <= 3;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/130dbfb1-6e07-4524-8cd6-b108c86290e1/image.png)

## 13-4 규칙에 따라 순번을 생성하는 시퀀스
### 시퀀스란?
>시퀀스(sequence)는 오라클 데이터베이스에서 특정 규칙에 맞는 연속 숫자를 생성하는 객체이다.

- 단지 연속하는 새로운 번호를 만드는 일이라면 다음과 같이 MAX 함수에 1을 더한 값을 사용해도 상관잉 없다.
- 이 방식은 실제로 연속하는 숫자로 이루어진 웹 서비스의 새로운 게시판 번호나 상품 주문 번호 등을 생성할 때 종종 생성하는 방식이다.
```sql
SELECT MAX(글 번호) + 1
	FROM 게시판 테이블
```
- 하지만, 이 방식은 테이블 수가 많을 수록 가장 큰 데이터를 찾고, 새로운 번호를 계산하는 시간이 함께 늘어나므로 아쉬운 부분이 있다.
- 또한 동시에 여러 곳에서 새로운 번호를 요구했을 경우, SELECT문의 결과 값이 같게 나와 번호가 중복될 수도 있다.
- 이러한 방식과 비교하여 시퀀스는 단순히 번호 생성을 위한 객체이지만, 지속적이고 효율적인 번호 생성이 가능하므로 여러모로 자주 사용하는 객체이다.

### - 시퀀스 생성
- 시퀀스 역시 CREATE문으로 생성하며 다음과 같이 다양한 옵션을 지정할 수 있다.
```sql
CREATE SEQUENCE 시퀀스 이름
[INCREMENT BY N]
[START WITH N]
[MAXVALUE N | NOMAXVALUE]
[MINVALUE N | NOMINVALUE]
[CYCLE | NOCYCLE]
[CACHE N | NOCACHE]
```

|요소|설명|
|----------|---------------------------|
|시퀀스 이름|생성할 시퀀스 이름 지정. 아래 절들을 지정하지 않을 경우 1부터 시작하여 1만큼 계속 증가하는 시퀀스가 생성(필수)|
|INCREMENT BY N|시퀀스에서 생성할 번호의 증가 값(기본값은 1) (선택)|
|START WITH N|시퀀스에서 생성할 번호의 시작 값(기본값은 1) (선택)|
|MAXVALUE N, NOMAXVALUE|시퀀스에서 생성할 번호의 최대값 지정(선택)|
|MINVALUE N, NOMINVALUE|시퀀스에서 생성할 번호의 최솟값 지정(선택)|
|CYCLE, NOCYCLE|시퀀스에서 생성한 번호가 최댓값에 도달했을 경우 CYCLE이면 시작값에서 다시 시작, NOCYCLE이면 번호 생성이 중단되고, 추가 번호 생성을 요청하면 오류 발생(선택)|
|CACHE N, NOCACHE|시퀀스가 생성할 번호를 메모리에 미리 할당해 놓은 수를 지정, NOCACHE는 미리 생성하지 않도록 설정, 옵션을 모두 생략하면 기본값은 20(선택)|

- 시퀀스를 사용하기 위해 DEPT 테이블과 열 구성은 같고 데이터는 없는 DEPT_SEQUENCE 테이블 생성하기
```sql
CREATE TABLE DEPT_SEQUENCE
    AS SELECT * FROM DEPT WHERE 1<>1;
```
- DEPT 테이블에서 부서번호를 10으로 시작해서 10씩 증가하게 하는 시퀀스 생성하기
```sql
CREATE SEQUENCE SEQ_DEPT_SEQUENCE
INCREMENT BY 10
START WITH 10
MAXVALUE 90
MINVALUE 0
NOCYCLE
CACHE 2;
```
- 생성한 시퀀스 확인하기
```sql
SELECT * FROM USER_SEQUENCES;
```

### - 시퀀스 사용하기
- 생성된 시퀀스를 사용할 때는 [시퀀스이름.CURRVAL]과 [시퀀스 이름.NEXTVAL]을 사용할 수 있다.
- CURRVAL은 시퀀스에서 마지막으로 생성한 번호를 반환한다.
- NEXTVAL은 다음 번호를 생성한다.
- CURRVAL은 시퀀스를 생성하고 바로 사용하면 번호가 만들어진적이 없으므로 오류가 난다.
- SEQ_DEPT_SEQUENCE 시퀀스를 사용하여 DEPT_SEQUENCE 테이블에 새로운 부서를 추가하려면 다음과 같이 INSERT문에 NEXTVAL을 사용한다.
```sql
INSERT INTO DEPT_SEQUENCE (DEPTNO, DNAME, LOC)
VALUES (SEQ_DEPT_SEQUENCE.NEXTVAL, 'DATABASE', 'SEOUL');

SELECT * FROM DEPT_SEQUENCE ORDER BY DEPTNO;
```
- 가장 마지막으로 생성된 시퀀스 확인하기
```sql
SELECT seq_dept_sequence.CURRVAL
    FROM DUAL;
```
- MAXVALUE인 90번에 이를때까지 값을 넣어주면 NOCYCLE로 시퀀스를 생성했기 때문에 오류가 난다.

### - 시퀀스 수정
- ALTER 명령어로 시퀀스를 수정하고 DROP 명령어로 시퀀스를 삭제한다.
- 시퀀스 수정은 옵션을 재설정하는데 사용한다.
- START WITH 값은 변경할 수 없다.
- 기본형식
```sql
ALTER SEQUENCE 시퀀스 이름
[INCREMENT BY N]
[MAXVALUE N | NOMAXVALUE]
[MINVALUE N | NOMINVALUE]
[CYCLE | NOCYCLE]
[CACHE N | NOCACHE]
```
- 시퀀스 옵션 수정하기
```sql
ALTER SEQUENCE SEQ_DEPT_SEQUENCE
INCREMENT BY 3
MAXVALUE 99
CYCLE;
```
- 수정한 시퀀스를 사용하여 INSERT문 실행하기
```sql
INSERT INTO DEPT_SEQUENCE (DEPTNO, DNAME, LOC)
VALUES (SEQ_DEPT_SEQUENCE.NEXTVAL, 'DATABASE', 'SEOUL');

SELECT * FROM DEPT_SEQUENCE ORDER BY DEPTNO;
```
- CYCL 옵션을 사용한 시퀀스의 최댓값 도달 후 수행 겨로가 확인하기
→ 새로 추가된 열의 DEPTNO 값이 99(시퀀스의 최댓값)가 되면 다시 0(시퀀스의 최솟값)부터 3씩 증가되어 열이 추가된다.
![](https://velog.velcdn.com/images/mini_mouse_/post/a453cd83-0de8-4c16-ae46-d4fe8b168117/image.png)
![](https://velog.velcdn.com/images/mini_mouse_/post/5ef7b3c6-30a3-444c-aadd-2692e59e4d82/image.png)

### - 시퀀스 삭제
- DROP SEQUENCE를 사용하면 시퀀스를 삭제할 수 있다.
- 시퀀스를 삭제해도 시퀀스를 사용하여 추가된 데이터는 삭제되지 않는다!
- 시퀀스 삭제 후 확인하기
```sql
DROP SEQUENCE SEQ_DEPT_SEQUENCE;
SELECT * FROM USER_SEQUENCES;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/9763f523-2011-4150-9db4-5d2dbe25ca68/image.png)

## 13-5 공식 별칭을 지정하는 동의어
### - 동의어란?
- 동의어(SYNONYM)는 테이블, 뷰, 시퀀스 등 객체 이름 대신 사용할 수 있는 다른 이름을 부여하는 객체이다.
- 주로 테이블 이름이 너무 길어 사용하기 불편할 때 좀 더 간단하고 짧은 이름을 하나 더 만들어 주기 위해 사용한다.
- 동의어를 만들기 위해서는 CREATE문을 사용하여 다음과 같이 작성한다.
- 기본 형식
```sql
CREATE [PUBLIC] SYNONYM 동의어 이름
FOR [사용자.][객체이름];
```

|요소|설명|
|-----------|-------------------------|
|PUBLIC|동의어를 데이터베이스 내 모든 사용자가 사용할 수 있도록 설정, 생략할 경우 동의어를 생성한 사용자만 사용 가능(PUBLIC으로 생성되어 있어도 본래 객체의 사용 권한이 있어야 사용이 가능하다.) (선택)|
|동의어 이름|생성할 동의어 이름(필수)|
|사용자.|생성할 동의어의 본래 객체 소유 사용자를 지정. 생략할 경우 현재 접속한 사용자로 지정 (선택)|
|객체 이름|동의어를 생성할 대상 객체 이름 (필수)|
- 생성한 동의어는 SELECT, INSERT, UPDATE, DELETE ... 다양한 SQL문에서 사용할 수 있다.

- 동의어는 SELECT문의 SELECT절, FROM절에서 사용한 열 또는 별칭과 유사하지만, 오라클 데이터베이스에서 저장되는 객체이기 때문에 **일회성이 아니라는 점**에서 차이가 난다.
- 동의어 생성 역시 권한을 따로 부여해야하기 때문에 SYSTEM에 접속하여 SCOTT 계정에 동의어 생성 권한을 부여해야 한다.
- PUBLIC SYNONYM 권한도 따로 부여해 주어야 한다.
- 권한 부여하기(SQL*PLUS)
![](https://velog.velcdn.com/images/mini_mouse_/post/8bb170a0-8504-4d83-945e-b4647f909ecb/image.png)

### - 동의어 생성
- 동의어 생성 권한을 부여했다면, SCOTT 계정으로 접속하여 다음과 같이 EMP 테이블에 동의어 E를 만들어보자!
```sql
CREATE SYNONYM E
 FOR EMP;
```
- E동의어로 SELECT문을 실행하면 EMP 테이블의 데이터가 조회된다.
```sql
SELECT * FROM E;
```

### - 동의어 삭제
- DROP SYNONYM을 사용하여 동의어를 삭제한다.
- 동의어 삭제하기
```sql
DROP SYNONYM E;
```
- 동의어를 삭제하면 이제 E동의어로 SELECT를 할 수는 없지만, EMP 테이블 이름과 데이터에는 아무 영향을 주지 않는다.

---
## 잊기 전에 한 번 더!
Q1.
```sql
1.
CREATE TABLE EMPIDX
AS (SELECT * FROM EMP WHERE 1<>1);
INSERT INTO EMPIDX SELECT * FROM EMP;

2.
CREATE INDEX IDX_EMPIDX_EMPNO ON EMPIDX(EMPNO);

3.
SELECT * FROM USER_IND_COLUMNS;
```
Q2.
```sql
CREATE OR REPLACE VIEW EMPIDX_OVER15K
    AS(SELECT EMPNO, ENAME, JOB, DEPTNO, SAL, NVL2(COMM, 'O', 'X') AS COMM
        FROM EMPIDX
        WHERE SAL > 1500);
```
Q3.
```sql
1.
CREATE TABLE DEPTSEQ
    AS (SELECT * FROM DEPT WHERE 1<>1);
INSERT INTO DEPTSEQ
    SELECT * FROM DEPT;
2.
CREATE SEQUENCE SEQ_DEPT
INCREMENT BY 1
START WITH 1 
MAXVALUE 99
MINVALUE 1
NOCYCLE
NOCACHE;
3.
INSERT INTO DEPTSEQ
(DEPTNO, DNAME, LOC)
VALUES(SEQ_DEPT.NEXTVAL, 'DATABASE', 'SEOUL');
INSERT INTO DEPTSEQ
(DEPTNO, DNAME, LOC)
VALUES(SEQ_DEPT.NEXTVAL, 'WEB', 'BUSAN');
INSERT INTO DEPTSEQ
(DEPTNO, DNAME, LOC)
VALUES(SEQ_DEPT.NEXTVAL, 'MOBILE', 'ILSAN');
```