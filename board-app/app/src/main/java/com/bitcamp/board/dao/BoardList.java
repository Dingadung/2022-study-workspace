package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.ObjectList;

// 게시글 목록을 관리하는 역할
//
public class BoardList  extends ObjectList{
  private int boardNo = 0;

  @Override
  public void add(Object e) {
    Board board = (Board) e;
    board.no = nextNo();
    super.add(e);
  }

  // 수퍼 클래스의 get()메서드를 호출했을 때  예외가 발생하면,
  // 서브 클래스의 get()메서드에서 처리할 상황이 아니다.
  // 서브 클래스의 get()을 호출한 쪽에 보고하는 것이 더 낫다.
  // 이럴 경우, try-catch-를 쓰지 말고 메서드 선언부에 발생되는 예외를 표시라하라!
  @Override
  public Board get(int boardNo) throws Throwable{
    for (int i = 0; i < size(); i++) {
      Board board = (Board)super.get(i+100);
      if (board.no== boardNo) {
        return board;
      }
    }
    return null;
  }


  @Override
  public boolean remove(int boardNo) throws Throwable{
    for (int i = 0; i < size(); i++) {
      Board board = (Board)super.get(i); // 예외를 던지는 메소드를 호출할 때는 무조건 try-catch문으로 감싸줘야한다.
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














