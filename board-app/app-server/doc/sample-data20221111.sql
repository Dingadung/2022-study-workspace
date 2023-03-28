-- jang_member
INSERT INTO jangdb.jang_member (ID, PWD, NNAME, SIDATE, PROFILE, NAME, EMAIL, PNUM, BIRTH, GENDER, SNS, MBTI, OUTDATE) VALUES ('user1', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 'juser1', '2022-10-06', null, 'JANG', 'user1@test.com', '01000000000', '1996-03-24', 'F', null, 'ESTJ', NULL);
INSERT INTO jangdb.jang_member (ID, PWD, NNAME, SIDATE, PROFILE, NAME, EMAIL, PNUM, BIRTH, GENDER, SNS, MBTI, OUTDATE) VALUES ('user2', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 'Muser1', '2022-10-06', null, 'MIN', 'user2@test.com', '01100000000', '1996-07-26', 'M', null, 'ISFJ', NULL);
INSERT INTO jangdb.jang_member (ID, PWD, NNAME, SIDATE, PROFILE, NAME, EMAIL, PNUM, BIRTH, GENDER, SNS, MBTI, OUTDATE) VALUES ('user3', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 'user3', '2022-10-10', null, 'user3', 'user3@test.com', '12345678906', '2022-10-10', 'm', null, null, NULL);
INSERT INTO jangdb.jang_member (ID, PWD, NNAME, SIDATE, PROFILE, NAME, EMAIL, PNUM, BIRTH, GENDER, SNS, MBTI, OUTDATE) VALUES ('user4', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 'Kimuser', '2022-09-09', null, 'HKyung', 'Kim@test.com', '01034567890', '1996-03-03', 'M', null, 'ISFJ', NULL);
INSERT INTO jangdb.jang_member (ID, PWD, NNAME, SIDATE, PROFILE, NAME, EMAIL, PNUM, BIRTH, GENDER, SNS, MBTI, OUTDATE) VALUES ('user5', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 'Parkuser', '2022-10-01', null, 'Mingu', 'Park@test.com', '01099999999', '1995-12-10', 'f', null, 'ISFJ', NULL);
INSERT INTO jangdb.jang_member (ID, PWD, NNAME, SIDATE, PROFILE, NAME, EMAIL, PNUM, BIRTH, GENDER, SNS, MBTI, OUTDATE) VALUES ('user6', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 'Uuser', '2022-10-03', null, 'Jimin', 'Uuser@test.com', '01087654321', '1994-11-10', 'm', null, 'ISFJ', NULL);
INSERT INTO jangdb.jang_member (ID, PWD, NNAME, SIDATE, PROFILE, NAME, EMAIL, PNUM, BIRTH, GENDER, SNS, MBTI, OUTDATE) VALUES ('user7', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 'Gooduser', '2022-10-06', null, 'Kaon', 'Gooduser@test.com', '01098765432', '1993-10-04', 'f', null, 'ISFJ', NULL);
INSERT INTO jangdb.jang_member (ID, PWD, NNAME, SIDATE, PROFILE, NAME, EMAIL, PNUM, BIRTH, GENDER, SNS, MBTI, OUTDATE) VALUES ('user8', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 'Baduser', '2022-10-10', null, 'Tonghyun', 'Baduser@test.com', '01009876543', '1992-10-10', 'm', null, null, NULL);

-- jang_alarm
INSERT INTO jangdb.jang_alarm (ALNO, ID, CONT, ALTIME, RDTIME) VALUES (1, 'user1', '알림1', '2022-10-06 19:40:23', null);
INSERT INTO jangdb.jang_alarm (ALNO, ID, CONT, ALTIME, RDTIME) VALUES (2, 'user2', '알림2', '2022-10-06 16:46:05', '2022-10-06 16:46:07');
INSERT INTO jangdb.jang_alarm (ALNO, ID, CONT, ALTIME, RDTIME) VALUES (3, 'user1', '알림3', '2022-10-07 11:35:57', '2022-10-07 11:35:59');
INSERT INTO jangdb.jang_alarm (ALNO, ID, CONT, ALTIME, RDTIME) VALUES (4, 'user3', '알림4', '2022-10-13 18:11:18', null);
INSERT INTO jangdb.jang_alarm (ALNO, ID, CONT, ALTIME, RDTIME) VALUES (5, 'user3', '알림5', '2022-10-13 18:11:39', null);
INSERT INTO jangdb.jang_alarm (ALNO, ID, CONT, ALTIME, RDTIME) VALUES (6, 'user4', '알림6', '2022-10-13 19:17:50', null);

-- jang_search
INSERT INTO jangdb.jang_search (SKNO, KWD, SCDATE) VALUES (1, '키워드1', '2022-10-06');
INSERT INTO jangdb.jang_search (SKNO, KWD, SCDATE) VALUES (2, '키워드2', '2022-10-11');
INSERT INTO jangdb.jang_search (SKNO, KWD, SCDATE) VALUES (3, '키워드2', '2022-10-11');
INSERT INTO jangdb.jang_search (SKNO, KWD, SCDATE) VALUES (4, '키워드1', '2022-10-11');
INSERT INTO jangdb.jang_search (SKNO, KWD, SCDATE) VALUES (5, '키워드1', '2022-10-11');

-- jang_follow
INSERT INTO jangdb.jang_follow (ID, OTHID) VALUES ('user1', 'user2');
INSERT INTO jangdb.jang_follow (ID, OTHID) VALUES ('user1', 'user5');
INSERT INTO jangdb.jang_follow (ID, OTHID) VALUES ('user2', 'user5');
INSERT INTO jangdb.jang_follow (ID, OTHID) VALUES ('user3', 'user4');
INSERT INTO jangdb.jang_follow (ID, OTHID) VALUES ('user4', 'user5');

-- jang_history
INSERT INTO jangdb.jang_history (HNO, HDATE, ID) VALUES (1, '2022-10-13', 'user1');
INSERT INTO jangdb.jang_history (HNO, HDATE, ID) VALUES (2, '2022-10-10', 'user2');
INSERT INTO jangdb.jang_history (HNO, HDATE, ID) VALUES (3, '2022-10-12', 'user3');
INSERT INTO jangdb.jang_history (HNO, HDATE, ID) VALUES (4, '2022-10-12', 'user4');
INSERT INTO jangdb.jang_history (HNO, HDATE, ID) VALUES (5, '2022-10-12', 'user5');

-- jang_so
INSERT INTO jangdb.jang_so (PLNO, PLNAME, QCALL, POSTNO, BASICAD, DETAILAD, WIDO, KDO) VALUES (1, 'BITCAMP', '02-1588-7776', 6134, 'Gangnam-gu, Seoul, Republic of Korea', '20, Gangnam-daero 94-gil', '38.439801', '127.127730');
INSERT INTO jangdb.jang_so (PLNO, PLNAME, QCALL, POSTNO, BASICAD, DETAILAD, WIDO, KDO) VALUES (2, 'CAFE', '02-2233-3333', 6234, 'Gangnam-gu, Seoul, Republic of Korea', '20, Gangnam-daero 15', '39.439801', '128.127730');
INSERT INTO jangdb.jang_so (PLNO, PLNAME, QCALL, POSTNO, BASICAD, DETAILAD, WIDO, KDO) VALUES (3, '전시장소1', '111-222-11', 6234, 'Gangnam-gu, Seoul, Republic of Korea', '20, Gangnam-daero 16', '44.2', '123.222');
INSERT INTO jangdb.jang_so (PLNO, PLNAME, QCALL, POSTNO, BASICAD, DETAILAD, WIDO, KDO) VALUES (4, '전시장소2', '111-222-12', 6234, 'Gangnam-gu, Seoul, Republic of Korea', '20, Gangnam-daero 17', '44.33', '123.22');
INSERT INTO jangdb.jang_so (PLNO, PLNAME, QCALL, POSTNO, BASICAD, DETAILAD, WIDO, KDO) VALUES (5, '전시장소3', '1120220222', 2344, 'Gangnam-gu, Seoul, Republic of Korea', '20, Gangnam-daero 15', '33.22', '123.34');

-- jang_exhibition
INSERT INTO jangdb.jang_exhibition (EXNO, EXNAME, CONT, WDATE, PLNO, STDATE, EDDATE, PRICE) VALUES (1, '전시제목1', '전시내용1', '2022-10-06', 3, '2022-10-07', '2022-10-20', 36000);
INSERT INTO jangdb.jang_exhibition (EXNO, EXNAME, CONT, WDATE, PLNO, STDATE, EDDATE, PRICE) VALUES (2, '전시제목2', '전시내용2', '2022-10-10', 3, '2022-10-10', '2022-10-10', 12000);
INSERT INTO jangdb.jang_exhibition (EXNO, EXNAME, CONT, WDATE, PLNO, STDATE, EDDATE, PRICE) VALUES (3, '전시제목3', '전시내용3', '2022-10-12', 4, '2022-10-12', '2022-10-12', 16000);
INSERT INTO jangdb.jang_exhibition (EXNO, EXNAME, CONT, WDATE, PLNO, STDATE, EDDATE, PRICE) VALUES (4, '전시제목4', '전시내용4', '2022-10-12', 4, '2022-10-12', '2022-10-12', 17000);
INSERT INTO jangdb.jang_exhibition (EXNO, EXNAME, CONT, WDATE, PLNO, STDATE, EDDATE, PRICE) VALUES (5, '전시제목5', '전시내용5', '2022-10-12', 4, '2022-10-12', '2022-10-12', 15000);

-- jang_exhibition_file
INSERT INTO jangdb.jang_exhibition_file (EXFNO, PATH, EXNO, FNAME) VALUES (1, '전시파일경로1', 1, '파일1');
INSERT INTO jangdb.jang_exhibition_file (EXFNO, PATH, EXNO, FNAME) VALUES (2, '전시파일경로2', 2, '파일2');
INSERT INTO jangdb.jang_exhibition_file (EXFNO, PATH, EXNO, FNAME) VALUES (3, '전시파일경로3', 3, '파일3');
INSERT INTO jangdb.jang_exhibition_file (EXFNO, PATH, EXNO, FNAME) VALUES (4, '전시파일경로4', 4, '파일4');
INSERT INTO jangdb.jang_exhibition_file (EXFNO, PATH, EXNO, FNAME) VALUES (5, '전시파일경로5', 5, '파일5');

-- jang_exhibition_review
INSERT INTO jangdb.jang_exhibition_review (RVNO, CONT, WDATE, ID, EXNO) VALUES (1, '감동적인 전시회였습니다.', '2022-10-06', 'user1', 1);
INSERT INTO jangdb.jang_exhibition_review (RVNO, CONT, WDATE, ID, EXNO) VALUES (2, '아름다운 전시회였습니다.', '2022-10-10', 'user2', 2);
INSERT INTO jangdb.jang_exhibition_review (RVNO, CONT, WDATE, ID, EXNO) VALUES (3, '다시 보고 싶은 전시회였습니다.', '2022-10-12', 'user3', 3);
INSERT INTO jangdb.jang_exhibition_review (RVNO, CONT, WDATE, ID, EXNO) VALUES (4, '다채로운 전시회였습니다.', '2022-10-12', 'user4', 4);
INSERT INTO jangdb.jang_exhibition_review (RVNO, CONT, WDATE, ID, EXNO) VALUES (5, '전시관이 넓어서 쾌적하게 관람할 수 있었습니다.', '2022-10-12', 'user5', 5);

-- jang_baguni
INSERT INTO jangdb.jang_baguni (ID, EXNO, CNT, ACT) VALUES ('user1', 1, 3, 1);
INSERT INTO jangdb.jang_baguni (ID, EXNO, CNT, ACT) VALUES ('user2', 2, 2, 1);
INSERT INTO jangdb.jang_baguni (ID, EXNO, CNT, ACT) VALUES ('user1', 2, 1, 1);
INSERT INTO jangdb.jang_baguni (ID, EXNO, CNT, ACT) VALUES ('user3', 1, 2, 1);
INSERT INTO jangdb.jang_baguni (ID, EXNO, CNT, ACT) VALUES ('user3', 2, 1, 1);

-- jang_notice
INSERT INTO jangdb.jang_notice (NTNO, TITLE, CONT, WDATE, VCNT) VALUES (1, '제목1', '내용1', '2022-09-09', 0);
INSERT INTO jangdb.jang_notice (NTNO, TITLE, CONT, WDATE, VCNT) VALUES (2, '제목2', '내용2', '2022-10-01', 0);
INSERT INTO jangdb.jang_notice (NTNO, TITLE, CONT, WDATE, VCNT) VALUES (3, '제목3', '내용3', '2022-10-05', 0);
INSERT INTO jangdb.jang_notice (NTNO, TITLE, CONT, WDATE, VCNT) VALUES (4, '제목4', '내용4', '2022-10-07', 0);
INSERT INTO jangdb.jang_notice (NTNO, TITLE, CONT, WDATE, VCNT) VALUES (5, '제목5', '내용5', '2022-10-10', 0);

-- jang_qna
INSERT INTO jangdb.jang_qna (QNANO, ID, PBL, TITLE, CONT, WDATE, ANSCONT, ANSDATE) VALUES (1, 'user1', 0, '제목1', '내용1', '2022-09-09', '', null);
INSERT INTO jangdb.jang_qna (QNANO, ID, PBL, TITLE, CONT, WDATE, ANSCONT, ANSDATE) VALUES (2, 'user2', 1, '제목2', '내용2', '2022-10-01', '답변2', '2022-10-12');
INSERT INTO jangdb.jang_qna (QNANO, ID, PBL, TITLE, CONT, WDATE, ANSCONT, ANSDATE) VALUES (3, 'user6', 1, '제목3', '내용3', '2022-10-03', '답변3', '2022-10-12');
INSERT INTO jangdb.jang_qna (QNANO, ID, PBL, TITLE, CONT, WDATE, ANSCONT, ANSDATE) VALUES (4, 'user5', 0, '제목4', '내용4', '2022-10-07', '답변4', '2022-10-12');
INSERT INTO jangdb.jang_qna (QNANO, ID, PBL, TITLE, CONT, WDATE, ANSCONT, ANSDATE) VALUES (5, 'user4', 1, '제목5', '내용5', '2022-10-12', '답변5', null);

-- jang_so_recommendation
INSERT INTO jangdb.jang_so_recommendation (RECONO, ID, TITLE, WDATE, CNT, ACT, PET, FRD, CPLE, FMLY, SOLO, TPNAME) VALUES (1, 'user1', 'title', '2022-10-06', 0, 1, null, 1, 1, 1, 1, '도보');
INSERT INTO jangdb.jang_so_recommendation (RECONO, ID, TITLE, WDATE, CNT, ACT, PET, FRD, CPLE, FMLY, SOLO, TPNAME) VALUES (2, 'user2', 'title2', '2022-10-11', 0, 1, null, 1, 1, 1, 1, '열차');
INSERT INTO jangdb.jang_so_recommendation (RECONO, ID, TITLE, WDATE, CNT, ACT, PET, FRD, CPLE, FMLY, SOLO, TPNAME) VALUES (3, 'user2', 'title3', '2022-10-11', 0, 1, null, 1, 1, 1, 1, '비행기');
INSERT INTO jangdb.jang_so_recommendation (RECONO, ID, TITLE, WDATE, CNT, ACT, PET, FRD, CPLE, FMLY, SOLO, TPNAME) VALUES (4, 'user2', 'title4', '2022-10-11', 0, 1, null, 1, 1, 1, 1, '자동차');
INSERT INTO jangdb.jang_so_recommendation (RECONO, ID, TITLE, WDATE, CNT, ACT, PET, FRD, CPLE, FMLY, SOLO, TPNAME) VALUES (5, 'user2', 'title5', '2022-10-11', 0, 1, null, 1, 1, 1, 1, '자전거');

-- jang_so_review
INSERT INTO jangdb.jang_so_review (PRVNO, CONT, RECONO, PLNAME, ADRS) VALUES (1, '강원도 정선 여행 2일차. 병방치 스카이워크에 잠깐 들린 후 오늘의 메인 코스인 정선 레일바이크로 향했다. 3년 전에 한번 왔었는데 미리 예약을 하지 못해 돌아갔던 아쉬움을 안고 다시 찾아서인지 설레임이 가득하다.', 1, '롯데리아1', '강남로1길');
INSERT INTO jangdb.jang_so_review (PRVNO, CONT, RECONO, PLNAME, ADRS) VALUES (2, '화암약수까지 차가 올라갈 수 있어 어찌나 다행이던지... 이 추위에 걸으라 했으면 나는 꼼짝없이 동장군이 되어 굳어 버렸을거다. 심지어 정선 여행 내내 너무 추워 기억의 절반은 날아간 것 같은 기분이다.', 1, '롯데리아2', '강남로2길');
INSERT INTO jangdb.jang_so_review (PRVNO, CONT, RECONO, PLNAME, ADRS) VALUES (3, '강원도 국가지질공원 코스는 화암 동굴! 지질명소 중 꽤 유명한 곳으로 통하고 있으며 카르스트지형으로 석회암의 용식 작용으로 만들어진 곳이다.', 1, '롯데리아3', '강남로3길');
INSERT INTO jangdb.jang_so_review (PRVNO, CONT, RECONO, PLNAME, ADRS) VALUES (4, '정선 레일바이크 출발지는 구절리역으로, 예전 열차 선로 그대로 보존이 되어 있다. 메뚜기 모양의 카페가 있고 그 뒤로는 열차펜션이 위치해 있다. 시설은 좋아보이지 않으나, 새마을호나 무궁화호 등으로 열차여행을 오시는 분들한텐 펜션 이용도 괜찮을 것 같다.', 2, '롯데리아4', '강남로4길');
INSERT INTO jangdb.jang_so_review (PRVNO, CONT, RECONO, PLNAME, ADRS) VALUES (5, '화암약수까지 차가 올라갈 수 있어 어찌나 다행이던지... 이 추위에 걸으라 했으면 나는 꼼짝없이 동장군이 되어 굳어 버렸을거다. 심지어 정선 여행 내내 너무 추워 기억의 절반은 날아간 것 같은 기분이다.', 3, '롯데리아5', '강남로5길');
INSERT INTO jangdb.jang_so_review (PRVNO, CONT, RECONO, PLNAME, ADRS) VALUES (6, '강원도 국가지질공원 코스는 화암 동굴! 지질명소 중 꽤 유명한 곳으로 통하고 있으며 카르스트지형으로 석회암의 용식 작용으로 만들어진 곳이다.', 4, '롯데리아6', '강남로6길');
INSERT INTO jangdb.jang_so_review (PRVNO, CONT, RECONO, PLNAME, ADRS) VALUES (7, '화암 동굴로 향하는 모노레일을 탑승하고 정상으로 올랐다. 참고로 모노레일과 입장권을 예약할 때 할인되는 항목이 있다면 꼼꼼하게 챙기시길! 모이면 생각보다 꽤 많은 금액이 세이브되곤 한다.', 5, '롯데리아7', '강남로7길');

-- jang_so_review_file
INSERT INTO jangdb.jang_so_review_file (RECOFNO, PRVNO, PATH, FNAME) VALUES (1, 1, '../review_file/file1_1', '파일6');
INSERT INTO jangdb.jang_so_review_file (RECOFNO, PRVNO, PATH, FNAME) VALUES (2, 1, '../review_file/file1_2', '파일7');
INSERT INTO jangdb.jang_so_review_file (RECOFNO, PRVNO, PATH, FNAME) VALUES (3, 2, '../review_file/file2_1', '파일8');
INSERT INTO jangdb.jang_so_review_file (RECOFNO, PRVNO, PATH, FNAME) VALUES (4, 2, '../review_file/file2_2', '파일9');
INSERT INTO jangdb.jang_so_review_file (RECOFNO, PRVNO, PATH, FNAME) VALUES (5, 3, '../review_file/file3_1', '파일10');
INSERT INTO jangdb.jang_so_review_file (RECOFNO, PRVNO, PATH, FNAME) VALUES (6, 4, '../review_file/file4_1', '파일11');
INSERT INTO jangdb.jang_so_review_file (RECOFNO, PRVNO, PATH, FNAME) VALUES (7, 5, '../review_file/file5_1', '파일12');
INSERT INTO jangdb.jang_so_review_file (RECOFNO, PRVNO, PATH, FNAME) VALUES (8, 5, '../review_file/file5_2', '파일13');

-- jang_ticket
INSERT INTO jangdb.jang_ticket (EXTKNO, ID, EXNO, CNT, PURDATE, PAYMENT, CCDATE, CRDATE) VALUES (1, 'user1', 1, 2, '2022-10-10', 'CASH', '2022-10-11', '2022-10-08');
INSERT INTO jangdb.jang_ticket (EXTKNO, ID, EXNO, CNT, PURDATE, PAYMENT, CCDATE) VALUES (2, 'user1', 1, 2, '2022-10-11', 'CREDIT_CARD', null);
INSERT INTO jangdb.jang_ticket (EXTKNO, ID, EXNO, CNT, PURDATE, PAYMENT, CCDATE) VALUES (3, 'user2', 2, 1, '2022-10-09', 'CREDIT_CARD', null);
INSERT INTO jangdb.jang_ticket (EXTKNO, ID, EXNO, CNT, PURDATE, PAYMENT, CCDATE) VALUES (4, 'user3', 2, 3, '2022-10-10', 'CREDIT_CARD', null);
INSERT INTO jangdb.jang_ticket (EXTKNO, ID, EXNO, CNT, PURDATE, PAYMENT, CCDATE) VALUES (5, 'user4', 3, 1, '2022-10-11', 'KAKAO_PAY', null);
INSERT INTO jangdb.jang_ticket (EXTKNO, ID, EXNO, CNT, PURDATE, PAYMENT, CCDATE, CRDATE) VALUES (6, 'user5', 4, 2, '2022-10-11', 'CASH', '2022-10-11', '2022-09-11');

-- jang_comment
INSERT INTO jangdb.jang_comment (CMNO, RECONO, ID, CONT, WDATE) VALUES (1, 1, 'user1', '내용1', '2022-10-11');
INSERT INTO jangdb.jang_comment (CMNO, RECONO, ID, CONT, WDATE) VALUES (2, 2, 'user2', '내용2', '2022-10-11');
INSERT INTO jangdb.jang_comment (CMNO, RECONO, ID, CONT, WDATE) VALUES (3, 1, 'user2', '내용3', '2022-10-12');
INSERT INTO jangdb.jang_comment (CMNO, RECONO, ID, CONT, WDATE) VALUES (4, 1, 'user3', '내용4', '2022-10-11');
INSERT INTO jangdb.jang_comment (CMNO, RECONO, ID, CONT, WDATE) VALUES (5, 2, 'user3', '내용5', '2022-10-11');

-- jang_like
INSERT INTO jangdb.jang_like (RECONO, ID) VALUES (1, 'user1');
INSERT INTO jangdb.jang_like (RECONO, ID) VALUES (2, 'user3');
INSERT INTO jangdb.jang_like (RECONO, ID) VALUES (3, 'user2');
INSERT INTO jangdb.jang_like (RECONO, ID) VALUES (4, 'user5');
INSERT INTO jangdb.jang_like (RECONO, ID) VALUES (5, 'user4');

-- jang_report
INSERT INTO jangdb.jang_report (RPNO, RECONO, ID, RSN, RST, RSTDATE) VALUES (1, 1, 'user1', '사유1', '처리내용1', '2022-10-06');
INSERT INTO jangdb.jang_report (RPNO, RECONO, ID, RSN, RST, RSTDATE) VALUES (2, 2, 'user2', '사유2', '처리내용2', '2022-10-11');
INSERT INTO jangdb.jang_report (RPNO, RECONO, ID, RSN, RST, RSTDATE) VALUES (3, 2, 'user1', '사유1', '처리내용11', '2022-10-11');
INSERT INTO jangdb.jang_report (RPNO, RECONO, ID, RSN, RST, RSTDATE) VALUES (4, 3, 'user1', '사유11', '처리내용11', '2022-10-11');
INSERT INTO jangdb.jang_report (RPNO, RECONO, ID, RSN, RST, RSTDATE) VALUES (5, 2, 'user1', '사유1', '처리내용11', '2022-10-11');

-- jang_event
INSERT INTO jangdb.jang_event (TITLE, CONT, STDATE, EDDATE, PBL, EVCODE, ECOUNT, WDATE, VCNT) VALUES ('이벤트제목1', '이벤트내용1', '2022-01-01 00:00:01', '2022-02-01 23:59:59', 1, '선착순', 10, '2021-12-20', 0);
INSERT INTO jangdb.jang_event (TITLE, CONT, STDATE, EDDATE, PBL, EVCODE, ECOUNT, WDATE, VCNT) VALUES ('이벤트제목2', '이벤트내용2', '2022-03-01 00:00:01', '2022-04-01 23:59:59', 0, '차등지급', 20, '2022-02-15', 0);
INSERT INTO jangdb.jang_event (TITLE, CONT, STDATE, EDDATE, PBL, EVCODE, ECOUNT, WDATE, VCNT) VALUES ('이벤트제목3', '이벤트내용3', '2022-05-01 00:00:01', '2022-06-01 23:59:59', 0, '선착순', 30, '2022-04-16', 0);
INSERT INTO jangdb.jang_event (TITLE, CONT, STDATE, EDDATE, PBL, EVCODE, ECOUNT, WDATE, VCNT) VALUES ('이벤트제목4', '이벤트내용4', '2022-07-01 00:00:01', '2022-08-01 23:59:59', 0, '차등지급', 40, '2022-06-01', 0);
INSERT INTO jangdb.jang_event (TITLE, CONT, STDATE, EDDATE, PBL, EVCODE, ECOUNT, WDATE, VCNT) VALUES ('이벤트제목5', '이벤트내용5', '2022-09-01 00:00:01', '2022-10-01 23:59:59', 1, '차등지급', 50, '2022-08-11', 0);

-- jang_join
INSERT INTO jangdb.jang_join (EVTNO, ID, WIN) VALUES (1, 'user1', 1);
INSERT INTO jangdb.jang_join (EVTNO, ID, WIN) VALUES (1, 'user2', 0);
INSERT INTO jangdb.jang_join (EVTNO, ID, WIN) VALUES (2, 'user3', 0);
INSERT INTO jangdb.jang_join (EVTNO, ID, WIN) VALUES (3, 'user3', 0);
INSERT INTO jangdb.jang_join (EVTNO, ID, WIN) VALUES (4, 'user4', 1);

-- jang_item
INSERT INTO jangdb.jang_item (ITMNO, NAME, CNT, EVTNO, RANK) VALUES (1, '상품1', 10, 5, 1);
INSERT INTO jangdb.jang_item (ITMNO, NAME, CNT, EVTNO, RANK) VALUES (2, '상품2', 20, 4, 2);
INSERT INTO jangdb.jang_item (ITMNO, NAME, CNT, EVTNO, RANK) VALUES (3, '상품3', 30, 3, 3);
INSERT INTO jangdb.jang_item (ITMNO, NAME, CNT, EVTNO, RANK) VALUES (4, '상품4', 40, 2, 1);
INSERT INTO jangdb.jang_item (ITMNO, NAME, CNT, EVTNO, RANK) VALUES (5, '상품5', 50, 1, 2);
INSERT INTO jangdb.jang_item (ITMNO, NAME, CNT, EVTNO, RANK) VALUES (6, '상품6', 10, 1, 3);

-- jang_event_file
INSERT INTO jangdb.jang_event_file (ITFNO, PATH, FNAME, ITMNO) VALUES (1, '../event_file/img1.jpg', '상품이미지1', 1);
INSERT INTO jangdb.jang_event_file (ITFNO, PATH, FNAME, ITMNO) VALUES (2, '../event_file/img2.jpg', '상품이미지2', 2);
INSERT INTO jangdb.jang_event_file (ITFNO, PATH, FNAME, ITMNO) VALUES (3, '../event_file/img3.jpg', '상품이미지3', 3);
INSERT INTO jangdb.jang_event_file (ITFNO, PATH, FNAME, ITMNO) VALUES (4, '../event_file/img4.jpg', '상품이미지4', 4);
INSERT INTO jangdb.jang_event_file (ITFNO, PATH, FNAME, ITMNO) VALUES (5, '../event_file/img5.jpg', '상품이미지5', 5);

