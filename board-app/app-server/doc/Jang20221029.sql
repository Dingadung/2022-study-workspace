-- 전시회한줄리뷰
DROP TABLE IF EXISTS jang_exhibition_review RESTRICT;

-- 알림
DROP TABLE IF EXISTS jang_alarm RESTRICT;

-- 이동수단
DROP TABLE IF EXISTS jang_transportation RESTRICT;

-- QnA
DROP TABLE IF EXISTS jang_qna RESTRICT;

-- 검색키워드
DROP TABLE IF EXISTS jang_search RESTRICT;

-- 팔로워팔로잉
DROP TABLE IF EXISTS jang_follow RESTRICT;

-- 코스추천글
DROP TABLE IF EXISTS jang_so_recommendation RESTRICT;

-- 장소후기첨부파일
DROP TABLE IF EXISTS jang_so_review_file RESTRICT;

-- 댓글
DROP TABLE IF EXISTS jang_comment RESTRICT;

-- 장소
DROP TABLE IF EXISTS jang_so RESTRICT;

-- 공지사항
DROP TABLE IF EXISTS jang_notice RESTRICT;

-- 전시회
DROP TABLE IF EXISTS jang_exhibition RESTRICT;

-- 신고
DROP TABLE IF EXISTS jang_report RESTRICT;

-- 장바구니
DROP TABLE IF EXISTS jang_baguni RESTRICT;

-- 전시회티켓구매
DROP TABLE IF EXISTS jang_ticket RESTRICT;

-- 전시회첨부파일
DROP TABLE IF EXISTS jang_exhibition_file RESTRICT;

-- 좋아요
DROP TABLE IF EXISTS jang_like RESTRICT;

-- 방문히스토리
DROP TABLE IF EXISTS jang_history RESTRICT;

-- 장소후기
DROP TABLE IF EXISTS jang_so_review RESTRICT;

-- 회원
DROP TABLE IF EXISTS jang_member RESTRICT;

-- 전시회한줄리뷰
CREATE TABLE jang_exhibition_review (
  RVNO  INTEGER     NOT NULL COMMENT '리뷰번호', -- 리뷰번호
  CONT  MEDIUMTEXT  NOT NULL COMMENT '내용', -- 내용
  WDATE DATE        NOT NULL DEFAULT curdate() COMMENT '작성일', -- 작성일
  ID    VARCHAR(50) NOT NULL COMMENT '아이디', -- 아이디
  EXNO  INTEGER     NOT NULL COMMENT '전시회번호' -- 전시회번호
)
COMMENT '전시회한줄리뷰';

-- 전시회한줄리뷰
ALTER TABLE jang_exhibition_review
  ADD CONSTRAINT PK_jang_exhibition_review -- 전시회한줄리뷰 기본키
    PRIMARY KEY (
      RVNO -- 리뷰번호
    );

ALTER TABLE jang_exhibition_review
  MODIFY COLUMN RVNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '리뷰번호';

-- 알림
CREATE TABLE jang_alarm (
  ALNO   INTEGER     NOT NULL COMMENT '알림번호', -- 알림번호
  ID     VARCHAR(50) NOT NULL COMMENT '아이디', -- 아이디
  CONT   MEDIUMTEXT  NOT NULL COMMENT '내용', -- 내용
  ALTIME DATE        NOT NULL COMMENT '알림시간', -- 알림시간
  RDTIME DATE        NULL     COMMENT '읽은시간' -- 읽은시간
)
COMMENT '알림';

-- 알림
ALTER TABLE jang_alarm
  ADD CONSTRAINT PK_jang_alarm -- 알림 기본키
    PRIMARY KEY (
      ALNO -- 알림번호
    );

ALTER TABLE jang_alarm
  MODIFY COLUMN ALNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '알림번호';

-- 이동수단
CREATE TABLE jang_transportation (
  TPNO   INTEGER     NOT NULL COMMENT '이동수단번호', -- 이동수단번호
  TPNAME VARCHAR(50) NOT NULL COMMENT '이동수단명' -- 이동수단명
)
COMMENT '이동수단';

-- 이동수단
ALTER TABLE jang_transportation
  ADD CONSTRAINT PK_jang_transportation -- 이동수단 기본키
    PRIMARY KEY (
      TPNO -- 이동수단번호
    );

ALTER TABLE jang_transportation
  MODIFY COLUMN TPNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '이동수단번호';

-- QnA
CREATE TABLE jang_qna (
  QNANO   INTEGER      NOT NULL COMMENT 'QnA번호', -- QnA번호
  ID      VARCHAR(50)  NOT NULL COMMENT '아이디', -- 아이디
  PBL     BOOLEAN      NULL     COMMENT '공개여부', -- 공개여부
  TITLE   VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  CONT    MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  WDATE   DATE         NOT NULL DEFAULT curdate() COMMENT '작성일', -- 작성일
  ANSCONT MEDIUMTEXT   NULL     COMMENT '답변내용', -- 답변내용
  ANSDATE DATE         NULL     COMMENT '답변일' -- 답변일
)
COMMENT 'QnA';

-- QnA
ALTER TABLE jang_qna
  ADD CONSTRAINT PK_jang_qna -- QnA 기본키
    PRIMARY KEY (
      QNANO -- QnA번호
    );

ALTER TABLE jang_qna
  MODIFY COLUMN QNANO INTEGER NOT NULL AUTO_INCREMENT COMMENT 'QnA번호';

-- 검색키워드
CREATE TABLE jang_search (
  SKNO   INTEGER     NOT NULL COMMENT '검색키워드번호', -- 검색키워드번호
  KWD    VARCHAR(50) NOT NULL COMMENT '키워드', -- 키워드
  SCDATE DATE        NOT NULL DEFAULT curdate() COMMENT '검색일' -- 검색일
)
COMMENT '검색키워드';

-- 검색키워드
ALTER TABLE jang_search
  ADD CONSTRAINT PK_jang_search -- 검색키워드 기본키
    PRIMARY KEY (
      SKNO -- 검색키워드번호
    );

ALTER TABLE jang_search
  MODIFY COLUMN SKNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '검색키워드번호';

-- 팔로워팔로잉
CREATE TABLE jang_follow (
  ID    VARCHAR(50) NOT NULL COMMENT '본인아이디', -- 본인아이디
  OTHID VARCHAR(50) NOT NULL COMMENT '다른이아이디' -- 다른이아이디
)
COMMENT '팔로워팔로잉';

-- 팔로워팔로잉
ALTER TABLE jang_follow
  ADD CONSTRAINT PK_jang_follow -- 팔로워팔로잉 기본키
    PRIMARY KEY (
      ID,    -- 본인아이디
      OTHID  -- 다른이아이디
    );

-- 코스추천글
CREATE TABLE jang_so_recommendation (
  RECONO INTEGER      NOT NULL COMMENT '코스추천글번호', -- 코스추천글번호
  ID     VARCHAR(50)  NOT NULL COMMENT '아이디', -- 아이디
  TITLE  VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  WDATE  DATE         NOT NULL DEFAULT curdate() COMMENT '등록일', -- 등록일
  CNT    INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  ACT    BOOLEAN      NOT NULL COMMENT '활성여부', -- 활성여부
  PET    BOOLEAN      NULL     COMMENT '반려동물동반가능', -- 반려동물동반가능
  FRD    BOOLEAN      NOT NULL COMMENT '친구동행추천', -- 친구동행추천
  CPLE   BOOLEAN      NOT NULL COMMENT '연인동행추천', -- 연인동행추천
  FMLY   BOOLEAN      NOT NULL COMMENT '가족동행추천', -- 가족동행추천
  SOLO   BOOLEAN      NOT NULL COMMENT '혼자여행추천', -- 혼자여행추천
  TPNO   INTEGER      NOT NULL COMMENT '이동수단번호' -- 이동수단번호
)
COMMENT '코스추천글';

-- 코스추천글
ALTER TABLE jang_so_recommendation
  ADD CONSTRAINT PK_jang_so_recommendation -- 코스추천글 기본키
    PRIMARY KEY (
      RECONO -- 코스추천글번호
    );

ALTER TABLE jang_so_recommendation
  MODIFY COLUMN RECONO INTEGER NOT NULL AUTO_INCREMENT COMMENT '코스추천글번호';

-- 장소후기첨부파일
CREATE TABLE jang_so_review_file (
  RECOFNO INTEGER      NOT NULL COMMENT '장소후기첨부파일번호', -- 장소후기첨부파일번호
  PRVNO   INTEGER      NOT NULL COMMENT '장소후기번호', -- 장소후기번호
  PATH    VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  FNAME   VARCHAR(50)  NOT NULL COMMENT '파일명' -- 파일명
)
COMMENT '장소후기첨부파일';

-- 장소후기첨부파일
ALTER TABLE jang_so_review_file
  ADD CONSTRAINT PK_jang_so_review_file -- 장소후기첨부파일 기본키
    PRIMARY KEY (
      RECOFNO -- 장소후기첨부파일번호
    );

ALTER TABLE jang_so_review_file
  MODIFY COLUMN RECOFNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '장소후기첨부파일번호';

-- 댓글
CREATE TABLE jang_comment (
  CMNO   INTEGER     NOT NULL COMMENT '댓글번호', -- 댓글번호
  RECONO INTEGER     NOT NULL COMMENT '코스추천글번호', -- 코스추천글번호
  ID     VARCHAR(50) NOT NULL COMMENT '아이디', -- 아이디
  CONT   MEDIUMTEXT  NOT NULL COMMENT '댓글내용', -- 댓글내용
  WDATE  DATE        NOT NULL DEFAULT curdate() COMMENT '댓글작성일자' -- 댓글작성일자
)
COMMENT '댓글';

-- 댓글
ALTER TABLE jang_comment
  ADD CONSTRAINT PK_jang_comment -- 댓글 기본키
    PRIMARY KEY (
      CMNO -- 댓글번호
    );

ALTER TABLE jang_comment
  MODIFY COLUMN CMNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 장소
CREATE TABLE jang_so (
  PLNO     INTEGER      NOT NULL COMMENT '장소번호', -- 장소번호
  PLNAME   VARCHAR(255) NOT NULL COMMENT '장소명', -- 장소명
  QCALL    VARCHAR(30)  NOT NULL COMMENT '문의번호', -- 문의번호
  POSTNO   INTEGER      NOT NULL COMMENT '우편번호', -- 우편번호
  BASICAD  VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  DETAILAD VARCHAR(255) NOT NULL COMMENT '상세주소', -- 상세주소
  WIDO     VARCHAR(50)  NOT NULL COMMENT '위도', -- 위도
  KDO      VARCHAR(50)  NOT NULL COMMENT '경도' -- 경도
)
COMMENT '장소';

-- 장소
ALTER TABLE jang_so
  ADD CONSTRAINT PK_jang_so -- 장소 기본키
    PRIMARY KEY (
      PLNO -- 장소번호
    );

ALTER TABLE jang_so
  MODIFY COLUMN PLNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '장소번호';

-- 공지사항
CREATE TABLE jang_notice (
  NTNO  INTEGER      NOT NULL COMMENT '공지사항번호', -- 공지사항번호
  TITLE VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  CONT  MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  WDATE DATE         NOT NULL DEFAULT curdate() COMMENT '작성일' -- 작성일
)
COMMENT '공지사항';

-- 공지사항
ALTER TABLE jang_notice
  ADD CONSTRAINT PK_jang_notice -- 공지사항 기본키
    PRIMARY KEY (
      NTNO -- 공지사항번호
    );

ALTER TABLE jang_notice
  MODIFY COLUMN NTNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '공지사항번호';

-- 전시회
CREATE TABLE jang_exhibition (
  EXNO   INTEGER      NOT NULL COMMENT '전시회번호', -- 전시회번호
  EXNAME VARCHAR(255) NOT NULL COMMENT '전시회명', -- 전시회명
  CONT   MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  WDATE  DATE         NOT NULL DEFAULT curdate() COMMENT '게시글등록일', -- 게시글등록일
  PLNO   INTEGER      NOT NULL COMMENT '장소번호', -- 장소번호
  STDATE DATE         NOT NULL COMMENT '시작일', -- 시작일
  EDDATE DATE         NOT NULL COMMENT '종료일', -- 종료일
  PRICE  INTEGER      NOT NULL COMMENT '판매가격' -- 판매가격
)
COMMENT '전시회';

-- 전시회
ALTER TABLE jang_exhibition
  ADD CONSTRAINT PK_jang_exhibition -- 전시회 기본키
    PRIMARY KEY (
      EXNO -- 전시회번호
    );

ALTER TABLE jang_exhibition
  MODIFY COLUMN EXNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '전시회번호';

-- 신고
CREATE TABLE jang_report (
  RPNO    INTEGER     NOT NULL COMMENT '신고번호', -- 신고번호
  RECONO  INTEGER     NOT NULL COMMENT '코스추천글번호', -- 코스추천글번호
  ID      VARCHAR(50) NOT NULL COMMENT '(신고자)아이디', -- (신고자)아이디
  RSN     MEDIUMTEXT  NOT NULL COMMENT '신고사유', -- 신고사유
  RST     MEDIUMTEXT  NULL     COMMENT '처리내용', -- 처리내용
  RSTDATE DATE        NULL     COMMENT '처리일' -- 처리일
)
COMMENT '신고';

-- 신고
ALTER TABLE jang_report
  ADD CONSTRAINT PK_jang_report -- 신고 기본키
    PRIMARY KEY (
      RPNO -- 신고번호
    );

-- 신고 유니크 인덱스
CREATE UNIQUE INDEX UIX_jang_report
  ON jang_report ( -- 신고
    ID ASC,     -- (신고자)아이디
    RECONO ASC  -- 코스추천글번호
  );

ALTER TABLE jang_report
  MODIFY COLUMN RPNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

-- 장바구니
CREATE TABLE jang_baguni (
  ID   VARCHAR(50) NOT NULL COMMENT '아이디', -- 아이디
  EXNO INTEGER     NOT NULL COMMENT '전시회번호', -- 전시회번호
  CNT  INTEGER     NOT NULL COMMENT '수량', -- 수량
  ACT  BOOLEAN     NOT NULL COMMENT '활성여부' -- 활성여부
)
COMMENT '장바구니';

-- 장바구니
ALTER TABLE jang_baguni
  ADD CONSTRAINT PK_jang_baguni -- 장바구니 기본키
    PRIMARY KEY (
      ID,   -- 아이디
      EXNO  -- 전시회번호
    );

-- 전시회티켓구매
CREATE TABLE jang_ticket (
  EXTKNO  INTEGER     NOT NULL COMMENT '전시회티켓구매번호', -- 전시회티켓구매번호
  ID      VARCHAR(50) NOT NULL COMMENT '아이디', -- 아이디
  EXNO    INTEGER     NOT NULL COMMENT '전시회번호', -- 전시회번호
  CNT     INTEGER     NOT NULL COMMENT '구매수량', -- 구매수량
  PURDATE DATE        NOT NULL DEFAULT curdate() COMMENT '구매일', -- 구매일
  PAYMENT VARCHAR(50) NOT NULL COMMENT '결제수단', -- 결제수단
  CCDATE  DATE        NULL     COMMENT '취소일', -- 취소일
  CRDATE  DATE        NULL     COMMENT '취소요청일' -- 취소요청일
)
COMMENT '전시회티켓구매';

-- 전시회티켓구매
ALTER TABLE jang_ticket
  ADD CONSTRAINT PK_jang_ticket -- 전시회티켓구매 기본키
    PRIMARY KEY (
      EXTKNO -- 전시회티켓구매번호
    );

ALTER TABLE jang_ticket
  MODIFY COLUMN EXTKNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '전시회티켓구매번호';

-- 전시회첨부파일
CREATE TABLE jang_exhibition_file (
  EXFNO INTEGER      NOT NULL COMMENT '전시회게시글첨부파일번호', -- 전시회게시글첨부파일번호
  PATH  VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  EXNO  INTEGER      NOT NULL COMMENT '전시회번호', -- 전시회번호
  FNAME VARCHAR(50)  NOT NULL COMMENT '파일명' -- 파일명
)
COMMENT '전시회첨부파일';

-- 전시회첨부파일
ALTER TABLE jang_exhibition_file
  ADD CONSTRAINT PK_jang_exhibition_file -- 전시회첨부파일 기본키
    PRIMARY KEY (
      EXFNO -- 전시회게시글첨부파일번호
    );

ALTER TABLE jang_exhibition_file
  MODIFY COLUMN EXFNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '전시회게시글첨부파일번호';

-- 좋아요
CREATE TABLE jang_like (
  RECONO INTEGER     NOT NULL COMMENT '코스추천글번호', -- 코스추천글번호
  ID     VARCHAR(50) NOT NULL COMMENT '아이디' -- 아이디
)
COMMENT '좋아요';

-- 좋아요
ALTER TABLE jang_like
  ADD CONSTRAINT PK_jang_like -- 좋아요 기본키
    PRIMARY KEY (
      RECONO, -- 코스추천글번호
      ID      -- 아이디
    );

-- 방문히스토리
CREATE TABLE jang_history (
  HNO   INTEGER     NOT NULL COMMENT '방문히스토리번호', -- 방문히스토리번호
  HDATE DATE        NOT NULL DEFAULT curdate() COMMENT '방문날짜', -- 방문날짜
  ID    VARCHAR(50) NOT NULL COMMENT '아이디' -- 아이디
)
COMMENT '방문히스토리';

-- 방문히스토리
ALTER TABLE jang_history
  ADD CONSTRAINT PK_jang_history -- 방문히스토리 기본키
    PRIMARY KEY (
      HNO -- 방문히스토리번호
    );

ALTER TABLE jang_history
  MODIFY COLUMN HNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '방문히스토리번호';

-- 장소후기
CREATE TABLE jang_so_review (
  PRVNO  INTEGER    NOT NULL COMMENT '장소후기번호', -- 장소후기번호
  CONT   MEDIUMTEXT NOT NULL COMMENT '내용', -- 내용
  RECONO INTEGER    NOT NULL COMMENT '코스추천글번호', -- 코스추천글번호
  PLNO   INTEGER    NOT NULL COMMENT '장소번호' -- 장소번호
)
COMMENT '장소후기';

-- 장소후기
ALTER TABLE jang_so_review
  ADD CONSTRAINT PK_jang_so_review -- 장소후기 기본키
    PRIMARY KEY (
      PRVNO -- 장소후기번호
    );

ALTER TABLE jang_so_review
  MODIFY COLUMN PRVNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '장소후기번호';

-- 회원
CREATE TABLE jang_member (
  ID      VARCHAR(50)  NOT NULL COMMENT '아이디', -- 아이디
  PWD     VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
  NNAME   VARCHAR(50)  NOT NULL COMMENT '닉네임', -- 닉네임
  SIDATE  DATE         NOT NULL DEFAULT curdate() COMMENT '가입일', -- 가입일
  PROFILE VARCHAR(255) NULL     COMMENT '프로필사진', -- 프로필사진
  NAME    VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  EMAIL   VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  PNUM    VARCHAR(30)  NOT NULL COMMENT '휴대폰번호', -- 휴대폰번호
  BIRTH   DATE         NULL     COMMENT '생년월일', -- 생년월일
  GENDER  CHAR(1)      NULL     COMMENT '성별', -- 성별
  SNS     VARCHAR(255) NULL     COMMENT 'SNS주소', -- SNS주소
  MBTI    VARCHAR(4)   NULL     COMMENT 'MBTI', -- MBTI
  STATUS  VARCHAR(10)  NOT NULL DEFAULT 'A' COMMENT '계정상태' -- 계정상태
)
COMMENT '회원';

-- 회원
ALTER TABLE jang_member
  ADD CONSTRAINT PK_jang_member -- 회원 기본키
    PRIMARY KEY (
      ID -- 아이디
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_jang_member
  ON jang_member ( -- 회원
    NNAME ASC -- 닉네임
  );

-- 회원 유니크 인덱스2
CREATE UNIQUE INDEX UIX_jang_member2
  ON jang_member ( -- 회원
    EMAIL ASC -- 이메일
  );

-- 회원 유니크 인덱스3
CREATE UNIQUE INDEX UIX_jang_member3
  ON jang_member ( -- 회원
    PNUM ASC -- 휴대폰번호
  );

-- 회원 인덱스
CREATE INDEX IX_jang_member
  ON jang_member( -- 회원
    NAME ASC -- 이름
  );

-- 회원 인덱스2
CREATE INDEX IX_jang_member2
  ON jang_member( -- 회원
    EMAIL ASC -- 이메일
  );

-- 전시회한줄리뷰
ALTER TABLE jang_exhibition_review
  ADD CONSTRAINT FK_jang_member_TO_jang_exhibition_review -- 회원 -> 전시회한줄리뷰
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 전시회한줄리뷰
ALTER TABLE jang_exhibition_review
  ADD CONSTRAINT FK_jang_exhibition_TO_jang_exhibition_review -- 전시회 -> 전시회한줄리뷰
    FOREIGN KEY (
      EXNO -- 전시회번호
    )
    REFERENCES jang_exhibition ( -- 전시회
      EXNO -- 전시회번호
    );

-- 알림
ALTER TABLE jang_alarm
  ADD CONSTRAINT FK_jang_member_TO_jang_alarm -- 회원 -> 알림
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- QnA
ALTER TABLE jang_qna
  ADD CONSTRAINT FK_jang_member_TO_jang_qna -- 회원 -> QnA
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 팔로워팔로잉
ALTER TABLE jang_follow
  ADD CONSTRAINT FK_jang_member_TO_jang_follow -- 회원 -> 팔로워팔로잉
    FOREIGN KEY (
      ID -- 본인아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 팔로워팔로잉
ALTER TABLE jang_follow
  ADD CONSTRAINT FK_jang_member_TO_jang_follow2 -- 회원 -> 팔로워팔로잉2
    FOREIGN KEY (
      OTHID -- 다른이아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 코스추천글
ALTER TABLE jang_so_recommendation
  ADD CONSTRAINT FK_jang_member_TO_jang_so_recommendation -- 회원 -> 코스추천글
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 코스추천글
ALTER TABLE jang_so_recommendation
  ADD CONSTRAINT FK_jang_transportation_TO_jang_so_recommendation -- 이동수단 -> 코스추천글
    FOREIGN KEY (
      TPNO -- 이동수단번호
    )
    REFERENCES jang_transportation ( -- 이동수단
      TPNO -- 이동수단번호
    );

-- 장소후기첨부파일
ALTER TABLE jang_so_review_file
  ADD CONSTRAINT FK_jang_so_review_TO_jang_so_review_file -- 장소후기 -> 장소후기첨부파일
    FOREIGN KEY (
      PRVNO -- 장소후기번호
    )
    REFERENCES jang_so_review ( -- 장소후기
      PRVNO -- 장소후기번호
    );

-- 댓글
ALTER TABLE jang_comment
  ADD CONSTRAINT FK_jang_so_recommendation_TO_jang_comment -- 코스추천글 -> 댓글
    FOREIGN KEY (
      RECONO -- 코스추천글번호
    )
    REFERENCES jang_so_recommendation ( -- 코스추천글
      RECONO -- 코스추천글번호
    );

-- 댓글
ALTER TABLE jang_comment
  ADD CONSTRAINT FK_jang_member_TO_jang_comment -- 회원 -> 댓글
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 전시회
ALTER TABLE jang_exhibition
  ADD CONSTRAINT FK_jang_so_TO_jang_exhibition -- 장소 -> 전시회
    FOREIGN KEY (
      PLNO -- 장소번호
    )
    REFERENCES jang_so ( -- 장소
      PLNO -- 장소번호
    );

-- 신고
ALTER TABLE jang_report
  ADD CONSTRAINT FK_jang_so_recommendation_TO_jang_report -- 코스추천글 -> 신고
    FOREIGN KEY (
      RECONO -- 코스추천글번호
    )
    REFERENCES jang_so_recommendation ( -- 코스추천글
      RECONO -- 코스추천글번호
    );

-- 신고
ALTER TABLE jang_report
  ADD CONSTRAINT FK_jang_member_TO_jang_report -- 회원 -> 신고
    FOREIGN KEY (
      ID -- (신고자)아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 장바구니
ALTER TABLE jang_baguni
  ADD CONSTRAINT FK_jang_member_TO_jang_baguni -- 회원 -> 장바구니
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 장바구니
ALTER TABLE jang_baguni
  ADD CONSTRAINT FK_jang_exhibition_TO_jang_baguni -- 전시회 -> 장바구니
    FOREIGN KEY (
      EXNO -- 전시회번호
    )
    REFERENCES jang_exhibition ( -- 전시회
      EXNO -- 전시회번호
    );

-- 전시회티켓구매
ALTER TABLE jang_ticket
  ADD CONSTRAINT FK_jang_exhibition_TO_jang_ticket -- 전시회 -> 전시회티켓구매
    FOREIGN KEY (
      EXNO -- 전시회번호
    )
    REFERENCES jang_exhibition ( -- 전시회
      EXNO -- 전시회번호
    );

-- 전시회티켓구매
ALTER TABLE jang_ticket
  ADD CONSTRAINT FK_jang_member_TO_jang_ticket -- 회원 -> 전시회티켓구매
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 전시회첨부파일
ALTER TABLE jang_exhibition_file
  ADD CONSTRAINT FK_jang_exhibition_TO_jang_exhibition_file -- 전시회 -> 전시회첨부파일
    FOREIGN KEY (
      EXNO -- 전시회번호
    )
    REFERENCES jang_exhibition ( -- 전시회
      EXNO -- 전시회번호
    );

-- 좋아요
ALTER TABLE jang_like
  ADD CONSTRAINT FK_jang_so_recommendation_TO_jang_like -- 코스추천글 -> 좋아요
    FOREIGN KEY (
      RECONO -- 코스추천글번호
    )
    REFERENCES jang_so_recommendation ( -- 코스추천글
      RECONO -- 코스추천글번호
    );

-- 좋아요
ALTER TABLE jang_like
  ADD CONSTRAINT FK_jang_member_TO_jang_like -- 회원 -> 좋아요
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 방문히스토리
ALTER TABLE jang_history
  ADD CONSTRAINT FK_jang_member_TO_jang_history -- 회원 -> 방문히스토리
    FOREIGN KEY (
      ID -- 아이디
    )
    REFERENCES jang_member ( -- 회원
      ID -- 아이디
    );

-- 장소후기
ALTER TABLE jang_so_review
  ADD CONSTRAINT FK_jang_so_recommendation_TO_jang_so_review -- 코스추천글 -> 장소후기
    FOREIGN KEY (
      RECONO -- 코스추천글번호
    )
    REFERENCES jang_so_recommendation ( -- 코스추천글
      RECONO -- 코스추천글번호
    );

-- 장소후기
ALTER TABLE jang_so_review
  ADD CONSTRAINT FK_jang_so_TO_jang_so_review -- 장소 -> 장소후기
    FOREIGN KEY (
      PLNO -- 장소번호
    )
    REFERENCES jang_so ( -- 장소
      PLNO -- 장소번호
    );