// FileOutputStream: boolean 값 쓰기
package com.bitcamp.study;

import java.io.FileOutputStream;

public class Test13_out {

  public static void main(String[] args) throws Exception{ // FIleOutputStream의 사용법을 배우는 것이 목적이지, 오류 처리하는 것이 목적이 아니기 때문이다.
    // TODO Auto-generated method stub
    FileOutputStream out = new FileOutputStream("test7.data");

    boolean b1 = true;
    boolean b2 = false;

    // f 출력
    out.write(b1? 1: 0);
    out.write(b2? 1: 0);

    out.close();

    System.out.println("finish!");
  }

}
