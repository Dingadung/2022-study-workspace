/*
 * 키보드 입력을 받는 도구를 구비하고 있다.
 */
package com.bitcamp.board;

import java.io.Closeable;

public class Prompt {

  //-------------------변수 선언-----------------------\\
  static java.util.Scanner keyboardInput = new java.util.Scanner(System.in);
  //-------------------변수 선언-----------------------\\

  static int inputInt() {
    /*int value = keyboardInput.nextInt();
    keyboardInput.nextLine();
    return value;*/
    String str = keyboardInput.nextLine();
    return Integer.parseInt(str);
  }

  static String inputString() {
    return keyboardInput.nextLine();
  }


  // 클래스 안에 close가 있으면 이 클래스는 close할 필요가 있음을 알 수 있다.
  static void close() {
    keyboardInput.close();
  }
}
