package com.bitcamp.util;

public class LinkedListTest {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.append("홍길동");
    list.append("임꺽정");
    list.append("유관순");
    list.append("안중근");
    printList(list.getArray());

    list.delete(0);
    printList(list.getArray());

    list.delete(1);
    printList(list.getArray());

    list.delete(1);
    printList(list.getArray());
  }

  static void printList(Object[] arr) {
    System.out.println("----------------------------------");
    for(int i=0;i<arr.length;i++) {
      System.out.println((String)arr[i]);
    }
  }
}

