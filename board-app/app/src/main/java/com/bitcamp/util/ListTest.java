package com.bitcamp.util;

import com.bitcamp.board.domain.Board;

public class ListTest {
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ObjectList<String> list = new ObjectList<>(); // 뒤의 괄호에는 String을 적어도 되고, 안적어도 된다.
    list.add("홍길동");
    list.add("임꺽정");
    list.add(null);
    // list.add(new Board()); // 컴파일 오류!
    String s1 =list.get(0); // 형변환할 필요가 없다!  String s1 = (String)list.get(0);

    ObjectList<Board> list2 = new ObjectList<>();
    // list2.add("Hello"); // 컴파일 오류!
    list2.add(new Board());
    //list2.add(new Member()); // 컴파일 오류!

    Board b = list2.get(0); // 형변환할 필요가 없다! Board b = (Board)list2.get(0);

    LinkedList<String> list3 = new LinkedList<>();

    list3.add("홍길동");
    list3.add("임꺽정");

    //    String[] names = new String[list3.size];
    //    list3.toArray(names);

    String[] names = list3.toArray(new String[0]);

    for(String name:names) {
      System.out.println(name);
    }
  }
}
