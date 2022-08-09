//FIleInputStream: read(byte[]) 사용법
package com.bitcamp.study;

import java.io.FileInputStream;

public class Test05_in {

  public static void main(String[] args) throws Exception{
    // TODO Auto-generated method stub
    FileInputStream in = new FileInputStream("test3.data");

    // 바이트를 담을 배열 준비
    byte[] arr = new byte[3];
    int b;

    // 배열 크기만큼 바이트를 읽어서 담기
    int len = in.read(arr);
    System.out.println("읽은 개수는 : " + len);
    for(int i=0;i<arr.length;i++) {
      System.out.printf("16진수 두자리: %02x\n", arr[i]);
      System.out.printf("정수: %d\n\n", arr[i]);
    }

    in.close();
    System.out.println("finish!");
  }

}
