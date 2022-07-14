/*
 * 게시판 관리 애플리케이션
 * 비트캠프-20220704
 */
package com.bitcamp.board;

public class App {
  //-------------------변수 선언-----------------------\\
  static int boardCount = 0; // 저장된 게시글의 개수
  static final int SIZE = 3;
  static int[] no = new int[SIZE];
  static String[] title = new String[SIZE];
  static String[] content = new String[SIZE];
  static String[] writer = new String[SIZE];
  static String[] password = new String[SIZE];
  static int[] viewCount = new int[SIZE];
  static long[] createdDate = new long[SIZE];
  static java.util.Scanner keyboardInput = new java.util.Scanner(System.in);
  //-------------------변수 선언-----------------------\\

  public static void main(String[] args) {
    welcome();
    
    loop:
      while (true) {
        displayMenu();
        int menuNo = promptMenu();
        displayLine('-');

        switch(menuNo){
          case 0: printBye(); break loop;
          case 1: processBoardList(); break;
          case 2: processBoardDetail(); break;
          case 3: processBoardInput(); break;
          default: printWrongMessage();
        }

        displayBlankLine();
      } //while문 끝 -----------------------------------------------

    keyboardInput.close();
  } //main 끝
  //------------------------------------------------------------
  //------------------------------------------------------------


  //메서드를 통해 특정 코드의 복잡함을 감출 수 있다. ==> 캡슐화(encapsulation)
  public static void welcome() {
    System.out.println("[게시판 애플리케이션]");
    System.out.println();
    System.out.println("환영합니다!");
    System.out.println();
  }

  public static void displayMenu() {
    System.out.println();
    System.out.println("메뉴: ");
    System.out.println("  1: 게시글 목록");
    System.out.println("  2: 게시글 상세보기");
    System.out.println("  3: 게시글 등록");
    System.out.println();
    System.out.print("메뉴를 선택하세요[1..3] (0: 종료) ");
  }

  public static int promptMenu() {
    //-------------------menu 번호 입력 받기
    int menuNo = keyboardInput.nextInt();
    keyboardInput.nextLine();
    return menuNo;
    /*
     * 또 다른 방식
     * String input = keyboardInput.nextLine();
     * return Integer.parseInt(input);
     */
  }

  public static void processBoardList() {
    //-------------------menu 1
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

  public static void processBoardDetail() {
    //-------------------menu 2

    System.out.println("게시판 상세보기");
    System.out.print("몇 번 게시물을 조회하시겠습까? ");
    String noStr = keyboardInput.nextLine();
    int noBoard = Integer.parseInt(noStr);
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

  public static void processBoardInput() {
    //-------------------menu 3

    System.out.println("[게시글 등록]");

    if (SIZE == boardCount) {
      System.out.println(
        "더 이상 게시글을 등록할 수 없습니다. 최대 게시물 등록 수를 초과했습니다!"
      );
      return;
    }
    System.out.print("제목? ");
    title[boardCount] = keyboardInput.nextLine();
    System.out.print("내용? ");
    content[boardCount] = keyboardInput.nextLine();
    System.out.print("작성자? ");
    writer[boardCount] = keyboardInput.nextLine();
    System.out.print("암호? ");
    password[boardCount] = keyboardInput.nextLine();
    no[boardCount] = boardCount == 0 ? 1 : no[boardCount - 1] + 1;
    createdDate[boardCount] = System.currentTimeMillis();
    viewCount[boardCount] = 0;
    boardCount++;
  }

  static void printWrongMessage(){
    System.out.println("메뉴 번호가 옳지 않습니다.");
  }

  static void displayLine(char c){
    for(int i=0;i<45;i++){
      System.out.print(c);
    }
    System.out.println();
  }

  static void displayBlankLine(){
    displayLine('-');
    System.out.println();
  }

  static void printBye(){
    System.out.println("프로그램을 종료합니다!\n안녕히 가세요!");
  }

}
