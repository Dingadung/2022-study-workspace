package com.eomcs.quiz.ex01;
// copyright by codefights.com
// 
// 문자열에 포함된 숫자의 합을 구하라.
// 예) 
//   sumUpNumbers("2 apples, 12 oranges") ==> 5
//
/*
Find the sum of all digits that occur in a string.

Example

sumUpNumbers("2 apples, 12 oranges") = 5

[input] string inputString

[output] integer
 */
//
// [시간 복잡도]
// - ? 
//
public class Test23 {

  public static void main(String[] args) {
    System.out.println(sumUpDigits("2 apples, 12 oranges") == 5);
  }

  static int sumUpDigits(String inputString) {
    int answer = 0;
    // 이 메서드를 완성하시오!
    for(int i=0;i<inputString.length();i++) {
      if(isNum(inputString.charAt(i))) {
        int tmp =0;
        while(isNum(inputString.charAt(i))) {
          tmp = tmp*10 + Character.getNumericValue(inputString.charAt(i));
          i++;
        }
        System.out.println(tmp);
        answer += tmp;
      }

    }
    return answer;
  }

  static boolean isNum(char e){
    return e >= '0' &&e <='9';
  }
}
