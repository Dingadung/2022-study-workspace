/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board.my; // 패키지는 항상 모든 코드들 최상위에 존재해야한다.

import java.text.SimpleDateFormat;
import java.util.Date; 

public class BoardHandler {
  //-------------------변수 선언-----------------------\\//
  // 각 게시판이 별도로 관리해야 할 데이터는 인스턴스 변수에 저장한다.
  // 왜? 인스턴스 변수는, 게시판 별로 생성할 수 있기 때문이다.
  String title;

  // 게시글 목록을 관리할 객체 준비
  BoardList boardList = new BoardList();
  //-------------------변수 선언-----------------------\\


  //-------------------생성자 선언-----------------------\\
  public BoardHandler() {

  }
  //제목을 입력 받는 생성자
  BoardHandler(String title) {
    this.title=title;
  }
  //-------------------생성자 선언-----------------------\\

  void execute() {
    while(true) {
      // 게시판 메뉴 출력
      System.out.println();
      System.out.printf("%s : \n", this.title);
      System.out.println("  1: 목록");
      System.out.println("  2: 상세보기");
      System.out.println("  3: 등록");
      System.out.println("  4: 삭제");
      System.out.println("  5: 수정");
      System.out.println();
      int menuNo = Prompt.inputInt("메뉴를 선택하세요[1..5] (0: 이전) ");
      displayHeadLine('-');

      // 다른 인스턴스 메서드를 호출할 때 this에 보관된 인스턴스 주소를 사용한다.
      switch (menuNo) {
        case 0:
          Print.bye();
          return;
        case 1:
          this.onList();
          break;
        case 2:
          this.onDetail();
          break;
        case 3:
          this.onInput();
          break;
        case 4:
          //메뉴 삭제
          this.onDelete();
          break;
        case 5:
          //게시글 변경
          this.onUpdate();
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


  void onList() {
    //인스턴스 메서드는 호출될 때 넘겨 받은 인스턴스 주소를 this라는 내장 변수에 보관한다.

    //------------------- menu 1 --------------------------
    SimpleDateFormat formatter = new SimpleDateFormat(
        "yyyy-MM-dd hh:mm:ss"
        );

    System.out.printf("[%s 목록]\n", this.title);
    System.out.println("번호\t제목\t\t조회수\t작성자\t등록일");


    // boardList 인스턴스에 들어 있는 데이터 목록을 가져온다.
    Board[] list = this.boardList.toArray();
    for (Board board : list) { // 딱 데이터가 들어 있는 값만 존재
      // 데이터 첨부터 끝까지 반복할때는 위에와 같이 for문선언하기
      Date date = new Date(board.createdDate);
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

  void onDetail() {
    //------------------- menu 2 --------------------------
    System.out.printf("[%s 상세보기]\n", this.title);
    int noBoard = Prompt.inputInt("몇 번 게시물을 조회하시겠습까? ");

    // 해당 번호의 게시글이 몇 번 배열에 들어 있는지 알아내기
    Board board = this.boardList.get(noBoard);

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

    Date date = new Date(board.createdDate);
    System.out.printf("등록일: %1$tY-%1$tm-%1$td/%1$tA %1$tH:%1$tM\n", date);
  }

  void onInput() {
    //------------------- menu 3 --------------------------
    System.out.printf("[%s 등록]\n", this.title);

    Board board = new Board();
    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer= Prompt.inputString("작성자? ");
    board.password = Prompt.inputString("암호? ");
    board.createdDate = System.currentTimeMillis();
    board.viewCount = 0;
    // 새로만든 인스턴스 주소를 레퍼런스 배열에 저장한다.
    this.boardList.add(board);

    System.out.println("게시글을 성공적으로 등록했습니다.");
  }

  void onDelete() {
    //------------------- menu 4 --------------------------
    System.out.printf("[%s 삭제]\n", this.title);
    if(boardList.boardCount ==0) {
      System.out.println("현재 존재하는 게시글이 없습니다!");
      return;
    }
    int deleteNo = Prompt.inputInt("삭제하시고 싶은 게시글의 번호를 입력해 주시길 바랍니다.");
    if(!boardList.remove(deleteNo)) {
      System.out.println("잘못된 번호를 입력하셨습니다. "
          + "해당 게시글 번호는 존재하지 않습니다."
          + "\n올바른 번호를 입력해 주십시오.");
      return;
    }else {
      System.out.println("삭제하였습니다!");
    }
  }

  void onUpdate() {
    //------------------- menu 5 --------------------------
    System.out.printf("[%s 수정]\n", this.title);
    if(boardList.boardCount ==0) {
      System.out.println("현재 존재하는 게시글이 없습니다!");
      return;
    }
    int editNo = Prompt.inputInt("변경할 게시글 번호?");
    Board board = this.boardList.get(editNo);
    if(board == null) {
      System.out.println("잘못된 번호를 입력하셨습니다. 해당 게시글 번호는 존재하지 않습니다.\n올바른 번호를 입력해 주십시오.");
      return;
    }else {
      edit(board);
      this.onList();
      System.out.println(board.title + board.content + board.writer);
    }
  }
  void edit(Board board) {
    String title= Prompt.inputString("제목? " + "("+board.title+")");
    String content= Prompt.inputString("내용? "+ "("+board.content+")");
    if(isEdit()) {
<<<<<<< HEAD:board-app/app/src/main/java/com/bitcamp/board/BoardHandler.java

      makeBoard(board, title, content, writer, password);
      System.out.println("�꽦怨듭쟻�쑝濡� 蹂�寃쎈릺�뿀�뒿�땲�떎!");

=======
      makeBoard(board, title, content);
      System.out.println("성공적으로 변경되었습니다!");
>>>>>>> d63849e30df5b0d4b67c5188546521a49eeca2db:board-app/app/src/main/java/com/bitcamp/board/my/BoardHandler.java
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
<<<<<<< HEAD:board-app/app/src/main/java/com/bitcamp/board/BoardHandler.java

  Board makeBoard(Board board, String title, String content, String writer, String pwd) {
=======
  void makeBoard(Board board, String title, String content) {
>>>>>>> d63849e30df5b0d4b67c5188546521a49eeca2db:board-app/app/src/main/java/com/bitcamp/board/my/BoardHandler.java
    board.title =title;
    board.content =content;
  }
}