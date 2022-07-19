package com.eomcs.quiz.ex01;

// 출처: codefights.com
//
// 숫자 배열에서 각 이웃하는 숫자간의 차가 가장 큰 것을 알아내기
// 예)
// [2, 4, 1, 0] => 3
// 

public class Test05 {

  public static void main(String[] args) {
    System.out.println(maxDiff(new int[]{2, 4, 1, 0}) == 3);
    System.out.println(maxDiff(new int[]{3, 1, 4, 3, 8, 7}) == 5);
  }

  static int maxDiff(int[] values) {
    int answer = 0;
    // 이 메서드를 완성하시오!
    for(int i=0;i<values.length;i++) {
      for(int j=i;j<values.length;j++) {
        if(answer<Math.abs(values[i]-values[j]))answer = Math.abs(values[i]-values[j]);
      }
    }
    return answer;
  }
}
