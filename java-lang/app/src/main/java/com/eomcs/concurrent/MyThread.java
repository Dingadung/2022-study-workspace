package com.eomcs.concurrent;

public class MyThread extends Thread {
  int count;
  public MyThread(int count) {
    // TODO Auto-generated constructor stub
    this.count = count;
  }
  @Override
  public void run() {
    for (int i = 0; i < count; i++) {
      System.out.println("==> " + i);
    }
  }
}
