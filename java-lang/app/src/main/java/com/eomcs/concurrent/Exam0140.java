// 패키지 멤버를 static nested 클래스로 만든다.  

package com.eomcs.concurrent;

public class Exam0140 {

  public static void main(String[] args) {

    int count = 1000;

    new MyThread(count).start(); 

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    }

  }

  static public class MyThread extends Thread {
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
  }
}

// 자바는 main() 메서드를 실행하는 한 개의 "실행 흐름"이 있다.
// 실행 흐름에 따라 순서대로 코드가 실행된다.


