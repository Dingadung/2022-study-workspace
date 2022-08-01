package com.bitcamp.util;

public class LinkedListTest {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add("홍길동");
    list.add("임꺽정");
    list.add("유관순");
    list.add("안중근");

    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));
    System.out.println(list.get(3));
    // System.out.println(list.get(-1));
    // System.out.println(list.get(4));

    //중간 노드 삭제 테스트.
    list.remove(2); // 유관순 삭제.
    printList(list);

    // 맨 앞 노드 삭제 테스트.
    list.remove(0); // 홍길동 삭제
    printList(list);

    // 맨 마지막 노드 삭제 테스트.
    list.remove(1); // 안중근  삭제.
    printList(list);
  }

  static void printList(LinkedList list) {
    System.out.println("---------------------------------------------------");
    for(int i=0;i<list.size();i++) {
      System.out.println(list.get(i));
    }
  }
}
