//FIleInputStream: read() 사용법
package com.bitcamp.study;

import java.io.FileInputStream;

public class Test02_in {

  public static void main(String[] args) throws Exception{
    // TODO Auto-generated method stub
    FileInputStream in = new FileInputStream("test2.data");

    // 4바이트 읽기
    int result = 0;
    int b = in.read(); // 얘가 int 값 받는다고 해서 read가 4바이트를 읽는 것은 아니다!
    result += b << 24;
    System.out.printf("%08x\n", b);

    b = in.read(); // 얘가 int 값 받는다고 해서 read가 4바이트를 읽는 것은 아니다!
    result += b << 16;

    System.out.printf("%08x\n", b);

    b = in.read(); // 얘가 int 값 받는다고 해서 read가 4바이트를 읽는 것은 아니다!
    result += b << 8;

    System.out.printf("%08x\n", b);

    b = in.read(); // 얘가 int 값 받는다고 해서 read가 4바이트를 읽는 것은 아니다!
    result += b;
    System.out.printf("%08x\n", b);
    System.out.println(result);
    in.close();
    System.out.println("finish!");
  }

}
