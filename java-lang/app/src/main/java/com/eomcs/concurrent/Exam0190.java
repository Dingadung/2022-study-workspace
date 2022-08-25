//

package com.eomcs.concurrent;

public class Exam0190 {

  public static void main(String[] args) {
    int count = 1000;

    new Thread(
        ()->{
          for (int i = 0; i < count; i++) {
            System.out.println("==> " + i);
          }
        }).start();

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    }
  }// main()

}
