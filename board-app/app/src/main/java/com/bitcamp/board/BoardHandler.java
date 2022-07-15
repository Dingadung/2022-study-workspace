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
      Board board = boards[i];
      java.util.Date date = new java.util.Date(board.createdDate);
      String dateStr = formatter.format(date);
      System.out.printf(
          "%d\t%s\t\t%d\t%s\t%s\n",
          board.no,
          board.title,
          board.viewCount,
          board.writer,
          dateStr
          );
    }
  }

  static void processDetail() {
    //------------------- menu 2 --------------------------
    Board board = null;
    System.out.println("[게시판 상세보기]");
    int noBoard = Prompt.inputInt("몇 번 게시물을 조회하시겠습까? ");
    for (int i = 0; i < boardCount; i++) {
      if (boards[i].no == noBoard) {
        board = boards[i];
        break;
      }
    }

    if (board == null) {
      System.out.println("해당 번호의 게시물은 존재하지 않습니다!");
      return; // 메소드를 호출할 곳으로 돌아가고 싶을 때. 
    }

    board.viewCount++;

    System.out.printf("번호: %d\n", board.no);
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("조회수: %d\n", board.viewCount);
    System.out.printf("작성자: %s\n", board.writer);

    java.util.Date date = new java.util.Date(board.createdDate);
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

    Board board = new Board();
    boards[boardCount] = board;
    //    title[boardCount] = Prompt.inputString("제목? ");
    //    content[boardCount] = Prompt.inputString("내용? ");
    //    writer[boardCount] = Prompt.inputString("작성자? ");
    //    password[boardCount] = Prompt.inputString("암호? ");
    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer= Prompt.inputString("작성자? ");
    board.password = Prompt.inputString("암호? ");

    board.no = boardCount == 0 ? 1 : boards[boardCount - 1].no + 1;

    boards[boardCount].createdDate = System.currentTimeMillis();

    boards[boardCount].viewCount = 0;

    // 새로만든 인스턴스 주소를 레퍼런스 배열에 저장한다.

    boardCount++;
  }

  static void processDelete() {
    int deleteNo = Prompt.inputInt("삭제하시고 싶은 게시글의 번호를 입력해 주시길 바랍니다.");
    int check = -1;
    for(int i=0;i<SIZE;i++) {
      if(deleteNo == boards[i].no) {
        System.out.println("삭제되었습니다.");
        check=1;

        delete(deleteNo);
        break;
      }
    }
    if(check == -1) {
      System.out.println("잘못된 번호를 입력하셨습니다. 해당 게시글 번호는 존재하지 않습니다.\n올바른 번호를 입력해 주십시오.");
      return;
    }
  }

  static void delete(int deleteNo) {
    for(int i=deleteNo-1;i<boardCount;i++) {
      boards[i] = boards[i+1];
    }
    boardCount--;
  }

}
