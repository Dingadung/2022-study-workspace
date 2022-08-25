// 멀티 스레드 적용 전 - 멀티 태스킹을 적용하기 전
package com.eomcs.concurrent;

public class ExamThread {

  public static void main(String[] args) {
    int count = 1000;

    // 1단계
    class MyThread1 extends Thread{
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
        super.run();
      }
    }

    MyThread1 thread = new MyThread1();
    thread.start();

    // 2단계
    class MyThread2 extends Thread{
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
        super.run();
      }
    }
    new MyThread2().start();

    // 3단계
    new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
        super.run();
      }
    }.start();

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    }
  }
}


