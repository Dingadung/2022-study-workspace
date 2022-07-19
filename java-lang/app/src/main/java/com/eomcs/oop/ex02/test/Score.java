package com.eomcs.oop.ex02.test;

class Score{
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float aver;

  void calc() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = (float) this.sum / 3;
  }
}
