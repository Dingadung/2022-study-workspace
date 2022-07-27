package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Member;
import com.bitcamp.util.ObjectList;

// 회원 목록을 관리하는 역할
//
// ObjectList를 상속 후 메서드를 오버라이딩 할 필요가 있음을 확인하기 위해 회원 번호 대신 이메일로 접근한다.
public class MemberList extends ObjectList{
  //private int memberNo = 0;

  // 인덱스 대신 이메일로 회원 데이터를 찾을 수 있도록
  // 메서드를 추가한다.
  // 오버로딩: 파라미터 타입이나, 개수, 순서가 다르더라도 같은 기능을 수행하는 메서드에 대해 같은 이름을 부여함으로써 프로그래밍의 일관성을 제공하는 문법!
  // 오버로딩을 하려면 메서드 끼리 같은 기능을 수행해야 한다!!!!!! -> 일관성 유지
  // 얘는 수퍼 클래스에서의 get과 파라미터 타입이 다르므로 결론적으로 오버로딩을 한 것이다!
  // 메서드 호출할 때 일관되게 사용할 수 있다. => 오버로딩!
  public Member get(String email) {
    for (int i = 0; i < size(); i++) {
      Member member = (Member)get(i); // 여기는 BoardList와 달리, get과 메서드 이름이 달라서 super붙일 필요 없다!
      if (member.email.equals(email)) {
        return member;
      }
    }
    return null;
  }

  //  @Override
  //  public void add(Object e) {
  //    Member member = (Member)e;
  //    member.no = nextNo();
  //    super.add(member);
  //  }

  // @Override: 컴파일러야, 수퍼 클래스의 메서드를 재정의하기 위해 다음 메서드를 만들었는데, 이거 제대로 검사했는지 확인해줄래?
  // 근데 밑의 remove 메소드는 오버로딩이라 @Override를 붙여주면 오류가 나게 된다!
  // 인덱스 대신 이메일로 회원 데이터를 찾아 삭제하는 메서드

  // 수퍼 클래스로부터 상속 받은 메서드와 같은 일을 하며, 메서드 이름도 같다 -> 오버로딩!
  public boolean remove(String email) {
    for (int i = 0; i < size(); i++) {
      Member member = (Member)get(i); // 여기는 BoardList와 달리, get과 메서드 이름이 달라서 super붙일 필요 없다!
      if (member.email.equals(email)) {
        return super.remove(i);
      }
    }
    return false;
  }

  //  private int nextNo() {
  //    return ++memberNo;
  //  }
}














