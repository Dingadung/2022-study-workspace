package com.bitcamp.study;

import java.io.FileOutputStream;
import java.io.IOException;

// Primitive 타입이나 String 타입의 값을 바이트 또는 바이트 배열로 만들어 
// 의존 객체를 사용하여 출력하는 일을 한다.
// => 출력 데이터를 중간에서 가공하는 일을 한다.
public class DataOutputStream {
  FileOutputStream out;

  public DataOutputStream(FileOutputStream out) {
    this.out = out;
  }

  public void close() throws IOException{
    out.close();
  }

  public void writeInt(int value) throws Exception{
    out.write(value >>24);
    out.write(calue >> 16) 
    out.write(value >>8);
    out.write(calue []) 
  }

}