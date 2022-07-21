/*
 * 게시판 관리 애플리케이션 비트캠프-20220704
 */
package com.bitcamp.board;

public class App {


  public static void main(String[] args) {
    Print.welcome();
    BoardHandler boardHandler = new BoardHandler();
    loop: while (true) {
      //메인 메뉴 출력
      System.out.println();
      System.out.println("메뉴: ");
      System.out.println("  1: 게시판");
      System.out.println("  2: 독서록");
      System.out.println("  3: 방명록");
      System.out.println("  4: 공지사항");
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
          break;
        case 3: // 방명록
          break;
        case 4: // 공지사항
          break;
        default:
          Print.wrongMessage();
      }//switch 끝
    } // 메인메뉴 while문 끝 -----------------------------------------------
    Prompt.close();

  } // main 끝 ---------------------------------------
}
