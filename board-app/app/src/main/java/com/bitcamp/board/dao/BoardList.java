package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.ListException;
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


  //ObjectList의 get()에서 던지는 예외를 이 메서드에서 처리하지 않고 호출자에게 처리를 위임한다.
  // ListException은 Runtime 계열이기 때문에 메서드 선언부에 표시하지 않아도 된다.
  // Exception 계열의 예외를 다루는 것보다 덜 번거롭다.
  // Throwable이 상위 클래스여도수퍼가 에러 안던지니까 얘도 던지면 안된다!
  @Override
  public Board get(int boardNo)throws ListException{
    for (int i = 0; i < size(); i++) {
      Board board = (Board)super.get(i);
      if (board.no== boardNo) {
        return board;
      }
    }
    return null;
  }

  //ObjectList의 get()에서 던지는 예외를 이 메서드에서 처리하지 않고 호출자에게 처리를 위임한다.
  // ListException은 Runtime 계열이기 때문에 메서드 선언부에 표시하지 않아도 된다.
  // Exception 계열의 예외를 다루는 것보다 덜 번거롭다.
  @Override
  public boolean remove(int boardNo) {
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













