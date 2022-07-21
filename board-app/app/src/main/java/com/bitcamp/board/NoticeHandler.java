/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board;

public class NoticeHandler {
  //-------------------변수 선언-----------------------\\
  static int boardCount = 0;
  static final int SIZE = 3;
  static Board[] boards = new Board[SIZE];
  //-------------------변수 선언-----------------------\\


  static void execute() {
    while(true) {
      // 게시판 메뉴 출력
      System.out.println();
      System.out.println("공지사항: ");
      System.out.println("  1: 목록");
      System.out.println("  2: 상세보기");
      System.out.println("  3: 등록");
      System.out.println("  4: 삭제");
      System.out.println("  5: 수정");
      System.out.println();
      int menuNo = Prompt.inputInt("메뉴를 선택하세요[1..5] (0: 이전) ");
      displayHeadLine('-');
      switch (menuNo) {
        case 0:
          Print.bye();
          return;
        case 1:
          NoticeHandler.processList();
          break;
        case 2:
          NoticeHandler.processDetail();
          break;
        case 3:
          NoticeHandler.processInput();
          break;
        case 4:
          //메뉴 삭제
          NoticeHandler.processDelete();
          break;
        case 5:
          //게시글 변경
          NoticeHandler.processUpdate();
          break;
        default:
          Print.wrongMessage();
      }
      displayBlankLine();
    }//게시판 while
  }


  static void displayHeadLine(char c) {
    for (int i = 0; i < 45; i++) {
      System.out.print(c);
    }
    System.out.println();
  }

  static void displayBlankLine() {
    displayHeadLine('=');
    System.out.println();
  }


  /////////////////////////////////////////////////


  static void processList() {
    //------------------- menu 1 --------------------------
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
        "yyyy-MM-dd hh:mm:ss"
        );

    System.out.println("[공지사항 목록]");

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
    System.out.println("[공지사항 상세보기]");
    int noBoard = Prompt.inputInt("몇 번 공지사항을 조회하시겠습까? ");
    for (int i = 0; i < boardCount; i++) {
      if (boards[i].no == noBoard) {
        board = boards[i];
        break;
      }
    }

    if (board == null) {
      System.out.println("해당 번호의 공지사항은 존재하지 않습니다!");
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
    System.out.println("[공지사항 등록]");

    // 배열의 크기를 초과하면, 배열 크기를 50% 증가시킨다.
    if (boards.length == boardCount) {
      // 새로 만들 배열의 크기를 계산한다.
      //      int newSize = boards.length + boards.length/2;
      int newSize = boards.length + (boards.length >> 1);

      // 새 배열 준비
      Board[] newArray = new Board[newSize];

      // 기존 배열 값을 새 배열에 넣는다.
      for(int i=0;i<boards.length;i++) {
        newArray[i] = boards[i];
      }

      // 기존 배열(주소)을 버리고 새 배열(주소)을 사용한다.
      boards = newArray;
    }

    Board board = new Board();
    boards[boardCount] = board;
    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer= Prompt.inputString("작성자? ");
    board.password = Prompt.inputString("암호? ");

    board.no = boardCount == 0 ? 1 : boards[boardCount - 1].no + 1;

    boards[boardCount].createdDate = System.currentTimeMillis();

    boards[boardCount].viewCount = 0;

    // 새로만든 인스턴스 주소를 레퍼런스 배열에 저장한다.

    boardCount++;

    System.out.println("공지사항을 성공적으로 등록했습니다.");
  }

  static void processDelete() {
    System.out.println("[공지사항 삭제]");
    if(boardCount ==0) {
      System.out.println("현재 존재하는 공지사항이 없습니다!");
      return;
    }
    int deleteNo = Prompt.inputInt("삭제하시고 싶은 공지사항의 번호를 입력해 주시길 바랍니다.");

    int deleteIdx = -1;
    for(int i=0;i<boardCount;i++) {
      if(deleteNo == boards[i].no) {
        System.out.println("삭제되었습니다.");
        deleteIdx=i;
        delete(deleteIdx);
        break;
      }
    }
    if(deleteIdx == -1) {
      System.out.println("잘못된 번호를 입력하셨습니다. 해당 게시글 번호는 존재하지 않습니다.\n올바른 번호를 입력해 주십시오.");
      return;
    }
  }

  static void delete(int deleteIdx) {
    for(int i=deleteIdx+1;i<boardCount;i++) {
      boards[i-1] = boards[i]; //마지막 인덱스 고려하기
    }
    boardCount--;
    boards[boardCount]=null; // 없앤 값은 null 처리하기

    //boards[--boardCount]=null도 가능!
  }

  public static void processUpdate() {
    System.out.println("[공지사항 수정]");
    if(boardCount ==0) {
      System.out.println("현재 존재하는 게시글이 없습니다!");
      return;
    }
    int editNo = Prompt.inputInt("변경할 게시글 번호?");

    int editIdx = -1;
    for(int i=0;i<boardCount;i++) {
      if(editNo == boards[i].no) {
        editIdx=i;
        edit(editIdx);
        break;
      }
    }
    if(editIdx == -1) {
      System.out.println("잘못된 번호를 입력하셨습니다. 해당 게시글 번호는 존재하지 않습니다.\n올바른 번호를 입력해 주십시오.");
      return;
    }
  }

  static void edit(int editIdx) {
    Board board = boards[editIdx];
    String title= Prompt.inputString("제목? " + "("+board.title+")");
    String content= Prompt.inputString("내용? "+ "("+board.content+")");
    String writer = Prompt.inputString("작성자? "+ "("+board.writer+")");
    String password = Prompt.inputString("암호? "+ "("+board.password+")");
    if(isEdit()) {
      board = makeBoard(title, content, writer, password);
      System.out.println("성공적으로 변경되었습니다!");
    }else {
      System.out.println("변경을 취소하였습니다.");
      return;
    }
  }

  static boolean isEdit() {
    char ans = Prompt.inputChar("변경하시겠습니까?(y/n) ");
    if(ans=='y')return true;
    else if(ans=='n') return false;
    return ans =='y'? true:false;
  }

  static Board makeBoard(String title, String content, String writer, String pwd) {
    Board board = new Board();
    board.title =title;
    board.content =content;
    board.writer=writer;
    board.password = pwd;
    return board;
  }

}
