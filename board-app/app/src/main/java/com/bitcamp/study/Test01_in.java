//FIleInputStream: read() 사용법
package com.bitcamp.study;

import java.io.FileInputStream;

public class Test01_in {

  public static void main(String[] args) throws Exception{
    // TODO Auto-generated method stub
    FileInputStream in = new FileInputStream("test.data");

    // 1바이트 읽기
    int b = in.read(); // 얘가 int 값 받는다고 해서 read가 4바이트를 읽는 것은 아니다!
    System.out.printf("%08x\n", b);

    in.close();
    System.out.println("finish!");
  }

}
