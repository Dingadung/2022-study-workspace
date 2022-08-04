package com.bitcamp.board.dao;

import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Member; // ctrl+shift+ Alphabet O

// 회원 목록을 관리하는 역할
//
public class MemberDao {

  // MemberDao는 List 규격에 맞춰 생산한 객체를 사용할 것이다.
  //=> Object List클래스는 List 규격에 맞춰 메서드를 정의한 클래스다.
  // 따라서, List   레퍼런스 변수에 그 주소를 저장할 수 있다.
  List<Member>  list = new LinkedList<>(); // 이 규칙에 따라 만든 객체 주소를담겠다는 의미이다 List

  public void insert(Member member) {
    list.add(member);
  }

  public Member findByEmail(String email) {
    for (int i = 0; i < list.size(); i++) {
      Member member = list.get(i);
      if (member.email.equals(email)) {
        return member;
      }
    }
    return null;
  }

  public boolean delete(String email) {
    for (int i = 0; i < list.size(); i++) {
      Member member = list.get(i);
      if (member.email.equals(email)) {
        return list.remove(i)  != null;
      }
    }
    return false;
  }

  public Member[] findAll() {

    return list.toArray(new Member[0]); // 빈배열 넘기면 새 배열을 얘가 알아서 만들어준다!  , size가 0인것임.
  }
}