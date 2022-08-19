package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import com.bitcamp.board.domain.Board;

// BoardDao와 통신을 담당할 대행 객체 <- server 개발자 입장에서 개발 클라이언트 개발자를 위하여!

//
public class BoardDaoProxy {
  String dataName;
  DataInputStream in;
  DataOutputStream out;

  public BoardDaoProxy(String dataName, DataInputStream in, DataOutputStream out) {
    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }



  public void insert(Board board) {
    board.no = nextNo();
    list.add(board);
  }

  public boolean update(Board board) {
    for (int i = 0; i < list.size(); i++) {
      Board b = list.get(i);
      if (b.no == board.no) {
        list.set(i, board);
        return true;
      }
    }
    return false;
  }

  public Board findByNo(int boardNo) {
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }

  public boolean delete(int boardNo) {
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return list.remove(i) != null;
      }
    }
    return false;
  }

  public Board[] findAll() {

    // 목록에서 값을 꺼내는 일을 할 객체를 준비한다.
    Iterator<Board> iterator = list.iterator();

    // 역순으로 정렬하여 리턴한다.
    Board[] arr = new Board[list.size()];

    int index = list.size() - 1;
    while (iterator.hasNext()) {
      arr[index--] = iterator.next();
    }
    return arr;
  }

  private int nextNo() {
    return ++boardNo;
  }
}














