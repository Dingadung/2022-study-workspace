package com.eomcs.concurrent;

public class MyRunnable implements Runnable {
  int count;
  public MyRunnable(int count) {
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
