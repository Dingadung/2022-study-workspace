package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Member;
import com.bitcamp.util.LinkedList;

// 회원 목록을 관리하는 역할
//
public class MemberDao {

  // MemberDao 가 사용할 의존 객체를 선언한다.
  LinkedList list = new LinkedList();

  public void insert(Member member) {
    list.append(member);
  }

  public Member findByEmail(String email) {
    for (int i = 0; i < list.length(); i++) {
      Member member = (Member) list.retrieve(i);
      if (member.email.equals(email)) {
        return member;
      }
    }
    return null;
  }

  public boolean delete(String email) {
    for (int i = 0; i < list.length(); i++) {
      Member member = (Member) list.retrieve(i);
      if (member.email.equals(email)) {
        return list.delete(i) != null;
      }
    }
    return false;
  }

  public Member[] findAll() {
    //목록에 저장된 회원 데이터를 가져온다.
    Object arr[] = list.getArray();
    //Object배열의 값을 Member배열로 옮긴다.
    Member [] members = new Member[arr.length];
    for(int i=0; i < arr.length; i++) {
      members[i] = (Member)arr[i];
    }
    return members;
  }
}














