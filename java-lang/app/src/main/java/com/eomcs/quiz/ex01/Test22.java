package com.eomcs.quiz.ex01;
// copyright by codefights.com
// 
// 숫자와 달의 영어 이름을 연결하라!
// 단 숫자는 0 이상이다. 
//
// 1: "Jan"
// 2: "Feb"
// 3: "Mar"
// 4: "Apr"
// 5: "May"
// 6: "Jun"
// 7: "Jul"
// 8: "Aug"
// 9: "Sep"
// 10: "Oct"
// 11: "Nov"
// 12: "Dec"
// 예) 
//    getMonthName(1) = "Jan"
//    getMonthName(0) = "invalid month"
// 
//
/*
Map the given integer to a month.

Example

getMonthName(1) = "Jan"
getMonthName(0) = "invalid month"

[input] integer mo
a non-negative integer

[output] string

a 3 letter abbreviation of month number mo or "invalid month" 
if the month doesn't exist
 */
//
// [시간 복잡도]
// - ?
//
public class Test22 {

  public static void main(String[] args) {
    System.out.println(getMonthName(1) == "Jan");
    System.out.println(getMonthName(12) == "Dec");
    System.out.println(getMonthName(0) == "invalid month"); 
  }

  static String getMonthName(int mo) {
    // 이 메서드를 완성하시오!
    switch(mo) {
      case  1:  return "Jan"; 
      case  2: return"Feb"; 
      case  3: return"Mar"; 
      case     4: return"Apr"; 
      case  5: return"May"; 
      case     6: return"Jun"; 
      case       7: return"Jul"; 
      case       8: return"Aug"; 
      case         9: return"Sep"; 
      case            10: return"Oct"; 
      case              11: return"Nov"; 
      case                 12: return"Dec"; 
      default: return "invalid month";
    }
  }
}
