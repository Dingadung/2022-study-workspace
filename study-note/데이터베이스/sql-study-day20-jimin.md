sql-study-day20-jimin
# 14강-제약조건
## 14-1 제약 조건 종류
### - 제약 조건이란?
- 오라클에서 사용하는 제약 조건은 테이블의 **특정 열**에 지정한다.
- **제약 조건을 지정한** **열에 제약 조건에 부합하지 않는 데이터를 저장할 수 없다**.
- 제약 조건 지정 방식에 따라 **기존 데이터의 수정이나 삭제 가능 여부도 영향을 받는다**.
- 예;
  - 로그인에 사용할 아이디나 이메일 주소를 중복하지 않도록 설정할 수 있다.
  - 회원 가입할 때 이름, 생년월일 등의 데이터는 필수 입력 항목으로 두어 빈값(NULL)을 허용하지 않도록 지정할 수 있다.
- 오라클 데이터베이스에서 사용하는 제약조건

|종류|설명|
|--------|--------------------------------------|
|**NOT NULL**|지정한 열에 NULL을 허용하지 않는다. NULL을 제외한 데이터의 중복은 허용된다.|
|**UNIQUE**|지정한 열이 유일한 값을 가져야 한다. 즉, 중복될 수 없다. 단, NULL은 값의 중복에서 제외된다.|
|**PRIMARY KEY**|지정한 열이 유일한 값이면서 NULL을 허용하지 않는다. PRIMARY KEY는 테이블에 하나만 지정 가능하다.|
|**FOREIGN KEY**|다른 테이블의 열을 참조하여 존재하는 값만 입력할 수 있다.|
|**CHECK**|설정한 조건식을 만족하는 데이터만 입력 가능하다.|
### - 데이터 무결성이란?
- 데이터 무결성(data integrity)은 데이터베이스에 저장되는 데이터의 정확성과 일관성을 보장한다는 의미이다.
- 이를 위해 항상 유지해야 하는 기본 규칙(제약 조건)을 가지고 있다.
- 제약 조건은 이러한 데이터 무결성을 지키기 위한 안전장치로서 중요한 역할을 한다.
- 테이블 데이터의 삽입(INSERT), 수정(UPDATE), 삭제(DELETE)등 모든 과정에서 지켜야 한다.
- 무결성의 종류

|종류|설명|
|----------|--------------------------|
|**영역 무결성**<br>(domain integrity)|열에 저장되는 값의 적정 여부를 확인. 자료형, 적절한 형식의 데이터, NULL의 여부 같은 정해 놓은 범위를 만족하는 데이터임을 규정|
|**개체 무결성**<br>(entity integrity)|테이블 데이터를 유일하게 식별할 수 있는 기본키는 반드시 값을 가지고 있어야 하며 NULL이 될 수 없고 중복될 수도 없음을 규정|
|**참조 무결성**<br>(referential integrity)|참조 테이블의 외래키 값은 참조 테이블의 기본키로서 존재해야 하며 NULL이 가능|

- 이러한 무결성을 보장하기 위해 오라클에서는 다섯가지 제약 조건을 제공한다.
- 제약 조건은 데이터 베이스 설계 시점, 즉 테이블을 생성할 때 주로 지정한다.
- 하지만, 테이블 생성 후에도 추가, 변경, 삭제할 수 있다.
⇒ 제약조건은 데이터 정의어(DDL)에서 활용한다.

## 14-2 빈값을 허락하지 않는 NOT NULL
### - 테이블을 생성하며 제약 조건 지정
- NOT NULL은 특정 열에 데이터의 중복 여부와는 상관없이 NULL의 저장을 허용하지 않는 제약 조건이다.
- 열에 값이 존재해야하는 경우에 지정한다.
- 테이블을 생성할 때 NOT NULL 설정하기
```sql
CREATE TABLE TABLE_NOTNULL(
    LOGIN_ID VARCHAR(20) NOT NULL,
    LOGIN_PWD VARCHAR(20) NOT NULL,
    TEL     VARCHAR(20)
);

DESC TABLE_NOTNULL;
```
- 제약 조건이 NOT NULL인 열에 NULL 값 넣어보기
```sql
INSERT INTO TABLE_NOTNULL (LOGIN_ID, LOGIN_PWD, TEL)
VALUES( 'TEST_ID_01', NULL, '010-1234-5678');
```
![](https://velog.velcdn.com/images/mini_mouse_/post/20127cd0-f5b4-41da-ade3-ca31b32659b6/image.png)

- 제약 조건이 없는 TEL 열에 NULL 값 입력하기
```sql
INSERT INTO TABLE_NOTNULL (LOGIN_ID, LOGIN_PWD)
VALUES( 'TEST_ID_01', '1234');
SELECT * FROM TABLE_NOTNULL;
```

- 제약 조건을 지정한 열은 항상 해당 제약 조건을 만족행하므로 신규 데이터의 삽입 뿐만 아니라 기존 데이터의 수정 및 삭제에도 영향을 준다.
  - NOT NULL 제약 조건이 지정된 열 데이터를 NULL 값으로 업데이트하기
```sql
UPDATE TABLE_NOTNULL
    SET LOGIN_PWD = NULL
WHERE LOGIN_ID = 'TEST_ID_01'; -> 오류 뜬다.
```
### - 제약 조건 확인하기
- 지정한 제약 정보를 호가인하려면 다음과 같은 USER_CONSTRAINTS 데이터 사전을 활용한다.

|열 이름|설명|
|--------|-------------------------------------|
|OWNER|제약 조건 소유 계정|
|CONSTRAINT_NAME|제약 조건 이름(직접 지정하지 않을 경우 오라클이 자동으로 지정함|
|CONSTRAINT_TYPE|제약 조건 종류<br>C: CHECK, NOT NULL<br>U: UNIQUE<br>P: PRIMARY KEY<br>R: FOREIGN KEY|
|TABLE_NAME|제약 조건을 지정한 테이블 이름|

- 제약 조건 살펴보기(SCOTT 계정)
```sql
SELECT OWNER, CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
    FROM USER_CONSTRAINTS;
```

### - 제약 조건 이름 직접 지정
- 제약 조건에 이름을 직접 지정해주지 않으면 오라클에서 자동으로 이름을 지정해준다.
- 제약 조건에 이름을 직접 지정하려면 CONSTRAINT 키워드를 사용한다.
```sql
CREATE TABLE TABLE_NOTNULL2(
    LOGIN_ID VARCHAR(20) CONSTRAINT TBLNN2_LGNID_NN NOT NULL,
    LOGIN_PWD VARCHAR(20) CONSTRAINT TBLNB2_LGNPW_NN NOT NULL,
    TEL     VARCHAR2(20)
);
```
- 실무에서 제약 조건 이름을 지정할 때
  - 오라클이 자동으로 지정해 주는 이름은 제약 조건이 많아진 후 찾기 어려워질 수 있으므로 실무에서는 이름 붙이는 규칙을 정하여 제약 조건 이름을 직접 지정하는 경우가 많다.

### - 이미 생성한 테이블에 제약 조건 지정
- **제약 조건은 저장할 데이터에 제한을 주는 규칙**으로 작용한다.
- 이러한 특성으로 인해 제약 조건은 데이터와 테이블을 설계하는 시점, 즉 데이터베이스 사용 주기에서 **비교적 초기**에 지정하는 것이 일반적이다.
- 하지만 경우에 따라 이미 생성되어 있는 테이블에 제약 조건을 추가하거나 제약 조건을 변경 또는 삭제해야하는 경우도 종종 생긴다.
#### -- 생성한 테이블에 제약 조건 추가하기
- NOT NULL 제약 조건의 추가는 ALTER명령어와 MODIFY 키워드를 사용한다.
- TEL 열에 NOT NULL 제약 조건 추가하기
```sql
ALTER TABLE TABLE_NOTNULL
MODIFY(TEL NOT NULL);
```
- 위의 실습을 실행하면 오류가 발생하고 제약 조건 추가는 실패하게 된다.
- 이는, 제약 조건 대상이 되는 열이 가진 데이터 중 추가하려는 계약 조건이 맞지 않는 데이터가 존재하기 때문이다.

> 제약 조건 추가를 위한 명령어를 잘 작성했어도 **제약 조건과 맞지 않는 데이터가 이미 있다면** 제약 조건 지정이 실패한다.

- TEL 열 데이터 수정하기 (NULL → 문자열 값 삽입)
```sql
UPDATE TABLE_NOTNULL
    SET TEL = '010-1234-5678'
WHERE LOGIN_ID = 'TEST_ID_01';

SELECT * FROM TABLE_NOTNULL;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/f05531fe-d2df-4fed-963c-a43ed4c887f1/image.png)

- NOT NULL 제약 조건 추가하기
```sql
ALTER TABLE TABLE_NOTNULL
MODIFY(TEL NOT NULL);

SELECT OWNER, CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
    FROM USER_CONSTRAINTS;
```
(CONSTRAINT TYPE이 C면 NOT NULL 또는 CHECK라는 뜻이다.)

#### -- 생성한 테이블에 제약 조건 이름 직접 지정해서 추가하기
- 제약 조건 이름을 직접 지정하려면 CREATE와 마찬가지로 CONSTRAINT 키워드를 사용하면 된다.
```sql
ALTER TABLE TABLE_NOTNULL2
MODIFY(TEL CONSTRAINT TBLNN_TEL_NN NOT NULL);

SELECT OWNER, CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
    FROM USER_CONSTRAINTS;
```
- TABLE_NOTNULL2 테이블 열 구조 확인하기
```sql
DESC TABLE_NOTNULL2;
```
#### -- 생성한 제약 조건의 이름 변경하기
- 이미 생성한 제약 조건의 이름을 변경하려면, ALTER 명령어에 RENAME CONSTRAINT 키워드를 사용한다.
- 이미 생성된 제약 조건 이름 변경하기
```sql
ALTER TABLE TABLE_NOTNULL2
RENAME CONSTRAINT TBLNN_TEL_NN TO TBLNN2_TEL_NN;
```

### - 제약 조건 삭제
- ALTER 명령어에 DROP CONSTRAINT 키워드를 사용하면 지정한 제약 조건을 삭제할 수 있다.
- 제약 조건 삭제하기
```sql
ALTER TABLE TABLE_NOTNULL2
DROP CONSTRAINT TBLNN2_TEL_NN;
```

## 14-3 중복되지 않는 값 UNIQUE
- UNIQUE 제약 조건은 열에 저장할 데이터의 중복을 허용하지 않고자 할 때 사용한다.
- NULL은 값이 존재하지 않음을 의미하기 때문에 중복 대상에서는 제외된다.
- 즉, UNIQUE 제약조건을 지정한 열에 NULL은 여러 개 존재할 수 있다.
### - 테이블을 생성하며 제약 조건 지정
- UNIQUE 제약 조건 역시 CREATE문으로 테이블을 생성할 때 지정할 수 있다.
- 제약 조건 지정하기(테이블을 생성할 때)
```sql
CREATE TABLE TABLE_UNIQUE(
    LOGIN_ID VARCHAR2(20) UNIQUE,
    LOGIN_PWD VARCHAR2(20) NOT NULL,
    TEL        VARCHAR2(20)
);
```
### - 제약 조건 확인
- USER_CONSTRAINTS 데이터 사전 뷰로 제약 조건 확인하기
```sql
SELECT OWNER, CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
    FROM USER_CONSTRAINTS
    WHERE TABLE_NAME = 'TABLE_UNIQUE';
```

### 중복을 허락하지 않는 UNIQUE
- TABLE_UNIQUE 테이블에 데이터 입력하기
```sql
INSERT INTO TABLE_UNIQUE(LOGIN_ID, LOGIN_PWD, TEL)
VALUES('TEST_ID_01', 'PWD01', '010-1234-5678');
SELECT * FROM TABLE_UNIQUE;
```
- LOGIN_ID 열에 중복되는 데이터 넣기
```sql
INSERT INTO TABLE_UNIQUE(LOGIN_ID, LOGIN_PWD, TEL)
VALUES('TEST_ID_02', 'PWD01', '010-1234-5678');
SELECT * FROM TABLE_UNIQUE;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/07df27d0-c1d2-44a4-b9a2-64e402c08542/image.png)
### - UNIQUE 제약 조건과 NULL 값
- UNIQUE 제약조건은 열 값의 중복은 허용하지 않지만 NULL 저장은 가능하다.
- NULL은 존재하지 않는 값 또는 해당 사항이 없다는 의미로 특수한 값이므로 NULL과 NULL을 비교했을 때, 값이 같은지 확인 할 수 없다.
⇒ 즉, NULL은 데이터 중복의 의미를 부여할 수 없기 때문에,
UNIQUE 제약 조건이 지정된 열에는  NULL이 여러 개 존재할 수 있다.
- UNIQUE 제약 조건이 지정된 열에 NULL 값 입력하기
```sql
INSERT INTO TABLE_UNIQUE(LOGIN_ID, LOGIN_PWD, TEL)
VALUES(NULL, 'PWD01', '010-1234-5678');
SELECT * FROM TABLE_UNIQUE;
```
- TABLE_UNIQUE 테이블 데이터 수정하기
```sql
UPDATE TABLE_UNIQUE
SET LOGIN_ID = 'TEST_ID_01'
WHERE LOGIN_ID IS NULL;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/595743a0-c51f-4aa6-b7ff-5217a8caccb4/image.png)

### - 테이블을 생성하며 제약 조건 이름 직접 지정
- UNIQUE 제약 조건 역시 제약 조건 이름을 지정할 수 있으며 지정하지 않으면 오라클이 자동으로 제약 조건 이름을 정해준다.
- 테이블을 생성할 때 UNIQUE 제약 조건 설정하기
```sql
CREATE TABLE TABLE_UNIQUE2(
    LOGIN_ID VARCHAR2(20)CONSTRAINT TBLUNQ2_LGNID_UNQ UNIQUE,
    LOGIN_PWD VARCHAR(20) CONSTRAINT TBLUNQ2_LGNPWD_NN NOT NULL,
    TEL     VARCHAR(20)
);
```
- 생성한 UNIQUE 제약 조건 확인하기(USER_CONSTRAINTS 사용)
```sql
SELECT OWNER, CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
    FROM USER_CONSTRAINTS
    WHERE TABLE_NAME LIKE 'TABLE_UNIQUE%';
```
![](https://velog.velcdn.com/images/mini_mouse_/post/32c7661e-bb2a-4595-8927-1b428bf48b44/image.png)
### - 이미 생성한 테이블에 제약 조건 지정
- ALTER 명령어로 이미 생성되어 있는 테이블에 UNIQUE 제약 조건을 추가할 수 있다.
#### -- 생성한 테이블에 제약 조건 추가하기
- 이미 생성한 테이블 열에 UNIQUE 제약 조건 추가하기
```sql
ALTER TABLE TABLE_UNIQUE
MODIFY(TEL UNIQUE); → 이미 중복값이 존재해서 오류
```
- TEL 열 값을 모두  NULL 값으로 변경하기
```sql
UPDATE TABLE_UNIQUE
SET TEL = NULL;
SELECT * FROM TABLE_UNIQUE;
```
- TEL 값에 UNIQUE 제약 조건 설정하기
```sql
ALTER TABLE TABLE_UNIQUE
MODIFY(TEL UNIQUE);
```
#### -- 생성한 테이블에 제약 조건 이름 직접 지정하거나 바꾸기
- UNIQUE 제약 조건 역시 이름을 직접 지정할 수 있으며 이후에 이름을 바꿀 수도 있다.
```sql
ALTER TABLE TABLE_UNIQUE2
MODIFY(TEL CONSTRAINT TBLUNQ_TEL_UNQ UNIQUE);
```
- 이미 만들어져 있는 UNIQUE 제약 조건 이름 수정하기
```sql
ALTER TABLE TABLE_UNIQUE2
RENAME CONSTRAINT TBLUNQ_TEL_UNQ TO TBLUNQ2_TEL_UNQ;
```
### - 제약 조건 삭제
- UNIQUE 제약 조건 삭제 역시 ALTER 명령어에 DROP CONSTRAINT 키워드를 사용한다.
- 제약 조건 삭제하기
```sql
ALTER TABLE TABLE_UNIQUE2
DROP CONSTRAINT TBLUNQ2_TEL_UNQ;
```

## 14-4 유일하게 하나만 있는 값 PRIMARY KEY
- PRIMARY KEY 제약조건은 UNIQUE와 NOT NULL 제약 조건의 특성을 모두 가지는 제약 조건이다.
⇒ 즉, 데이터 중복을 허용하지 않고, NULL도 허용하지 않는다.
- NULL이 아닌 유일한 값을 가지므로 주민등록번호나 EMP 테이블의 사원 번호 같이 **테이블의 각 행을 식별**하는데 활용된다.
- **PRIMARY KEY 제약 조건**은 **테이블에 하나만 지정**할 수 있다.
- 특정 열을 PRIMARY KEY로 지정하면 해당 열에는 자동으로 **인덱스**가 만들어 진다.
- 하지만 PRIMARY KEY 로 적합한 특성을 가졌다 할지라도 주민등록번호와 같은 예민한 개인 정보를 의미하는 데이터는 PRIMARY KEY로 지정하지 않는다.
### - 테이블을 생성하며 제약 조건 지정하기
- 테이블 PRIMARY KEY(기본키) 제약 조건은 앞에서 살펴본 제약 조건처럼 CREATE문으로 테이블을 생성하면서 지정할 수 있다.
- 테이블을 생성할 때 특정 열에 PRIMARY KEY 설정하기
```sql
REATE TABLE TABLE_PK(
    LOGIN_ID VARCHAR(20) PRIMARY KEY,
    LOGIN_PWD VARCHAR(20) NOT NULL,
    TEL     VARCHAR(20)
);

DESC TABLE_PK;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/798e8f4a-40b0-4390-a1ed-f7a55ec52e7c/image.png)
- 생성한 PRIMARY KEY 확인하기
```sql
SELECT OWNER, CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
    FROM USER_CONSTRAINTS
    WHERE TABLE_NAME LIKE 'TABLE_PK%';
```
![](https://velog.velcdn.com/images/mini_mouse_/post/4109f61a-caca-4e72-912f-e0fdcbc37847/image.png)
- PRIMARY KEY 제약 조건은 특정 테이블의 데이터를 식별하는 유일한 값이라는 뜻이다.
- 이 때문에, SELECT문을 통한 검색에 자주 활용되므로 PRIMARY KEY 제약 조건을 지정한 열에는 자동으로 인덱스가 만들어진다.
- 생성한 PRIMARY KEY를 통해 자동 생성된 INDEX 확인하기
```sql
SELECT INDEX_NAME, TABLE_OWNER, TABLE_NAME
    FROM USER_INDEXES
    WHERE TABLE_NAME LIKE 'TABLE_PK%';
```
![](https://velog.velcdn.com/images/mini_mouse_/post/e65e0e7a-d27c-44b1-b38e-d967269fc88a/image.png)
### - 테이블을 생성하며 제약조건 이름 직접 지정하기
- 제약 조건의 이름을 직접 지정하여 테이블 생성하기
```sql
CREATE TABLE TABLE_PK2(
    LOGIN_ID VARCHAR2(20) CONSTRAINT TBLPK2_LGNID_PK PRIMARY KEY,
    LOGIN_PWD VARCHAR(20) CONSTRAINT TBLOK2_LGNID_NN NOT NULL,
    TEL     VARCHAR(20)
);

DESC TABLE_PK2;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/5eb60e03-7a54-430b-9d86-abd3d4544994/image.png)
### - PRIMARY KEY 제약 조건을 지정한 열 확인(중복 값을 입력했을 때)
- PRIMARY KEY 제약 조건을 지정한 열에는 중복 값과 NULL이 허용되지 않는다.
- TABLE_PK 테이블에 데이터 입력하기
```sql
INSERT INTO TABLE_PK(LOGIN_ID, LOGIN_PWD, TEL)
VALUES ('TEST_ID_01', 'PWD02', '010-1234-5678');
```
- TABLE_PK 테이블에 중복되는 데이터 입력하기
```sql
INSERT INTO TABLE_PK(LOGIN_ID, LOGIN_PWD, TEL)
VALUES (NULL, 'PWD02', '010-1234-5678');
```
![](https://velog.velcdn.com/images/mini_mouse_/post/820142bb-46f0-45a5-a652-9f9ac9b4ec81/image.png)
### - PRIMARY KEY 제약 조건을 지정한 열 확인(NULL 값을 입력했을 때)
- NULL 값을 명시적으로 입력하기
```sql
INSERT INTO TABLE_PK(LOGIN_ID, LOGIN_PWD, TEL)
VALUES (NULL, 'PWD02', '010-1234-5678');
SELECT * FROM TABLE_PK;
```
- NULL 값을 암시적으로 입력하기
```sql
INSERT INTO TABLE_PK(LOGIN_PWD, TEL)
VALUES ('PWD02', '010-1234-5678');
SELECT * FROM TABLE_PK;
```
⇒ 둘 다 오류 난다.

- PRIMARY KEY 제약 조건 역시 ALTER문의 MODIFY, RENAME, DROP을 통해 추가, 수정, 이름 변경, 삭제 등의 수행이 가능하다.
- PRIMARY KEY 제약 조건은 테이블의 생성 시점에 확정되는 경우가 대부분이다.

---
### - CREATE문에서 제약 조건을 지정하는 다른 방식
1. 인라인(inline) 또는 열 레벨(column-level) 제약 조건 정의
   - 열 바로 옆에 제약 조건을 지정하는 형식
   - 이 책에 나오는 모든 제약 조건을 이 방식으로 지정할 수 있다.
```sql
CREATE TABLE TABLE_NAME(
	COL1 VARCHAR2(20) CONSTRAINT CONSTRAINT_NAME PRIMARY KEY,
    COL2 VARCHAR2(20) NOT NULL,
    COL3 VARCHAR2(20)
);
```
2. 아웃오브라인(out-of-line) 또는 테이블 레벨(table-level) 제약 조건 방식
   - 열을 정의한 후 별도로 제약 조건을 정의하는 것
   - 열을 명시한 후, 제약 조건을 테이블 단위에 지정하는 방식
   - NOT NULL 제약 조건을 제외한 제약 조건이 지정 가능하다.
```sql
CREATE TABLE TABLE_NAME(
	COL1 VARCHAR2(20) CONSTRAINT CONSTRAINT_NAME,
    COL2 VARCHAR2(20),
    COL3 VARCHAR2(20),
    PRIMARY KEY (COL1),
    CONSTRAINT CONSTAINT_NAME UNIQUE(COL2)
);
```

## 14-5 다른 테이블과 관계를 맺는 FOREIGN KEY
>외래키, 외부키로도 부르는 **FOREIGN KEY**는 **서로 다른 테이블 간 관계**를 정의하는데 사용하는 제약 조건이다.

- 특정 테이블에서 PRIMARY KEY 제약 조건을 지정한 열을, 다른 테이블의 특정 열에서 참조하겠다는 의미로 지정할 수 있다.
- EMP 테이블과 DEPT 테이블의 제약 조건 살펴보기
```sql
SELECT OWNER, CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME, R_OWNER, R_CONSTRAINT_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME IN ('EMP', 'DEPT');
```

- CONSTRAINT_TYPE 열 값이 R일 경우 외래키를 의미한다.
![](https://velog.velcdn.com/images/mini_mouse_/post/d4d641d8-1ccb-4ca4-acae-ece48469f786/image.png)

- 참조 관계를 정의하면 참조하려는 테이블(EMP)에서는 참조 테이블(DEPT)에 존재하는 기본키 열(DPETNO)에 존재하는 값과 NULL만 저장할 수 있다.
- FOREIGN KEY가 참조하는 열에 존재하지 않는 데이터 입력하기
```sql
INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(9999, '홍길동', 'CLERK', '7788', TO_DATE('2017/04/30', 'YYYY/MM/DD'), 1200, NULL, 50);
```
![](https://velog.velcdn.com/images/mini_mouse_/post/ab98d867-4bf6-488c-801e-2bd9c8ff6a86/image.png)
- 오류 메시지에 언급한 부모 키가 없습니다 라는 말은 DEPT 테이블의 DEPTNO열에 50이 존재하지 않는다는 뜻이다.

> 참조 대상 테이블 = **부모**
참조하는 테이블 = **자식**으로 표현한다.

### - FOREIGN KEY 지정하기
- FOREIGN KEY 지정은 지금까지 살펴본 제약 조건을 지정하는 방법과 비슷하다.
- 기본형식
```sql
CREATE TABLE 테이블 이름(
	...(다른 열 정의),
    열 자료형 CONSTRAINT [제약 조건 이름] PREFERENCES 참조 테이블(참조할 열)
);
```
- 제약 조건 이름을 지정하지 않고 FOEREIGN KEY 정의하기
```sql
CREATE TABLE 테이블 이름(
	...(다른 열 정의),
    열 자료형 CONSTRAINT PREFERENCES 참조 테이블(참조할 열)
);
```
- 열을 모두 정의한 후 제약 조건을 지정하기 
-> 마지막에 CONSTRAINT 키워드 사용
```sql
CREATE TABLE 테이블 이름(
	...(다른 열 정의),
    CONSTRAINT [제약조건이름] FOREIGN KEY(열) PREFERENCES 참조 테이블(참조할 열)
);
```
- DEPT_FK 테이블 생성하기
```sql
CREATE TABLE DEPT_FK(
    DEPTNO NUMBER(2) CONSTRAINT DEPTFK_DEPTNO_PK PRIMARY KEY,
    DNAME VARCHAR2(14),
    LOC   VARCHAR2(13)
    );
```
- EMP_FK 테이블 생성하기
```sql
CREATE TABLE EMP_FK(
    EMPNO NUMBER(4) CONSTRAINT EMPFK_EMPNO_PK PRIMARY KEY,
    ENAME VARCHAR2(10),
    JOB   VARCHAR2(9),
    MGR NUMBER(4),
    HIREDATE DATE,
    SAL NUMBER(7, 2),
    COMM NUMBER(7, 2),
    DEPTNO NUMBER(2) CONSTRAINT EMPKR_DEPTNO_FK REFERENCES DEPT_FK (DEPTNO)
);
```

#### -- FOREIGN KEY 지정할 때 유의점
- 위에서 만든, DEPT_FK 테이블에는 데이터가 아직 없는 상태이다.
- EMP_FK 테이블의 DEPTNO 열은 DPET_FK 테이블의 DEPTNO를 참조하기 때문에 DEPT_FK 테이블의 DEPTNO 열에 존재하지 않는 값을 사용하는 것은 불가능하다.
- EMP_FK 테이블에 데이터 삽입하기 
→ DEPTNO 데이터가 아직 DPET_FK 테이블에 없을 때
```sql
INSERT INTO EMP_FK
VALUES(9999, 'TEST_NMAME', 'TEST_JOB', NULL, TO_DATE('2001/01/01', 'YYYY/MM/DD'), 3000, NULL, 10);
```
- DEPT_FK에 데이터 삽입하기
```sql
INSERT INTO DEPT_FK
VALUES(10, 'TEST_DNAME', 'TEST_LOC');
SELECT * FROM DEPT_FK;
```
- EMP_FK 테이블에 데이터 삽입하기
```sql
INSERT INTO EMP_FK
VALUES(9999, 'TEST_NMAME', 'TEST_JOB', NULL, TO_DATE('2001/01/01', 'YYYY/MM/DD'), 3000, NULL, 10);
SELECT * FROM EMP_FK;
```

### - FOREIGN KEY로 참조 행 데이터 삭제하기
- DEPT_FK 테이블의 10번 부서 데이터 삭제하기
```sql
DELETE FROM DEPT_FK
WHERE DEPTNO = 10;
```
- 위의 코드는 오류가 발생하게 된다.
→ 오류가 발생하는 이유: 자식 레코드, 즉 삭제하려는 DEPTNO 값을 참조하는 데이터가 존재하기 때문이다.
- DEPT_FK 테이블의 데이터를 삭제하려면 다음 방법 중 한 가지를 사용해야 한다.
1. 현재 삭제하려는 열 값을 참조하는 데이터를 먼저 삭제한다.
→ EMP_FK 테이블의 DEPTNO가 10번인 데이터를 먼저 삭제한 후 DEPT_FK 테이블의 10번 부서 삭제
2. 현재 삭제하려는 열 값을 참조하는 데이터 수정
→ EMP_FK 테이블의 DEPTNO가 10번인 데이터를 다른 부서 번호 또는 NULL로 변경한 후 DPET_FK 테이블의 10번 부서 삭제
3. 현재 삭제하려는 열을 참조하는 자식 테이블의 FOREIGN KEY 제약 조건을 해제한다.

- 하지만 위의 방법들은 삭제할 데이터를 참조하는 데이터의 수정 또는 삭제 작업을 선행해야 하므로 귀찮다.
- FOREIGN KEY 제약 조건을 해제할 수 없는 경우도 종종 있으므로 이미 제약 조건으로 연결된 데이터의 삭제는 꽤나 까다로운 일이다.
- 따라서, 제약 조건을 처음 지정할 때 다음과 같이 추가 옵션을 지정하는 방법을 사용한다.
- 이 방법은 **데이터 삭제와 더불어 삭제할 데이터를 참조하는 처리**를 어떻게 할지 정할 수 있다.
1. 참조되는 열 데이터를 삭제할 때 이 데이터를 참조하고 있는 데이터도 함께 삭제
→ DEPT_FK 테이블의 DEPTNO 열값이 10인 데이터를 삭제하면 이를 참조하는 EMP_FK 테이블의 DEPTNO 열 값이 10인 데이터도 함께 삭제된다.
   - 기본 형식
```sql
CONSTRAINT [제약 조건 이름] REFERENCES 참조 테이블(참조할 열) ON DELETE CASCADE
```
- 참조되는 열 데이터를 삭제할 때 이 데이터를 참조하는 데이터를 NULL로 수정
→ DEPT_FK 테이블의 DEPTNO 열 값이 10인 데이터를 삭제하면 이를 참조하는 EMP_FK 테이블의 DPETNO 열 값이 10인 데이터를 NULL로 수정한다.
   - 기본 형식
```sql
CONSTRAINT [제약 조건 이름] REFERENCES 참조 테이블(참조할 열) ON DELETE SET NULL
```

- 참조 데이터를 지정하는 FOREIGN KEY 제약 조건도 PRIMARY KEY 제약 조건과 마찬가지로 테이블을 설계하는 시점에서 결정이 나는 경우가 많다.
⇒ 따라서 ALTER문을 사용한 제약 조건의 추가, 변경, 삭제 등 여러 기능을 수행할 수 있지만, 이 책에서는 많이 쓰이는 CREATE문 사용법만 나와있다.

## 14-6 데이터 형태와 범위를 정하는 CHECK
- CHECK 제약 조건은 열에 저장할 수 있는 값의 범위 또는 패턴을 정의할 때 사용한다.
  - 예를 들어, 시간을 저장할 열 데이터는 0에서 23까지의 숫자만을 허용한다.
- CHECK 제약 조건 역시 다른 제약 조건과 마찬가지로 지정할 수 있다.
- 테이블을 생성할 때 CHECK 제약 조건 설정하기
```sql
CREATE TABLE TABLE_CHECK(
    LOGIN_ID VARCHAR(20) CONSTRAINT TBLCK_LOGINID_PK PRIMARY KEY,
    LOGIN_PWD VARCHAR(20) CONSTRAINT TBLCK_LOGINPW_CK CHECK(LENGTH(LOGIN_PWD) >3),
    TEL     VARCHAR(20)
);
```
- 위의 코드에서 LENGTH(LOGIN_PWD)>3은 LOGIN_PWD의 열 길이가 3 이상인 데이터만 저장 가능하다는 뜻이다.
⇒ 즉, 비밀번호는 3글자 이상만 저장할 수 있도록 제한을 둔 것이다.
- CHECK 제약 조건은 단순 연산뿐만아니라, 함수 활용도 가능하다.
- CHECK 제약 조건에 맞지 않은 예(비밀번호 길이가 3자리 안넘음)
```sql
INSERT INTO TABLE_CHECK
VALUES('TEST_ID', '123', '010-1234-5678');
```
- CHECK 제약 조건에 맞는 예(비밀번호가 4자리)
```sql
INSERT INTO TABLE_CHECK
VALUES('TEST_ID', '1234', '010-1234-5678');
SELECT * FROM TABLE_CHECK;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/82580d35-1456-4c07-aea5-3aaa4b24f0f3/image.png)

## 14-7 기본값을 정하는 DEFAULT
>제약 조건과는 별개로 특정 열에 저장할 값이 지정되지 않았을 경우 기본값(default)을 지정할 수 있는데, 이때 사용되는 키워드가 DEFAULT이다.

- 테이블을 생성할 때 DEFAULT 제약 조건 설정하기
```sql
CREATE TABLE TABLE_DEFAULT(
    LOGIN_ID VARCHAR(20) CONSTRAINT TBLCK2_LOGINID_PK PRIMARY KEY,
    LOGIN_PWD VARCHAR(20) DEFAULT '1234',
    TEL     VARCHAR(20)
);
```
- DEFAULT로 지정한 기본값이 입려괴는 INSERT문 확인하기
```sql
INSERT INTO TABLE_DEFAULT VALUES('TEST_ID', NULL, '010-1234-5678');

INSERT INTO TABLE_DEFAULT (LOGIN_ID, TEL) VALUES('TEST_ID2', '010-1234-5678');

SELECT * FROM TABLE_DEFAULT;
```
![](https://velog.velcdn.com/images/mini_mouse_/post/9bee1a46-b49a-4180-ab9b-3c77d2cea3d9/image.png)

>NULL 값을 넣겠다고 지정해주면 NULL이 들어가지만, 아예 열 값을 지정하지 않으면 DEFAULT로 지정한 값이 들어가게 된다.

#### 제약 조건 비활성화, 활성화
- 제약 조건은 데이터 무결성을 보장하는 데 중요한 역할을 한다.
- 하지만 신규 기능 개발, 테이트 같은 특정 업무를 수행해야 할 때 제약 조건이 걸림돌이 되는 경우가 종종 생긴다.
- 이때 여러 필요에 의해 제약 조건을 비활성화하거나 비활성화되어 있는 제약 조건을 다시 활성화할 수 있다.
- 비활성화 → DISABLE절 이용
```sql
DISABLE [NOVALIDATE / VALIDATE(선택)] CONSTRAINT 제약조건이름;
```
- 활성화 → ENABLE절 이용
```sql
ENABLE [NONVALIDATE / VALIDATE(선택)] CONSTRAINT 제약조건이름;
```
- 제약 조건의 비활성화와 활성화라는, 제약 조건의 제한을 일시적으로 풀어주는 방법이 존재한다는 점을 기억해두면 도움이 된다.

---
## 잊기 전에 한 번 더!
Q1. DEPT_CONST 테이블 생성하기
```sql
CREATE TABLE DEPT_CONST(
    DEPTNO NUMBER(2) CONSTRAINT DEPTCONST_DEPTNO_PK PRIMARY KEY,
    DNAME VARCHAR2(14) CONSTRAINT DEPTCONST_DNAME_UNQ UNIQUE,
    LOC VARCHAR(13) CONSTRAINT DEPTCONST_LOC_NN NOT NULL
);
```
Q2. EMP_CONST 테이블 생성하기
```sql
CREATE TABLE EMP_CONST(
    EMPNO NUMBER(4) CONSTRAINT EMPCONST_EMPNO_PK PRIMARY KEY,
    ENAME VARCHAR2(10) CONSTRAINT EMPCONST_ENAME_NN NOT NULL,
    JOB VARCHAR2(9),
    TEL VARCHAR2(20) CONSTRAINT EMPCONST_TEL_UNQ UNIQUE,
    HIREDATE DATE,
    SAL NUMBER(7, 2) CONSTRAINT EMPCONST_SAL_CHK CHECK(SAL BETWEEN 1000 AND 9999),
    COMM NUMBER(7, 2),
    DEPTNO NUMBER(2) CONSTRAINT EMPCONST_DEPTNO_FK REFERENCES DEPT_CONST(DEPTNO)
);
```
Q3. 위의 두 테이블의 제약 조건 확인하기
```sql
SELECT OWNER, CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME LIKE '%CONST%';
```