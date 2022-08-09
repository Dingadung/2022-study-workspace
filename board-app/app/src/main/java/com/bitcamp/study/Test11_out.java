// FileOutputStream: short, byte, int , long 값 출력
package com.bitcamp.study;

import java.io.FileOutputStream;

public class Test11_out {

  public static void main(String[] args) throws Exception{ // FIleOutputStream의 사용법을 배우는 것이 목적이지, 오류 처리하는 것이 목적이 아니기 때문이다.
    // TODO Auto-generated method stub
    FileOutputStream out = new FileOutputStream("test5.data");

    byte b =100; // 0x64
    short s = 32000; // 0x7d00
    int i = 1827568876; // 0x6cee7cec
    long l = 2345987655438181L; // 0x0008_55A9_D0D9_8F65

    // b 출력
    out.write(b); // 0x00000cce -> 1바이트만 출력하기 때문에 ce만 출력된다.

    // s 출력
    out.write(s >> 8);
    out.write(s);

    // i 출력
    out.write(i >> 24);
    out.write(i >> 16);
    out.write(i >> 8);
    out.write(i);

    // l 출력
    out.write((int)(l >> 56));
    out.write((int)(l >> 48));
    out.write((int)(l >> 40));
    out.write((int)(l >> 32));
    out.write((int)(l >> 24));
    out.write(i >> 16);
    out.write(i >> 8);
    out.write((i));


    out.close();

    System.out.println("finish!");
  }

}
