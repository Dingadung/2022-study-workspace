//FIleInputStream: read() 사용법
package com.bitcamp.study;

import java.io.FileInputStream;

public class Test03_in {

  public static void main(String[] args) throws Exception{
    // TODO Auto-generated method stub
    FileInputStream in = new FileInputStream("test3.data");

    // 파일 데이터를 끝까지 읽기
    // => -1을 리턴할 때까지 읽으면 된다.
    // => read() 메서드는 더 이상 읽을 바이트가 없다면 -1을 리턴한다.
    int b;

    while((b= in.read()) != -1) { // b에 대입되고, 결국 괄호는 b가 된다.
      System.out.printf("%08x\n", b);
      System.out.printf("%d\n\n", b);
    }    in.close();
    System.out.println("finish!");
  }

}
