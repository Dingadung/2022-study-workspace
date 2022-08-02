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
