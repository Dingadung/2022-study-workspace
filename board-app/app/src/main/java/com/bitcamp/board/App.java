/*
 * 게시판 관리 애플리케이션
 * 비트캠프-20220704
 */
package com.bitcamp.board;

public class App {
  //-------------------변수 선언-----------------------\\
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
          case 0: Print.bye(); break loop;
          case 1: BoardHandler.processList(); break;
          case 2: BoardHandler.processDetail(); break;
          case 3: BoardHandler.processInput(); break;
          default: Print.wrongMessage();
        }

        displayBlankLine();
      } //while문 끝 -----------------------------------------------

    keyboardInput.close();
  } //main 끝 -------------------------------------



  //메서드를 통해 특정 코드의 복잡함을 감출 수 있다. ==> 캡슐화(encapsulation)
  static void welcome() {
    System.out.println("[게시판 애플리케이션]");
    System.out.println();
    System.out.println("환영합니다!");
    System.out.println();
  }

  static void displayMenu() {
    System.out.println();
    System.out.println("메뉴: ");
    System.out.println("  1: 게시글 목록");
    System.out.println("  2: 게시글 상세보기");
    System.out.println("  3: 게시글 등록");
    System.out.println();
    System.out.print("메뉴를 선택하세요[1..3] (0: 종료) ");
  }

  static int promptMenu() {
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
}
