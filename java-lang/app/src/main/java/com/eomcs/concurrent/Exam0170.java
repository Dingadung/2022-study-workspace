//  로컬클래스를 익명 클래스로 정의해도 된다.

package com.eomcs.concurrent;

public class Exam0170 {

  public static void main(String[] args) {
    int count = 1000;



    new Thread () {
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
      }
    }.start(); 

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    }

  }// main()

}
