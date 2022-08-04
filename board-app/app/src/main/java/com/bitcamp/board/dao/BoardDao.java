package com.bitcamp.board.dao;

import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Board;

// 게시글 목록을 관리하는 역할
//
public class BoardDao {

  // List   인터페이스 레퍼런스인, list 변수는
  // List 규격에 따라 만든 객체 주소를 담을 수 있다.
  List<Board>  list = new LinkedList<>();

  private int boardNo = 0;


  public void insert(Board board) {
    board.no = nextNo();
    list.add(board);
    //ctrl하고 눌러도,  list  변수의 타입이 List interface 이기 때문에 실제 add() 를 구현한 LinkedList 로 가지 않는다.
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
    // 의존 객체 ObjectList을 이용하여 목록에 저장된 게시글을 찾아 삭제한다.
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return list.remove(i) != null;
      }
    }

    return false;
  }

  public Board[] findAll() {
    return  list.toArray(new Board[0]);
  }

  private int nextNo() {
    return ++boardNo;
  }
}














