//

package com.eomcs.concurrent;

public class Exam0180 {

  public static void main(String[] args) {
    int count = 1000;

    new Thread(
        new Runnable() {
          @Override
          public void run() {
            for (int i = 0; i < count; i++) {
              System.out.println("==> " + i);
            }
          }
        }
        ).start();

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    }

  }// main()

}
