// FileOutputStream: 문자열출력
package com.bitcamp.study;

import java.io.FileOutputStream;

public class Test14_out {

  public static void main(String[] args) throws Exception{ // FIleOutputStream의 사용법을 배우는 것이 목적이지, 오류 처리하는 것이 목적이 아니기 때문이다.
    // TODO Auto-generated method stub
    FileOutputStream out = new FileOutputStream("test8.data");

    String name = "ABCabc012가각간";
    String gender = "man";

    byte[] bytes = name.getBytes("UTF-8");
    // 배열의 길이 출력
    out.write(bytes.length>> 24);
    out.write(bytes.length>>16);
    out.write(bytes.length>>8);
    out.write(bytes.length);
    // 값 출력
    out.write(bytes); 


    bytes = gender.getBytes("UTF-8");
    out.write(bytes.length>> 24);
    out.write(bytes.length>>16);
    out.write(bytes.length>>8);
    out.write(bytes.length);
    out.write(bytes);
    out.close();

    System.out.println("finish!");
  }

}
