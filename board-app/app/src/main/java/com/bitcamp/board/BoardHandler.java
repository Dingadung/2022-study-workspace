/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board;

public class BoardHandler {
  //-------------------변수 선언-----------------------\\
  static int boardCount = 0; // 저장된 게시글의 개수
  static final int SIZE = 3;
  static Board[] boards = new Board[SIZE];
  //-------------------변수 선언-----------------------\\

  static void processList() {
    //------------------- menu 1 --------------------------
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
        "yyyy-MM-dd hh:mm:ss"
        );

    System.out.println("[게시글 목록]");

    System.out.println("번호\t제목\t\t조회수\t작성자\t등록일");
    for (int i = 0; i < boardCount; i++) {
      java.util.Date date = new java.util.Date(createdDate[i]);
      String dateStr = formatter.format(date);
      System.out.printf(
          "%d\t%s\t\t%d\t%s\t%s\n",
          no[i],
          title[i],
          viewCount[i],
          writer[i],
          dateStr
          );
    }
  }

  static void processDetail() {
    //------------------- menu 2 --------------------------
    System.out.println("[게시판 상세보기]");
    int noBoard = Prompt.inputInt("몇 번 게시물을 조회하시겠습까? ");
    int idx = -1;
    for (int i = 0; i < boardCount; i++) {
      if (no[i] == noBoard) {
        idx = i;
        break;
      }
    }

    if (idx == -1) {
      System.out.println("해당 번호의 게시물은 존재하지 않습니다!");
      return; // 메소드를 호출할 곳으로 돌아가고 싶을 때. 
    }

    viewCount[idx]++;

    System.out.printf("번호: %d\n", no[idx]);
    System.out.printf("제목: %s\n", title[idx]);
    System.out.printf("내용: %s\n", content[idx]);
    System.out.printf("조회수: %d\n", viewCount[idx]);
    System.out.printf("작성자: %s\n", writer[idx]);

    java.util.Date date = new java.util.Date(createdDate[idx]);
    System.out.printf("등록일: %1$tY-%1$tm-%1$td/%1$tA %1$tH:%1$tM\n", date);
  }

  static void processInput() {
    //------------------- menu 3 --------------------------
    System.out.println("[게시글 등록]");

    if (SIZE == boardCount) {
      System.out.println(
          "더 이상 게시글을 등록할 수 없습니다. 최대 게시물 등록 수를 초과했습니다!"
          );
      return;
    }

    boards[boardCount] = new Board();
    //    title[boardCount] = Prompt.inputString("제목? ");
    //    content[boardCount] = Prompt.inputString("내용? ");
    //    writer[boardCount] = Prompt.inputString("작성자? ");
    //    password[boardCount] = Prompt.inputString("암호? ");
    boards[boardCount].title = Prompt.inputString("제목? ");
    boards[boardCount].content = Prompt.inputString("내용? ");
    boards[boardCount].writer= Prompt.inputString("작성자? ");
    boards[boardCount].password = Prompt.inputString("암호? ");

    //no[boardCount] = boardCount == 0 ? 1 : no[boardCount - 1] + 1;

    boards[boardCount].createdDate = System.currentTimeMillis();

    boards[boardCount].viewCount = 0;

    // 새로만든 인스턴스 주소를 레퍼런스 배열에 저장한다.

    boardCount++;
  }

}
