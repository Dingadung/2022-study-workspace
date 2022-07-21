/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board; // 패키지는 항상 모든 코드들 최상위에 존재해야한다.

import java.util.Date; 

public class BoardHandler {
  //-------------------변수 선언-----------------------\\
  // 모든 게시판이 공유하는 데이터는 클래스 변수에 저장한다.
  // 왜? 클래스 변수는 클래스를 로딩할 때 한 번만 생성되기 때문이다.
  static final int DEFAULT_SIZE = 3;

  // 각 게시판이 별도로 관리해야 할 데이터는 인스턴스 변수에 저장한다.
  // 왜? 인스턴스 변수는, 게시판 별로 생성할 수 있기 때문이다.
  int boardCount = 0; // 저장된 게시글의 개수
  Board[] boards = new Board[DEFAULT_SIZE];
  String title ="";
  //-------------------변수 선언-----------------------\\

  // 클래스 생성자가 정의되어 있지 않으면,
  // 다음과 같이 파라미터가 없는 기본 생성자를 컴파일러가 자동으로 추가한다.
  // 기본생성자: 
  BoardHandler(){
    // 파라미터가 없다.
    // 메서드 몸체는 비어있다.
    // 기본 생성자는 무조건 public, 접근 범위 무조건 공개이다.
  }

  // 제목을 입력 받는 생성자
  BoardHandler(String title) {
    this.title=title;
  }


  void execute() {
    //App 클래스에서 이 메서드를 호출할 때, BoardHandler의 인스턴스를 줄 것이다.
    // 그 주소는 this라는 내장 변수에 보관된다.
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
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
        "yyyy-MM-dd hh:mm:ss"
        );

    System.out.printf("[%s 목록]\n", this.title);
    System.out.println("번호\t제목\t\t조회수\t작성자\t등록일");

    for (int i = 0; i < this.boardCount; i++) {
      Board board = this.boards[i];
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
    Board board = null;
    System.out.printf("[%s 상세보기]\n", this.title);
    int noBoard = Prompt.inputInt("몇 번 게시물을 조회하시겠습까? ");
    for (int i = 0; i <this.boardCount; i++) {
      if (this.boards[i].no == noBoard) {
        board = this.boards[i];
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

  void onInput() {
    //------------------- menu 3 --------------------------
    System.out.printf("[%s 등록]\n", this.title);

    // 배열의 크기를 초과하면, 배열 크기를 50% 증가시킨다.
    if (this.boards.length == this.boardCount) {
      // 새로 만들 배열의 크기를 계산한다.
      //      int newSize = boards.length + boards.length/2;
      int newSize = this.boards.length + (this.boards.length >> 1);

      // 새 배열 준비
      Board[] newArray = new Board[newSize];

      // 기존 배열 값을 새 배열에 넣는다.
      for(int i=0;i<this.boards.length;i++) {
        newArray[i] = this.boards[i];
      }

      // 기존 배열(주소)을 버리고 새 배열(주소)을 사용한다.
      boards = newArray;
    }

    Board board = new Board();
    this.boards[this.boardCount] = board;
    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer= Prompt.inputString("작성자? ");
    board.password = Prompt.inputString("암호? ");

    board.no = this.boardCount == 0 ? 1 : this.boards[this.boardCount - 1].no + 1;

    this.boards[this.boardCount].createdDate = System.currentTimeMillis();

    this.boards[this.boardCount].viewCount = 0;

    // 새로만든 인스턴스 주소를 레퍼런스 배열에 저장한다.

    this.boardCount++;

    System.out.println("게시글을 성공적으로 등록했습니다.");
  }

  void onDelete() {
    System.out.printf("[%s 삭제]\n", this.title);
    if(this.boardCount ==0) {
      System.out.println("현재 존재하는 게시글이 없습니다!");
      return;
    }
    int deleteNo = Prompt.inputInt("삭제하시고 싶은 게시글의 번호를 입력해 주시길 바랍니다.");

    int deleteIdx = -1;
    for(int i=0;i<this.boardCount;i++) {
      if(deleteNo == this.boards[i].no) {
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

  void delete(int deleteIdx) {
    for(int i=deleteIdx+1;i<this.boardCount;i++) {
      this.boards[i-1] = this.boards[i]; //마지막 인덱스 고려하기
    }
    this.boardCount--;
    this.boards[this.boardCount]=null; // 없앤 값은 null 처리하기

    //boards[--boardCount]=null도 가능!
  }

  void onUpdate() {
    System.out.printf("[%s 수정]\n", this.title);
    if(this.boardCount ==0) {
      System.out.println("현재 존재하는 게시글이 없습니다!");
      return;
    }
    int editNo = Prompt.inputInt("변경할 게시글 번호?");

    int editIdx = -1;
    for(int i=0;i<this.boardCount;i++) {
      if(editNo == this.boards[i].no) {
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

  void edit(int editIdx) {
    Board board = this.boards[editIdx];
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
