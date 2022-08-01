package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.LinkedList;
import com.bitcamp.util.ListException;

// 게시글 목록을 관리하는 역할
//
public class BoardList  extends LinkedList{
  private int boardNo = 0;

  @Override
  public void append(Object e) {
    Board board = (Board) e;
    board.no = nextNo();
    super.append(e);
  }

  @Override
  public Board retrieve(int boardNo)throws ListException{
    for (int i = 0; i < length(); i++) {
      Board board = (Board)super.retrieve(i);
      if (board.no== boardNo) {
        return board;
      }
    }
    return null;
  }

  @Override
  public Object delete(int boardNo) {
    for (int i = 0; i < length(); i++) {
      Board board = (Board)super.retrieve(i); 
      if (board.no == boardNo) {
        return super.delete(i);
      }
    }
    return null; // 삭제할 게 없는 경우.
  }


  private int nextNo() {
    return ++boardNo;
  }
}














