-- 33
DROP TABLE IF EXISTS "member";

-- 후기게시글
DROP TABLE IF EXISTS "app_board";

-- 첨부파일
DROP TABLE IF EXISTS "app_board_file";

-- 회원
DROP TABLE IF EXISTS "TABLE";

-- 장소후기
DROP TABLE IF EXISTS "TABLE2";

-- 전시회게시판
DROP TABLE IF EXISTS "TABLE3";

-- 위치정보
DROP TABLE IF EXISTS "TABLE4";

-- 장소
DROP TABLE IF EXISTS "TABLE5";

-- 개인정보
DROP TABLE IF EXISTS "TABLE6";

-- 결제정보
DROP TABLE IF EXISTS "TABLE7";

-- 전시회티켓
DROP TABLE IF EXISTS "TABLE8";

-- 장소후기첨부파일
DROP TABLE IF EXISTS "TABLE9";

-- 내가 쓴 글
DROP TABLE IF EXISTS "TABLE10";

-- 수집지표데이터
DROP TABLE IF EXISTS "TABLE11";

-- 댓글
DROP TABLE IF EXISTS "TABLE12";

-- 코스정보수집데이터
DROP TABLE IF EXISTS "TABLE13";

-- 코스추천글
DROP TABLE IF EXISTS "TABLE14";

-- 회원
DROP TABLE IF EXISTS "TABLE15";

-- 회원
DROP TABLE IF EXISTS "TABLE16";

-- 로그인 히스토리
DROP TABLE IF EXISTS "TABLE17";

-- 관리자 회원
DROP TABLE IF EXISTS "TABLE18";

-- 장바구니
DROP TABLE IF EXISTS "TABLE19";

-- 상품(전시회)
DROP TABLE IF EXISTS "TABLE20";

-- 관리자회원
DROP TABLE IF EXISTS "TABLE21";

-- 결제
DROP TABLE IF EXISTS "TABLE22";

-- 결제2
DROP TABLE IF EXISTS "TABLE23";

-- 관리자 회원2
DROP TABLE IF EXISTS "TABLE24";

-- 상품(전시회)2
DROP TABLE IF EXISTS "TABLE25";

-- 전시회 게시판
DROP TABLE IF EXISTS "TABLE26";

-- 장바구니
DROP TABLE IF EXISTS "TABLE27";

-- 후기게시판
DROP TABLE IF EXISTS "TABLE28";

-- 결제 상세
DROP TABLE IF EXISTS "TABLE29";

-- 회원2
DROP TABLE IF EXISTS "TABLE30";

-- 공지게시판
DROP TABLE IF EXISTS "TABLE31";

-- 결제확인
DROP TABLE IF EXISTS "TABLE32";

-- 게시판번호
DROP TABLE IF EXISTS "TABLE33";

-- 로그인 히스토리
DROP TABLE IF EXISTS "TABLE34";

-- 판매처
DROP TABLE IF EXISTS "TABLE35";

-- 전시회한줄리뷰
DROP TABLE IF EXISTS "TABLE36";

-- 전시회첨부파일
DROP TABLE IF EXISTS "TABLE37";

-- 팔로우
DROP TABLE IF EXISTS "TABLE38";

-- 검색
DROP TABLE IF EXISTS "TABLE39";

-- 팔로잉
DROP TABLE IF EXISTS "TABLE40";

-- 프로필사진첨부파일
DROP TABLE IF EXISTS "TABLE41";

-- 좋아요
DROP TABLE IF EXISTS "TABLE42";

-- 알림
DROP TABLE IF EXISTS "TABLE43";

-- 공지사항
DROP TABLE IF EXISTS "TABLE44";

-- QnA
DROP TABLE IF EXISTS "TABLE45";

-- QnA답글
DROP TABLE IF EXISTS "TABLE46";

-- 방문자히스토리
DROP TABLE IF EXISTS "TABLE47";

-- 관리자
DROP TABLE IF EXISTS "TABLE48";

-- 관리자계정
DROP TABLE IF EXISTS "TABLE49";

-- 신고
DROP TABLE IF EXISTS "TABLE50";

-- 결제취소
DROP TABLE IF EXISTS "TABLE51";

-- 동행인
DROP TABLE IF EXISTS "TABLE52";

-- 주 이동 수단
DROP TABLE IF EXISTS "TABLE53";

-- 반려동물 동반 여부
DROP TABLE IF EXISTS "TABLE54";

-- 33
CREATE TABLE "member" (
  "mno" <데이터 타입 없음> NOT NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL8" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL7" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL
);

-- 33
ALTER TABLE "member"
  ADD CONSTRAINT "PK_member" -- 33 기본키
  PRIMARY KEY (
  "mno" -- 회원 번호
  );

-- 후기게시글
CREATE TABLE "app_board" (
  "bno" INTEGER NOT NULL,
  "title" VARCHAR(255) NOT NULL,
  "cont" MEDIUMTEXT NULL,
  "mno" INTEGER NULL,
  "pwd" VARCHAR(100) NULL,
  "cdt" DATE NOT NULL,
  "vw_cnt" INTEGER NOT NULL
);

-- 후기게시글
ALTER TABLE "app_board"
  ADD CONSTRAINT "PK_app_board" -- 후기게시글 기본키
  PRIMARY KEY (
  "bno" -- 게시글번호
  );

-- 첨부파일
CREATE TABLE "app_board_file" (
  "bfno" INTEGER NOT NULL,
  "filepath" VARCHAR(255) NOT NULL,
  "bno" INTEGER NOT NULL,
  "bfno2" INTEGER NULL
);

-- 첨부파일
ALTER TABLE "app_board_file"
  ADD CONSTRAINT "PK_app_board_file" -- 첨부파일 기본키
  PRIMARY KEY (
  "bfno" -- 첨부파일번호
  );

-- 회원
CREATE TABLE "TABLE" (
  "COL2" <데이터 타입 없음> NOT NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL7" <데이터 타입 없음> NULL,
  "COL" <데이터 타입 없음> NULL
);

-- 회원
ALTER TABLE "TABLE"
  ADD CONSTRAINT "PK_TABLE" -- 회원 기본키
  PRIMARY KEY (
  "COL2" -- 아이디
  );

-- 장소후기
CREATE TABLE "TABLE2" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 장소후기
ALTER TABLE "TABLE2"
  ADD CONSTRAINT "PK_TABLE2" -- 장소후기 기본키
  PRIMARY KEY (
  "COL" -- 장소후기번호
  );

-- 전시회게시판
CREATE TABLE "TABLE3" (
  "COL9" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL7" <데이터 타입 없음> NULL
);

-- 전시회게시판
ALTER TABLE "TABLE3"
  ADD CONSTRAINT "PK_TABLE3" -- 전시회게시판 기본키
  PRIMARY KEY (
  "COL9" -- 전시회게시글번호
  );

-- 위치정보
CREATE TABLE "TABLE4" (
  "COL3" <데이터 타입 없음> NOT NULL,
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL
);

-- 위치정보
ALTER TABLE "TABLE4"
  ADD CONSTRAINT "PK_TABLE4" -- 위치정보 기본키
  PRIMARY KEY (
  "COL3" -- 장소번호
  );

-- 장소
CREATE TABLE "TABLE5" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL
);

-- 장소
ALTER TABLE "TABLE5"
  ADD CONSTRAINT "PK_TABLE5" -- 장소 기본키
  PRIMARY KEY (
  "COL" -- 장소번호
  );

-- 개인정보
CREATE TABLE "TABLE6" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL,
  "COL7" <데이터 타입 없음> NULL,
  "COL8" <데이터 타입 없음> NULL
);

-- 개인정보
ALTER TABLE "TABLE6"
  ADD CONSTRAINT "PK_TABLE6" -- 개인정보 기본키
  PRIMARY KEY (
  "COL" -- 아이디
  );

-- 결제정보
CREATE TABLE "TABLE7" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL
);

-- 결제정보
ALTER TABLE "TABLE7"
  ADD CONSTRAINT "PK_TABLE7" -- 결제정보 기본키
  PRIMARY KEY (
  "COL" -- 결제번호
  );

-- 전시회티켓
CREATE TABLE "TABLE8" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL7" <데이터 타입 없음> NULL,
  "COL9" <데이터 타입 없음> NULL
);

-- 전시회티켓
ALTER TABLE "TABLE8"
  ADD CONSTRAINT "PK_TABLE8" -- 전시회티켓 기본키
  PRIMARY KEY (
  "COL" -- 상품번호
  );

-- 장소후기첨부파일
CREATE TABLE "TABLE9" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL
);

-- 장소후기첨부파일
ALTER TABLE "TABLE9"
  ADD CONSTRAINT "PK_TABLE9" -- 장소후기첨부파일 기본키
  PRIMARY KEY (
  "COL" -- 장소후기첨부파일번호
  );

-- 내가 쓴 글
CREATE TABLE "TABLE10" (
  "COL2" <데이터 타입 없음> NULL,
  "COL" <데이터 타입 없음> NULL
);

-- 수집지표데이터
CREATE TABLE "TABLE11" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL
);

-- 수집지표데이터
ALTER TABLE "TABLE11"
  ADD CONSTRAINT "PK_TABLE11" -- 수집지표데이터 기본키
  PRIMARY KEY (
  "COL" -- 지표데이터번호
  );

-- 댓글
CREATE TABLE "TABLE12" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 댓글
ALTER TABLE "TABLE12"
  ADD CONSTRAINT "PK_TABLE12" -- 댓글 기본키
  PRIMARY KEY (
  "COL" -- 댓글번호
  );

-- 코스정보수집데이터
CREATE TABLE "TABLE13" (
  "COL4" <데이터 타입 없음> NOT NULL
);

-- 코스정보수집데이터
ALTER TABLE "TABLE13"
  ADD CONSTRAINT "PK_TABLE13" -- 코스정보수집데이터 기본키
  PRIMARY KEY (
  "COL4" -- 코스추천글번호
  );

-- 코스추천글
CREATE TABLE "TABLE14" (
  "COL4" <데이터 타입 없음> NOT NULL,
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL
);

-- 코스추천글
ALTER TABLE "TABLE14"
  ADD CONSTRAINT "PK_TABLE14" -- 코스추천글 기본키
  PRIMARY KEY (
  "COL4" -- 코스추천글번호
  );

-- 회원
CREATE TABLE "TABLE15" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 회원
CREATE TABLE "TABLE16" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL,
  "COL7" <데이터 타입 없음> NULL
);

-- 로그인 히스토리
CREATE TABLE "TABLE17" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL
);

-- 관리자 회원
CREATE TABLE "TABLE18" (
  "COL" <데이터 타입 없음> NULL
);

-- 장바구니
CREATE TABLE "TABLE19" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL
);

-- 상품(전시회)
CREATE TABLE "TABLE20" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL
);

-- 관리자회원
CREATE TABLE "TABLE21" (
  "COL" <데이터 타입 없음> NULL
);

-- 결제
CREATE TABLE "TABLE22" (
);

-- 결제2
CREATE TABLE "TABLE23" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL7" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL
);

-- 관리자 회원2
CREATE TABLE "TABLE24" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 상품(전시회)2
CREATE TABLE "TABLE25" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL
);

-- 전시회 게시판
CREATE TABLE "TABLE26" (
);

-- 장바구니
CREATE TABLE "TABLE27" (
  "COL7" <데이터 타입 없음> NOT NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL" <데이터 타입 없음> NULL
);

-- 장바구니
ALTER TABLE "TABLE27"
  ADD CONSTRAINT "PK_TABLE27" -- 장바구니 기본키
  PRIMARY KEY (
  "COL7" -- 아이디
  );

-- 후기게시판
CREATE TABLE "TABLE28" (
);

-- 결제 상세
CREATE TABLE "TABLE29" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL
);

-- 회원2
CREATE TABLE "TABLE30" (
);

-- 공지게시판
CREATE TABLE "TABLE31" (
  "COL" <데이터 타입 없음> NULL
);

-- 결제확인
CREATE TABLE "TABLE32" (
);

-- 게시판번호
CREATE TABLE "TABLE33" (
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 로그인 히스토리
CREATE TABLE "TABLE34" (
  "COL2" <데이터 타입 없음> NOT NULL,
  "COL6" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL
);

-- 로그인 히스토리
ALTER TABLE "TABLE34"
  ADD CONSTRAINT "PK_TABLE34" -- 로그인 히스토리 기본키
  PRIMARY KEY (
  "COL2" -- 아이디
  );

-- 판매처
CREATE TABLE "TABLE35" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 판매처
ALTER TABLE "TABLE35"
  ADD CONSTRAINT "PK_TABLE35" -- 판매처 기본키
  PRIMARY KEY (
  "COL" -- 사업자번호
  );

-- 전시회한줄리뷰
CREATE TABLE "TABLE36" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL
);

-- 전시회한줄리뷰
ALTER TABLE "TABLE36"
  ADD CONSTRAINT "PK_TABLE36" -- 전시회한줄리뷰 기본키
  PRIMARY KEY (
  "COL" -- 리뷰번호
  );

-- 전시회첨부파일
CREATE TABLE "TABLE37" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL9" <데이터 타입 없음> NULL
);

-- 전시회첨부파일
ALTER TABLE "TABLE37"
  ADD CONSTRAINT "PK_TABLE37" -- 전시회첨부파일 기본키
  PRIMARY KEY (
  "COL" -- 전시회게시글첨부파일번호
  );

-- 팔로우
CREATE TABLE "TABLE38" (
  "COL3" <데이터 타입 없음> NOT NULL,
  "COL" <데이터 타입 없음> NULL
);

-- 팔로우
ALTER TABLE "TABLE38"
  ADD CONSTRAINT "PK_TABLE38" -- 팔로우 기본키
  PRIMARY KEY (
  "COL3" -- 본인아이디
  );

-- 검색
CREATE TABLE "TABLE39" (
  "COL" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 팔로잉
CREATE TABLE "TABLE40" (
  "COL2" <데이터 타입 없음> NOT NULL,
  "COL" <데이터 타입 없음> NULL
);

-- 팔로잉
ALTER TABLE "TABLE40"
  ADD CONSTRAINT "PK_TABLE40" -- 팔로잉 기본키
  PRIMARY KEY (
  "COL2" -- 본인아이디
  );

-- 프로필사진첨부파일
CREATE TABLE "TABLE41" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL
);

-- 프로필사진첨부파일
ALTER TABLE "TABLE41"
  ADD CONSTRAINT "PK_TABLE41" -- 프로필사진첨부파일 기본키
  PRIMARY KEY (
  "COL" -- 프로필사진첨부파일번호
  );

-- 좋아요
CREATE TABLE "TABLE42" (
  "COL4" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 좋아요
ALTER TABLE "TABLE42"
  ADD CONSTRAINT "PK_TABLE42" -- 좋아요 기본키
  PRIMARY KEY (
  "COL4" -- 코스추천글번호
  );

-- 알림
CREATE TABLE "TABLE43" (
  "COL2" <데이터 타입 없음> NOT NULL,
  "COL" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL
);

-- 알림
ALTER TABLE "TABLE43"
  ADD CONSTRAINT "PK_TABLE43" -- 알림 기본키
  PRIMARY KEY (
  "COL2" -- 아이디
  );

-- 공지사항
CREATE TABLE "TABLE44" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL
);

-- 공지사항
ALTER TABLE "TABLE44"
  ADD CONSTRAINT "PK_TABLE44" -- 공지사항 기본키
  PRIMARY KEY (
  "COL" -- 공지사항번호
  );

-- QnA
CREATE TABLE "TABLE45" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL,
  "COL6" <데이터 타입 없음> NULL
);

-- QnA
ALTER TABLE "TABLE45"
  ADD CONSTRAINT "PK_TABLE45" -- QnA 기본키
  PRIMARY KEY (
  "COL" -- QnA번호
  );

-- QnA답글
CREATE TABLE "TABLE46" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL4" <데이터 타입 없음> NULL
);

-- QnA답글
ALTER TABLE "TABLE46"
  ADD CONSTRAINT "PK_TABLE46" -- QnA답글 기본키
  PRIMARY KEY (
  "COL" -- QnA번호
  );

-- 방문자히스토리
CREATE TABLE "TABLE47" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 방문자히스토리
ALTER TABLE "TABLE47"
  ADD CONSTRAINT "PK_TABLE47" -- 방문자히스토리 기본키
  PRIMARY KEY (
  "COL" -- IP
  );

-- 관리자
CREATE TABLE "TABLE48" (
  "COL2" <데이터 타입 없음> NULL
);

-- 관리자계정
CREATE TABLE "TABLE49" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL2" <데이터 타입 없음> NULL
);

-- 관리자계정
ALTER TABLE "TABLE49"
  ADD CONSTRAINT "PK_TABLE49" -- 관리자계정 기본키
  PRIMARY KEY (
  "COL" -- 관리자계정아이디
  );

-- 신고
CREATE TABLE "TABLE50" (
  "COL" <데이터 타입 없음> NOT NULL,
  "COL4" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL
);

-- 신고
ALTER TABLE "TABLE50"
  ADD CONSTRAINT "PK_TABLE50" -- 신고 기본키
  PRIMARY KEY (
  "COL" -- 신고번호
  );

-- 결제취소
CREATE TABLE "TABLE51" (
  "COL2" <데이터 타입 없음> NOT NULL,
  "COL" <데이터 타입 없음> NULL
);

-- 결제취소
ALTER TABLE "TABLE51"
  ADD CONSTRAINT "PK_TABLE51" -- 결제취소 기본키
  PRIMARY KEY (
  "COL2" -- 결제번호
  );

-- 동행인
CREATE TABLE "TABLE52" (
  "COL4" <데이터 타입 없음> NOT NULL,
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL
);

-- 동행인
ALTER TABLE "TABLE52"
  ADD CONSTRAINT "PK_TABLE52" -- 동행인 기본키
  PRIMARY KEY (
  "COL4" -- 코스추천글번호
  );

-- 주 이동 수단
CREATE TABLE "TABLE53" (
  "COL4" <데이터 타입 없음> NOT NULL,
  "COL" <데이터 타입 없음> NULL,
  "COL2" <데이터 타입 없음> NULL,
  "COL3" <데이터 타입 없음> NULL,
  "COL5" <데이터 타입 없음> NULL
);

-- 주 이동 수단
ALTER TABLE "TABLE53"
  ADD CONSTRAINT "PK_TABLE53" -- 주 이동 수단 기본키
  PRIMARY KEY (
  "COL4" -- 코스추천글번호
  );

-- 반려동물 동반 여부
CREATE TABLE "TABLE54" (
  "COL4" <데이터 타입 없음> NOT NULL,
  "COL" <데이터 타입 없음> NULL
);

-- 반려동물 동반 여부
ALTER TABLE "TABLE54"
  ADD CONSTRAINT "PK_TABLE54" -- 반려동물 동반 여부 기본키
  PRIMARY KEY (
  "COL4" -- 코스추천글번호
  );

-- 장소후기
ALTER TABLE "TABLE2"
  ADD CONSTRAINT "FK_TABLE14_TO_TABLE2" -- 코스추천글 -> 장소후기
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE14" ( -- 코스추천글
  "COL4" -- 코스추천글번호
  );

-- 장소후기
ALTER TABLE "TABLE2"
  ADD CONSTRAINT "FK_TABLE5_TO_TABLE2" -- 장소 -> 장소후기
  FOREIGN KEY (
  "COL2" -- 장소번호
  )
  REFERENCES "TABLE5" ( -- 장소
  "COL" -- 장소번호
  );

-- 전시회게시판
ALTER TABLE "TABLE3"
  ADD CONSTRAINT "FK_TABLE5_TO_TABLE3" -- 장소 -> 전시회게시판
  FOREIGN KEY (
  "COL6" -- 장소번호
  )
  REFERENCES "TABLE5" ( -- 장소
  "COL" -- 장소번호
  );

-- 위치정보
ALTER TABLE "TABLE4"
  ADD CONSTRAINT "FK_TABLE5_TO_TABLE4" -- 장소 -> 위치정보
  FOREIGN KEY (
  "COL3" -- 장소번호
  )
  REFERENCES "TABLE5" ( -- 장소
  "COL" -- 장소번호
  );

-- 개인정보
ALTER TABLE "TABLE6"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE6" -- 회원 -> 개인정보
  FOREIGN KEY (
  "COL" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 결제정보
ALTER TABLE "TABLE7"
  ADD CONSTRAINT "FK_TABLE8_TO_TABLE7" -- 전시회티켓 -> 결제정보
  FOREIGN KEY (
  "COL2" -- 상품번호
  )
  REFERENCES "TABLE8" ( -- 전시회티켓
  "COL" -- 상품번호
  );

-- 결제정보
ALTER TABLE "TABLE7"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE7" -- 회원 -> 결제정보
  FOREIGN KEY (
  "COL4" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 전시회티켓
ALTER TABLE "TABLE8"
  ADD CONSTRAINT "FK_TABLE3_TO_TABLE8" -- 전시회게시판 -> 전시회티켓
  FOREIGN KEY (
  "COL9" -- 전시회게시글번호
  )
  REFERENCES "TABLE3" ( -- 전시회게시판
  "COL9" -- 전시회게시글번호
  );

-- 장소후기첨부파일
ALTER TABLE "TABLE9"
  ADD CONSTRAINT "FK_TABLE2_TO_TABLE9" -- 장소후기 -> 장소후기첨부파일
  FOREIGN KEY (
  "COL3" -- 장소후기번호
  )
  REFERENCES "TABLE2" ( -- 장소후기
  "COL" -- 장소후기번호
  );

-- 내가 쓴 글
ALTER TABLE "TABLE10"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE10" -- 회원 -> 내가 쓴 글
  FOREIGN KEY (
  "COL2" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 내가 쓴 글
ALTER TABLE "TABLE10"
  ADD CONSTRAINT "FK_TABLE2_TO_TABLE10" -- 장소후기 -> 내가 쓴 글
  FOREIGN KEY (
  "COL" -- 장소후기번호
  )
  REFERENCES "TABLE2" ( -- 장소후기
  "COL" -- 장소후기번호
  );

-- 댓글
ALTER TABLE "TABLE12"
  ADD CONSTRAINT "FK_TABLE14_TO_TABLE12" -- 코스추천글 -> 댓글
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE14" ( -- 코스추천글
  "COL4" -- 코스추천글번호
  );

-- 댓글
ALTER TABLE "TABLE12"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE12" -- 회원 -> 댓글
  FOREIGN KEY (
  "COL2" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 코스정보수집데이터
ALTER TABLE "TABLE13"
  ADD CONSTRAINT "FK_TABLE14_TO_TABLE13" -- 코스추천글 -> 코스정보수집데이터
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE14" ( -- 코스추천글
  "COL4" -- 코스추천글번호
  );

-- 코스추천글
ALTER TABLE "TABLE14"
  ADD CONSTRAINT "FK_TABLE11_TO_TABLE14" -- 수집지표데이터 -> 코스추천글
  FOREIGN KEY (
  "COL3" -- 지표데이터번호
  )
  REFERENCES "TABLE11" ( -- 수집지표데이터
  "COL" -- 지표데이터번호
  );

-- 코스추천글
ALTER TABLE "TABLE14"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE14" -- 회원 -> 코스추천글
  FOREIGN KEY (
  "COL5" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 장바구니
ALTER TABLE "TABLE27"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE27" -- 회원 -> 장바구니
  FOREIGN KEY (
  "COL7" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 장바구니
ALTER TABLE "TABLE27"
  ADD CONSTRAINT "FK_TABLE8_TO_TABLE27" -- 전시회티켓 -> 장바구니
  FOREIGN KEY (
  "COL" -- 상품번호
  )
  REFERENCES "TABLE8" ( -- 전시회티켓
  "COL" -- 상품번호
  );

-- 로그인 히스토리
ALTER TABLE "TABLE34"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE34" -- 회원 -> 로그인 히스토리
  FOREIGN KEY (
  "COL2" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 로그인 히스토리
ALTER TABLE "TABLE34"
  ADD CONSTRAINT "FK_TABLE47_TO_TABLE34" -- 방문자히스토리 -> 로그인 히스토리
  FOREIGN KEY (
  "COL4" -- IP
  )
  REFERENCES "TABLE47" ( -- 방문자히스토리
  "COL" -- IP
  );

-- 전시회한줄리뷰
ALTER TABLE "TABLE36"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE36" -- 회원 -> 전시회한줄리뷰
  FOREIGN KEY (
  "COL4" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 전시회한줄리뷰
ALTER TABLE "TABLE36"
  ADD CONSTRAINT "FK_TABLE3_TO_TABLE36" -- 전시회게시판 -> 전시회한줄리뷰
  FOREIGN KEY (
  "COL6" -- 전시회게시글번호
  )
  REFERENCES "TABLE3" ( -- 전시회게시판
  "COL9" -- 전시회게시글번호
  );

-- 전시회첨부파일
ALTER TABLE "TABLE37"
  ADD CONSTRAINT "FK_TABLE3_TO_TABLE37" -- 전시회게시판 -> 전시회첨부파일
  FOREIGN KEY (
  "COL9" -- 전시회게시글번호
  )
  REFERENCES "TABLE3" ( -- 전시회게시판
  "COL9" -- 전시회게시글번호
  );

-- 팔로우
ALTER TABLE "TABLE38"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE38" -- 회원 -> 팔로우
  FOREIGN KEY (
  "COL3" -- 본인아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 팔로잉
ALTER TABLE "TABLE40"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE40" -- 회원 -> 팔로잉
  FOREIGN KEY (
  "COL2" -- 본인아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 프로필사진첨부파일
ALTER TABLE "TABLE41"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE41" -- 회원 -> 프로필사진첨부파일
  FOREIGN KEY (
  "COL3" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 좋아요
ALTER TABLE "TABLE42"
  ADD CONSTRAINT "FK_TABLE14_TO_TABLE42" -- 코스추천글 -> 좋아요
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE14" ( -- 코스추천글
  "COL4" -- 코스추천글번호
  );

-- 좋아요
ALTER TABLE "TABLE42"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE42" -- 회원 -> 좋아요
  FOREIGN KEY (
  "COL2" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 알림
ALTER TABLE "TABLE43"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE43" -- 회원 -> 알림
  FOREIGN KEY (
  "COL2" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- QnA
ALTER TABLE "TABLE45"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE45" -- 회원 -> QnA
  FOREIGN KEY (
  "COL2" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- QnA답글
ALTER TABLE "TABLE46"
  ADD CONSTRAINT "FK_TABLE45_TO_TABLE46" -- QnA -> QnA답글
  FOREIGN KEY (
  "COL" -- QnA번호
  )
  REFERENCES "TABLE45" ( -- QnA
  "COL" -- QnA번호
  );

-- 관리자
ALTER TABLE "TABLE48"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE48" -- 회원 -> 관리자
  FOREIGN KEY (
  "COL2" -- 아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 신고
ALTER TABLE "TABLE50"
  ADD CONSTRAINT "FK_TABLE14_TO_TABLE50" -- 코스추천글 -> 신고
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE14" ( -- 코스추천글
  "COL4" -- 코스추천글번호
  );

-- 신고
ALTER TABLE "TABLE50"
  ADD CONSTRAINT "FK_TABLE_TO_TABLE50" -- 회원 -> 신고
  FOREIGN KEY (
  "COL2" -- (신고자)아이디
  )
  REFERENCES "TABLE" ( -- 회원
  "COL2" -- 아이디
  );

-- 결제취소
ALTER TABLE "TABLE51"
  ADD CONSTRAINT "FK_TABLE7_TO_TABLE51" -- 결제정보 -> 결제취소
  FOREIGN KEY (
  "COL2" -- 결제번호
  )
  REFERENCES "TABLE7" ( -- 결제정보
  "COL" -- 결제번호
  );

-- 동행인
ALTER TABLE "TABLE52"
  ADD CONSTRAINT "FK_TABLE13_TO_TABLE52" -- 코스정보수집데이터 -> 동행인
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE13" ( -- 코스정보수집데이터
  "COL4" -- 코스추천글번호
  );

-- 동행인
ALTER TABLE "TABLE52"
  ADD CONSTRAINT "FK_TABLE14_TO_TABLE52" -- 코스추천글 -> 동행인
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE14" ( -- 코스추천글
  "COL4" -- 코스추천글번호
  );

-- 주 이동 수단
ALTER TABLE "TABLE53"
  ADD CONSTRAINT "FK_TABLE13_TO_TABLE53" -- 코스정보수집데이터 -> 주 이동 수단
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE13" ( -- 코스정보수집데이터
  "COL4" -- 코스추천글번호
  );

-- 주 이동 수단
ALTER TABLE "TABLE53"
  ADD CONSTRAINT "FK_TABLE14_TO_TABLE53" -- 코스추천글 -> 주 이동 수단
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE14" ( -- 코스추천글
  "COL4" -- 코스추천글번호
  );

-- 반려동물 동반 여부
ALTER TABLE "TABLE54"
  ADD CONSTRAINT "FK_TABLE13_TO_TABLE54" -- 코스정보수집데이터 -> 반려동물 동반 여부
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE13" ( -- 코스정보수집데이터
  "COL4" -- 코스추천글번호
  );

-- 반려동물 동반 여부
ALTER TABLE "TABLE54"
  ADD CONSTRAINT "FK_TABLE14_TO_TABLE54" -- 코스추천글 -> 반려동물 동반 여부
  FOREIGN KEY (
  "COL4" -- 코스추천글번호
  )
  REFERENCES "TABLE14" ( -- 코스추천글
  "COL4" -- 코스추천글번호
  );