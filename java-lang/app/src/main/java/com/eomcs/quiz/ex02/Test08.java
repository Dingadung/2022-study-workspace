package com.eomcs.quiz.ex02;

// 출처: codefights.com
// 
// 장애물들의 x 좌표(양의 좌표)가 배열로 주어질 때,
// 0부터 오른쪽으로 동일한 거리를 점프하여 장애물들을 피한다면,
// 점프해야 할 최소 거리는 얼마인지 구하라!
// 
// 예) 
//   [5, 3, 6, 7, 9] ==> 4
// 
/*
You are given an array of integers representing coordinates of obstacles 
situated on a straight line.

Assume that you are jumping from the point with coordinate 0 to the right. 
You are allowed only to make jumps of the same length represented 
by some integer.

Find the minimal length of the jump enough to avoid all the obstacles.

Example

for [5, 3, 6, 7, 9] output should be 4

[input] array.integer inputArray
non-empty array of positive integers

[output] integer
the desired length
 */
//
// [시간 복잡도]
// - ?
//
public class Test08 {

  public static void main(String[] args) {
    System.out.println(avoidObstacles(new int[]{5,3,6,7,9,13,11}) == 4);
  }

  static int avoidObstacles(int[] inputArray) {
    // 이 메서드를 완성하시오!
    int ans = 1;
    // 점프하는 칸 수가 배열에 주어진 장애물의 좌표의 배수인가?
    // 배열에 주어진장애물들의 좌표 중에서 점프하는 칸 수의 배수가 있으면 안된다.
    // 즉, 나누어 떨어지면 안된다는 말이다.
    for(int i:inputArray) {
      if(i%ans==0) {
        ans++;
      }
    }
    return ans;
  }
}
