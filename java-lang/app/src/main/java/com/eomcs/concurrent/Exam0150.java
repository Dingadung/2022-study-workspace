//  static nested 클래스를 로컬 클래스로 만든다.  

package com.eomcs.concurrent;

public class Exam0150 {

  public static void main(String[] args) {
    class MyThread extends Thread {
      int count;
      public MyThread(int count) {
        this.count = count;
      }
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
      }
    }// MyThread

    int count = 1000;

    new MyThread(count).start(); 

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    }

  }// main()
}
