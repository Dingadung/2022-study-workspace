package com.eomcs.oop.ex02;

// 다른 패키지에서도 사용할 수 있도록 public 공개한다.
public class Score {
  String name; // 필드
  int kor;
  int eng;
  int math;
  int sum;
  float average;

  //static method ==>instance method로 
  void compute() {
    //instance 메서드를 호출할 때 넘겨준 인스턴스 주소는
    //this라는 내장 변수에 보관된다.
    this.sum = this.kor + this.eng + this.math;
    //this에 넘겨준 레퍼런스 변수의 주소가 들어있다.
    this.average = this.sum / 3f;
  }

  static Score createScore(String name, int kor, int eng, int math) {
    Score s = new Score();
    s.name = name;
    s.kor = kor;
    s.eng = eng;
    s.math = math;
    return s;
  }
}