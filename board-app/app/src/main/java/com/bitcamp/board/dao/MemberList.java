package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Member;

// 회원 목록을 관리하는 역할
//
public class MemberList extends ObjectList {
  // 선배가 작성한 코드 이제 내것 처럼 작성할 수 있다!
  private int no = 0;
  private int nextNo() {
    return ++no;
  }
  @Override
  public void add(Object object) {
    // TODO Auto-generated method stub
    // 게시글을 그냥 바로 넣으면 안되고 번호설정하고 넣어야해!
    Member member = (Member) object; // 얘 원래 멤버인거 알려준거임
    member.no = nextNo();
    super.add(object); /// 근데 그냥 member 넣으면 안되나? 
    // 원래 import한 add의 파라미터의 데이터 타입이 오브젝트라서
    // 오브젝트의 서브클래스 타입은 다 넣을 수 있다.
  }

  @Override
  public Member get(int memberNo) {
    // TODO Auto-generated method stub
    int idx=-1;
    for(int i=0;i<this.length;i++) {
      Member member = (Member)this.list[i];
      if(member.no ==memberNo) {
        return member;
      }
    }
    return null;
  }

  @Override
  public boolean remove(int memberNo) {
    // TODO Auto-generated method stub
    int idx=-1;
    for(int i=0;i<this.length;i++) {
      Member member = (Member)this.list[i];
      if(member.no ==memberNo) {
        idx =i;
        break;
      }
    }
    return super.remove(idx);
  }
}














