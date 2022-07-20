package com.eomcs.oop.ex02.cal_test;

//# 관련된 기능(메서드)을 묶어 분류하기
//1) 분류 전
//2) 메서드를 클래스로 묶어 분류하기
//3) 클래스 변수 도입
//4) 클래스 변수의 한계 확인
//5) 인스턴스 변수 도입
//6) 인스턴스 메서드 활용
//7) 패키지 멤버 클래스로 분리
//8) 클래스를 역할에 따라 패키지로 분류하기
public class CalTest6 {
  static class Calculate{
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

  public static void main(String[] args) {
    // 다음 식을 연산자 우선 순위를 고려하지 않고 순서대로 계산하라!
    // 2 + 3 - 1 * 7 / 3 = ?
    Calculate c1 = new Calculate();
    Calculate c2 = new Calculate();


    // 메서드를 호출하여 작업을 수행하고,
    // 리턴 결과는 로컬 변수에 저장한다.
    c1.plus(2);
    c1.minus(1);
    c1.multiple(7);
    c1.divide(3);

    System.out.printf("result = %d\n", c1.result);

    c2.result = 0;
    c2.plus(3); // + 3
    c2.multiple(2); // + 3 * 2
    c2.plus(7); // + 3 * 2 + 7
    c2.divide(4); // + 3 * 2 + 7 / 4
    c2.minus(5); // + 3 * 2 + 7 / 4 - 5 = ?

    System.out.printf("result = %d\n", c2.result);
  }


}
// 클래스 문법의 용도?
// 1) 사용자 정의 데이터 타입 만들 때
// - 즉 새로운 구조의 메모리를 설계할 때 사용한다.
// 2) 메서드를 묶을 때
// - 서로 관련된 기능을 관리하기 쉽게 묶고 싶을 때 사용한다.


