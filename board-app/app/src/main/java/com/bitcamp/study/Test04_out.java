// FileOutputStream: write(byte[], offset, len) 사용법
// offset은 기준을 말한다.
package com.bitcamp.study;

import java.io.FileOutputStream;

public class Test04_out {

  public static void main(String[] args) throws Exception{ // FIleOutputStream의 사용법을 배우는 것이 목적이지, 오류 처리하는 것이 목적이 아니기 때문이다.
    // TODO Auto-generated method stub
    FileOutputStream out = new FileOutputStream("test4.data");

    byte[ ] bytes = new byte[] {100, 90, 80, 70, 60};

    // 바이트 배열 모두 출력
    out.write(bytes, 2, 2); // 두 번째부터 두개 읽기

    out.close();

    System.out.println("finish!");
  }

}
