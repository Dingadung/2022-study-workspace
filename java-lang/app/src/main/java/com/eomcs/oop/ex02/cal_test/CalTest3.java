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
public class CalTest3 {
  static class Calculate{
    // 계산 결과를 담을 변수를 준비한다.
    static int result = 0;
    static void plus(int a) {
      result += a;
    }

    static void minus(int a) {
      result -= a;
    }

    static void multiple(int a) {
      result *= a;
    }

    static void divide(int a) {
      result /= a;
    }
  }

  public static void main(String[] args) {
    // 다음 식을 연산자 우선 순위를 고려하지 않고 순서대로 계산하라!
    // 2 + 3 - 1 * 7 / 3 = ?



    // 메서드를 호출하여 작업을 수행하고,
    // 리턴 결과는 로컬 변수에 저장한다.
    Calculate.plus(2);
    Calculate.minus(1);
    Calculate.multiple(7);
    Calculate.divide(3);

    System.out.printf("result = %d\n", Calculate.result);
  }


}
// 클래스 문법의 용도?
// 1) 사용자 정의 데이터 타입 만들 때
// - 즉 새로운 구조의 메모리를 설계할 때 사용한다.
// 2) 메서드를 묶을 때
// - 서로 관련된 기능을 관리하기 쉽게 묶고 싶을 때 사용한다.


