package com.eomcs.quiz.ex01;

// 출처: codefights.com
//
// 숫자 배열이 있다.
// 나누어 0이 떨어지는 수가 몇 쌍이 있는지 구하라!
//
// [시간 복잡도]
// - ?
//
public class Test07 {

  public static void main(String[] args) {
    int[] values = {2, 4, 8};
    System.out.println(divisorsPairs(values) == 3);

  }

  public static int divisorsPairs(int[] sequence) {
    int result = 0;
    // 이 메서드를 완성하시오!
    int size = sequence.length;
    for(int i=0;i<size;i++) {
    	if(sequence[i]%2==0)result++;
    }
    return result;
  }
}
