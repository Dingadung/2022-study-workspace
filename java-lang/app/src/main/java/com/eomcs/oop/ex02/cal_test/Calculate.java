package com.eomcs.oop.ex02.cal_test;

class Calculate{
  // 계산 결과를 담을 변수를 준비한다.
  int result = 0;
  void plus(int a) {
    this.result += a;
  }

  void minus(int a) {
    this.result -= a;
  }

  void multiple(int a) {
    this.result *= a;
  }

  void divide(int a) {
    this.result /= a;
  }

  static int abs(int a) {
    return a>0? a:-1*a;
  }
}