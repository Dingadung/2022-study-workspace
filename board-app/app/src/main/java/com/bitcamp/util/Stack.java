package com.bitcamp.util;

public class Stack extends LinkedList{
  // 비록, push() 나 pop() 메서드는 LinkedList의 메서드를 호출하는 껍데기에 불과하지만, Stack이라는 개념을 명확하게 표현할 수 있어서
  // 이렇게 별도의 메서드로 구현하는 것이다.

  // 스택의 맨 마지막에 값을 추가한다.
  public void push(Object value) {
    add(value); // 수퍼 클래스의 메서드를 호출하여push() 기능을 구현한다.
  }

  // 스택의 맨 마지막 값을 꺼낸다. 꺼낸 값은 스택에서 제거한다.
  public Object pop() {
    remove(size()-1); // 수퍼 클래스의 메서드를 호출하여pop() 기능을 구현한다.
    return remove(size()-1);
  }

  // 스택이 비어 있는지 여부를 알려준다.
  public boolean empty() { // 스택이 비어있는지 여부를 알려준다.
    return size == 0;
  }

  public Object peek() { // 스택의 저장된 맨 마지막 값을 리턴한다. 제거하지 않는다.
    return get(size-1);
  }
}
