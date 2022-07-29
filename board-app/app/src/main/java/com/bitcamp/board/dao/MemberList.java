package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Member;
import com.bitcamp.util.ObjectList;

// 회원 목록을 관리하는 역할
//
// ObjectList를 상속 후 메서드를 오버라이딩 할 필요가 있음을 확인하기 위해 회원 번호 대신 이메일로 접근한다.
public class MemberList extends ObjectList{


  //ObjectList의 get()에서 던지는 예외를 이 메서드에서 처리하지 않고 호출자에게 처리를 위임한다.
  // ListException은 Runtime 계열이기 때문에 메서드 선언부에 표시하지 않아도 된다.
  // Exception 계열의 예외를 다루는 것보다 덜 번거롭다.
  public Member get(String email) {
    for (int i = 0; i < size(); i++) {
      Member member = (Member)get(i); 
      if (member.email.equals(email)) {
        return member;
      }
    }
    return null;
  }


  public boolean remove(String email){
    for (int i = 0; i < size(); i++) {
      Member member = (Member)get(i); 
      if (member.email.equals(email)) {
        return super.remove(i);
      }
    }
    return false;
  }

}














