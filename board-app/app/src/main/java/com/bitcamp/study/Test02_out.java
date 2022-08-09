// FileOutputStream: write(int) 사용법
package com.bitcamp.study;

import java.io.FileOutputStream;

public class Test02_out {

  public static void main(String[] args) throws Exception{ // FIleOutputStream의 사용법을 배우는 것이 목적이지, 오류 처리하는 것이 목적이 아니기 때문이다.
    // TODO Auto-generated method stub
    FileOutputStream out = new FileOutputStream("test2.data");

    // 4바이트 출력
    out.write(3278 >> 24); //             00|000cee
    out.write(3278 >> 16); //        0000|0cce
    out.write(3278 >> 8); //      00000c|ce
    out.write(3278); //            0x00000cce -> 1바이트만 출력하기 때문에 ce만 출력된다.

    out.close();

    System.out.println("finish!");
  }

}
