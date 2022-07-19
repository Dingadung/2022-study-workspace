package com.eomcs.oop.ex02.domain;

// 다른 패키지에서도 사용할 수 있도록 public 공개한다.
public class Score {
  //다른 패키지에서 이 설계도에 따라 만든 변수에 접근할 수 있도록
  //접근 범위를 넓힌다.
  public String name; // 필드
  public int kor;
  public int eng;
  public int math;
  public int sum;
  public float average;


  //static method ==>instance method로 
  public void compute() {
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