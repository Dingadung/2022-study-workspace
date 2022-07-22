package com.bitcamp.board.my;

//게시글 목록을 관리하는 역할을 맡고 있는 클래스
public class MemberList {
  //-------------------변수 선언-----------------------\\
  static final int DEFAULT_SIZE = 3;
  int memberCount; 
  Member[] members; 
  int no=0;
  //-------------------변수 선언-----------------------\\

  //-------------------생성자 선언-----------------------\\
  public MemberList(){
    this.members = new Member[DEFAULT_SIZE];
  }
  MemberList(int initCapacity) {
    this.members = new Member[initCapacity];
  }
  //-------------------생성자 선언-----------------------\\

  // 목록에 저장된 인스턴스를 꺼내서 리턴한다.
  Member[] toArray() {
    Member[] arr = new Member[this.memberCount];
    for(int i=0;i<arr.length;i++) {
      arr[i] = this.members[i];
    }
    return arr;
  }

  // 게시글 번호에 해당하는 Board 인스턴스를 찾아 리턴한다.
  Member get(int memberNo) {
    for (int i = 0; i <this.memberCount; i++) {
      if (this.members[i].no == memberNo) {
        return this.members[i];
      }
    }
    return null;
  }

  // Board 인스턴스를 배열에 저장한다.
  void add(Member member) {
    if (this.members.length == this.memberCount) {
      grow();
    }
    member.no = nextNo();
    this.members[this.memberCount++] = member;
  }

  boolean remove(int deleteNo) {
    int deleteIdx = -1;
    for(int i=0;i<this.memberCount;i++) {
      if(deleteNo == this.members[i].no) {
        deleteIdx=i;
        delete(deleteIdx);
        break;
      }
    }
    if(deleteIdx == -1) {
      return false;
    }
    return true;
  }

  void delete(int deleteIdx) {
    // 삭제할 항목의 다음 항목을 앞으로 당긴다.
    for(int i=deleteIdx+1;i<this.memberCount;i++) {
      this.members[i-1] = this.members[i];
    }
    // 게시글 개수를 한 개 줄인 후, 맨 뒤에 있던 항목의 주소를 0으로 설정한다.
    this.members[--this.memberCount]=null;
  }



  void grow(){
    // 저장할 수 있는 게시글 추가
    int newSize = this.members.length + (this.members.length >> 1);
    Member[] newArray = new Member[newSize];
    for(int i=0;i<this.members.length;i++) {
      newArray[i] = this.members[i];
    }
    members = newArray;
  }

  int nextNo() {
    // 추가 등록하는 게시글 번호 설정
    return ++no;
  }
}
