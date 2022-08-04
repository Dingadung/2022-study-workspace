package com.bitcamp.util;

import com.bitcamp.board.domain.Board;

public class ListTest {
  private void mtin() {
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
  }
}
