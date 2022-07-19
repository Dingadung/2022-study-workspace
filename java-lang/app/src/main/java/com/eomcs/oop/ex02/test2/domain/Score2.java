package com.eomcs.oop.ex02.test2.domain;

public class Score2 {
  public String name;
  public int kor;
  public int eng;
  public int math;
  public int sum;
  public float aver;

  public Score2(String name, int kor, int eng, int math) {
    this.name= name;
    this.kor=kor;
    this.eng=eng;
    this.math=math;

    this.calc();
  }

  public void calc() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = (float) this.sum / 3;
  }
}
