/*
 * 게시글 프린트 처리 클래스
 */
package com.bitcamp.board;

public class Print {
  static void wrongMessage() {
    System.out.println("메뉴 번호가 옳지 않습니다.");
  }

  static void bye() {
    System.out.println("프로그램을 종료합니다!\n안녕히 가세요!");
  }

  // 메서드를 통해 특정 코드의 복잡함을 감출 수 있다. ==> 캡슐화(encapsulation)
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
    System.out.println("  4: 게시글 삭제");
    System.out.println();
  }

  static void displayLine(char c) {
    for (int i = 0; i < 45; i++) {
      System.out.print(c);
    }
    System.out.println();
  }

  static void displayBlankLine() {
    displayLine('-');
    System.out.println();
  }
}
