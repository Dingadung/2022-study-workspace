package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.LinkedList;

// 게시글 목록을 관리하는 역할
//
public class BoardDao {
  LinkedList list = new LinkedList();

  private int boardNo = 0;

  public void insert(Object e) {

    Board board = (Board) e;
    board.no = nextNo();
    list.append(e);
  }

  public Board findByNo(int boardNo) {

    for (int i = 0; i < list.length(); i++) {
      Board board = (Board) list.retrieve(i);
      if (board.no == boardNo) {
        return board;
      }
    }

    return null;
  }

  public boolean delete(int boardNo) {

    for (int i = 0; i < list.length(); i++) {
      Board board = (Board) list.retrieve(i);
      if (board.no == boardNo) {
        return list.delete(i) != null;
      }
    }

    return false;
  }

  public Board[] findAll() {

    Object[] arr = list.getArray();

    Board[] boards = new Board[arr.length];

    for (int i = 0; i < arr.length; i++) {
      boards[i] = (Board) arr[i];
    }

    return boards;
  }

  private int nextNo() {
    return ++boardNo;
  }
}














