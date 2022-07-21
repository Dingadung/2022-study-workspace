/*
 * 게시판 관리 애플리케이션 비트캠프-20220704
 */
package com.bitcamp.board;

public class App {


  public static void main(String[] args) {
    Print.welcome();
    // 각 메뉴들의 인스턴스 변수는 while 밖에 선언해야 한다.
    // 인스턴스를 생성할 때 생성자가 원하는 값을 반드시 줘야 한다.
    // 주지 않으면 컴파일 오류이다!
    BoardHandler boardHandler = new BoardHandler("게시글");
    BoardHandler readingHandler = new BoardHandler("독서록");
    BoardHandler visitingHandler = new BoardHandler("방명록");
    BoardHandler noticeHandler = new BoardHandler("공지사항");
    BoardHandler diaryHandler = new BoardHandler("일기장");
    loop: while (true) {
      //메인 메뉴 출력
      System.out.println();
      System.out.println("메뉴: ");
      System.out.printf("  1: %s\n", boardHandler.title);
      System.out.printf("  2: %s\n", readingHandler.title);
      System.out.printf("  3: %s\n", visitingHandler.title);
      System.out.printf("  4: %s\n", noticeHandler.title);
      System.out.printf("  5: %s\n", diaryHandler.title);
      int mainMenuNo = Prompt.inputInt("메뉴를 선택하세요[1..4] (0: 종료) ");
      System.out.println();
      switch (mainMenuNo) {
        case 0:
          Print.bye();
          break loop;
        case 1: // 게시판
          boardHandler.execute();
          break; // 메인 메뉴 화면으로 돌아가기
        case 2: // 독서록
          readingHandler.execute();
          break;
        case 3: // 방명록
          visitingHandler.execute();
          break;
        case 4: // 공지사항
          noticeHandler.execute();
          break;
        case 5: // 일기장
          diaryHandler.execute();
          break;
        default:
          Print.wrongMessage();
      }//switch 끝
    } // 메인메뉴 while문 끝 -----------------------------------------------
    Prompt.close();

  } // main 끝 ---------------------------------------
}
