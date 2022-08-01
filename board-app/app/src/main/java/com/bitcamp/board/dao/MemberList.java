package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Member;
import com.bitcamp.util.LinkedList;

// 회원 목록을 관리하는 역할
//
// ObjectList를 상속 후 메서드를 오버라이딩 할 필요가 있음을 확인하기 위해 회원 번호 대신 이메일로 접근한다.
public class MemberList extends LinkedList{


  // 수퍼 클래스 교체에 따라 메서드의 이름도 일관성 있게 수퍼클래스의 메서드 이름과 같게 한다. 즉, 오버로딩 규칙을 준수한다.
  public Member retrieve(String email) {
    for (int i = 0; i < length(); i++) {
      Member member = (Member)retrieve(i); 
      if (member.email.equals(email)) {
        return member;
      }
    }
    return null;
  }

  //수퍼 클래스 교체에 따라 메서드의 이름도 일관성 있게 수퍼클래스의 메서드 이름과 같게 한다. 즉, 오버로딩 규칙을 준수한다.
  public Object delete(String email){
    for (int i = 0; i < length(); i++) {
      Member member = (Member)retrieve(i); 
      if (member.email.equals(email)) {
        return super.delete(i);
      }
    }
    return false;
  }

}














