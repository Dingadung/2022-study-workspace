package com.eomcs.oop.ex02.domain;

// 다른 패키지에서도 사용할 수 있도록 public 공개한다.
public class Score2 {
  //다른 패키지에서 이 설계도에 따라 만든 변수에 접근할 수 있도록
  //접근 범위를 넓힌다.
  public String name; // 필드
  public int kor;
  public int eng;
  public int math;
  public int sum;
  public float average;


  //new 연산자를 이용하여 인스턴스를 만들 때 자동으로 호출되는 메서드 => 생성자
  //생성자 (constructor) - 메서드 명은 클래스명과 같아야한다.
  //- 리턴 타입은 없다.
  // 오직 new 명령을 실행할 때 호출할 수 있다. 나중에 따로 호출할 수 없다.
  public Score2(String n, int k, int e, int m) {
    this.name = n;
    this.kor = k;
    this.eng = e;
    this.math = m;

    this.compute();
  }

  //static method ==>instance method로 
  public void compute() {
    //instance 메서드를 호출할 때 넘겨준 인스턴스 주소는
    //this라는 내장 변수에 보관된다.
    this.sum = this.kor + this.eng + this.math;
    //this에 넘겨준 레퍼런스 변수의 주소가 들어있다.
    this.average = this.sum / 3f;
  }

}