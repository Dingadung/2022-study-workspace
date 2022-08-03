package com.bitcamp.util;

public class StackTest {
  public static void main(String[] args) {
    Stack stack = new Stack();
    stack.push("홍길동");
    stack.push("임꺽정");
    stack.push("유관순");
    stack.push("안중근");

    while(!stack.empty()) {
      System.out.println(stack.pop());
    }
  }
}
