/*
 * 게시판 관리 애플리케이션 비트캠프-20220704
 */
package com.bitcamp.board;

public class App {


  public static void main(String[] args) {
    Print.welcome();

    loop: while (true) {
      Print.displayMenu();
      int menuNo = Prompt.inputInt("메뉴를 선택하세요[1..5] (0: 종료) ");
      Print.displayLine('-');

      switch (menuNo) {
        case 0:
          Print.bye();
          break loop;
        case 1:
          BoardHandler.processList();
          break;
        case 2:
          BoardHandler.processDetail();
          break;
        case 3:
          BoardHandler.processInput();
          break;
        case 4:
          //메뉴 삭제
          BoardHandler.processDelete();
          break;
        case 5:
          //게시글 변경
          BoardHandler.processUpdate();
          break;
        default:
          Print.wrongMessage();
      }

      Print.displayBlankLine();
    } // while문 끝 -----------------------------------------------

    Prompt.close();
  } // main 끝 ---------------------------------------


}
