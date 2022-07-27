package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.ObjectList;

// 게시글 목록을 관리하는 역할
//
public class BoardList  extends ObjectList{
  // 자동으로 증가하는 게시글 번호
  private int boardNo = 0;

  // 게시글을 저장할 때 자동으로 증가한 번호를 게시글 번호로 설정할 수 있도록
  // add() 메서드를 재정의한다.
  @Override
  public void add(Object e) {
    // 넘어오는 Object 객체를 Board로 형변환해줘야 한다!
    Board board = (Board) e;
    board.no = nextNo();
    super.add(e);
  }

  // 목록에 인덱스로 해당 항목을 찾는 get() 메서드를 오버라이딩하여
  // 게시글을 등록할 때 부여한 일련 번호로 찾을 수 있도록
  // get() 메서드를 재정의(overriding)한다.
  // => 오버라이딩 메서드의 리턴 타입은 원래 타입의 서브 클래스로 변경할 수 있다.
  @Override
  public Board get(int boardNo) {
    for (int i = 0; i < size(); i++) {
      Board board = (Board)super.get(i);
      if (board.no== boardNo) {
        return board;
      }
    }
    return null;
  }


  // 수퍼 클래스의 remove()는 인덱스로 지정한 항목을 삭제한다.
  /// 이것을 게시글 번호에 해당하는 항목을 삭제하도록 상속받은 메소드를 재정의(overriding)한다.
  @Override
  public boolean remove(int boardNo) {
    for (int i = 0; i < size(); i++) {
      Board board = (Board)super.get(i);
      if (board.no == boardNo) {
        return super.remove(i);
      }
    }
    return false;
  }


  private int nextNo() {
    return ++boardNo;
  }
}














