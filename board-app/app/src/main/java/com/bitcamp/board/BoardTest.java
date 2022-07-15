package com.bitcamp.board;

public class BoardTest {
  public static void main(String[] args) {
    // b1, b2, b3 ==> reference variance
    Board b1;
    Board b2;

    b1 = new Board(); 
    // new 명령어로 준비하는 변수는 인스턴스 변수이다.
    b1.no = 1;

    b2 = b1;
    b2.no = 100;

    System.out.println(b1.no);

    b1 = new Board();

    b1.no = 500;
    b2 = b1;
    System.out.println(b1.no + b2.no);
  }
}
