package com.eomcs.oop.ex02.cal_test.util;

public class Calculate2{
  // 계산 결과를 담을 변수를 준비한다.
  public int result = 0;
  public void plus(int a) {
    this.result += a;
  }

  public void minus(int a) {
    this.result -= a;
  }

  public void multiple(int a) {
    this.result *= a;
  }

  public void divide(int a) {
    this.result /= a;
  }

  public static int abs(int a) {
    return a>0? a:-1*a;
  }


  public int getResult() {
    return this.result;
  }
}