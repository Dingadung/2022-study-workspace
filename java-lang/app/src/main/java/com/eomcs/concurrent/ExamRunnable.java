// 멀티 스레드 적용 전 - 멀티 태스킹을 적용하기 전
package com.eomcs.concurrent;

public class ExamRunnable {

  public static void main(String[] args) {

    int count = 1000;

    // 1단계
    class MyRunnable1 implements Runnable{
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
      }
    }
    MyRunnable1 r = new MyRunnable1();
    Thread t1 = new Thread(r);
    t1.start();

    // 2단계
    class MyRunnable2 implements Runnable{
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
      }
    }
    Thread t2= new Thread(new MyRunnable2());
    t2.start();

    // 3단계 - 스레드 변수는 재사용할 수 없으므로 변수로 만들어줄 필요 없다.
    class MyRunnable3 implements Runnable{
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
      }
    }
    new Thread(new MyRunnable3()).start();

    // 4단계 -Runnable 안의 코드의 길이가 짧은 편이므로 익명 클래스로 만들어준다.
    new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
      }
    }).start();

    // 5단계 - 인터페이스 안에 함수가 한 개인 형태이므로 람다 문법을 사용한다.
    new Thread(
        () -> {
          for (int i = 0; i < count; i++) {
            System.out.println("==> " + i);
          }
        }
        ).start();



    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    }
  }
}

