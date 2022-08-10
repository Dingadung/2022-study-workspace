package com.bitcamp.board.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Board;

// 게시글 목록을 관리하는 역할
//
public class BoardDao {

  List<Board> list = new LinkedList<>();
  private int boardNo = 0;

  String fileName;

  // 인스턴스 값을 유효한 값으로 초기화 시키기 위해 생성자를 사용한다.
  public  BoardDao(String fileName){ 
    this.fileName = fileName;
  }

  public void load() throws Exception {
    try( ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
      // => 먼저 게시글 개수를 읽는다.
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Board board = (Board) in.readObject();

        // 게시글 데이터가 저장된 Board 객체를 목록에 추가한다.
        list.add(board);

        boardNo = board.no;
      }
    }
  }

  public void save() throws Exception{ 
    try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
      out.writeInt(list.size());
      for (Board board :list) {
        out.writeObject(board);
      }
    }
  }

  public void insert(Board board) {
    board.no = nextNo();
    list.add(board);
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
    // Iterator는 값을 꺼내는 일을 한다!!!
    Iterator<Board> iterator = list.iterator();

    // 역순으로 정렬하여 리턴한다.
    Board[] arr = new Board[list.size()];

    int index = list.size() -1;
    while(iterator.hasNext()) {
      arr[index--] = iterator.next();
    }
    return arr;
  }

  private int nextNo() {
    return ++boardNo;
  }
}














