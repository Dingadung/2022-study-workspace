package com.eomcs.quiz.ex01;
// 출처: codefights.com
// 
// 주어진 문자열이 반복 문자열인지 검사하라!
// 예) 
//   "abcdeabcde"   --> true
//   "qqq"          --> false
//   "2w2www        --> false
//
/*
Determine whether the given string is a concatenation of another string 
to itself.

Example

for 'tandemtandem' output should be true

for 'qqq' or '2w2ww' output should be false

[input] string inputString

[output] boolean

true if inputString represents a string concatenated to itself, 
false otherwise

 */
//
// [시간 복잡도]
// - ?
//
public class Test15 {

  public static void main(String[] args) {
    System.out.println(isTandemRepeat("abcdeabcde")); // true
    System.out.println(isTandemRepeat("qqq")); // false
    System.out.println(isTandemRepeat("2w2www")); // false
  }

  static boolean isTandemRepeat(String inputString) {
    // 이 메서드를 완성하시오!
    char[] check = new char [inputString.length()];
    char [] input =inputString.toCharArray();
    int f=0, idx=1;
    check[0]=input[0];
    loop:
      for(int i=1;i<input.length;i++) {
        for(int j=0;j<idx;j++) {
          if(input[i] == check[j]) {
            break loop;
          }else {
            check[idx++] = input[i];
            break;
          }
        }
      }

    for(int i=0;i<input.length;i+=idx) {
      for(int j=0;j<idx;j++) {
        if(input[i+j] != check[j]) {
          return false;
        }
      }
      return true;
    }

    return true;
  }
}
