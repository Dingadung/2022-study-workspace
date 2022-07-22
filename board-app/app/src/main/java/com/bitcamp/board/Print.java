/*
 * 게시글 프린트 처리 클래스
 */
package com.bitcamp.board;

public class Print {
  static void wrongMessage() {
    System.out.println("메뉴 번호가 옳지 않습니다.");
  }

  static void bye() {
    System.out.println("안녕히 가세요!");
  }

  // 메서드를 통해 특정 코드의 복잡함을 감출 수 있다. ==> 캡슐화(encapsulation)
  static void welcome() {
    System.out.println("[게시판 애플리케이션]");
    System.out.println();
    System.out.println("환영합니다!");
    System.out.println();
  }

  //  static void displayMenu() {
  //    System.out.println();
  //    System.out.println("게시판: ");
  //    System.out.println("  1: 목록");
  //    System.out.println("  2: 상세보기");
  //    System.out.println("  3: 등록");
  //    System.out.println("  4: 삭제");
  //    System.out.println("  5: 수정");
  //    System.out.println();
  //  }

  //  static void displayHeadLine(char c) {
  //    for (int i = 0; i < 45; i++) {
  //      System.out.print(c);
  //    }
  //    System.out.println();
  //  }
  //
  //  static void displayBlankLine() {
  //    displayHeadLine('=');
  //    System.out.println();
  //  }
}

