package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import com.bitcamp.board.domain.Board;
import com.google.gson.Gson;

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



  public void insert(Board board)throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("insert");
    String json = new Gson().toJson(board);
    out.writeUTF(json); // json 서버로 보내기

    // 서버로부터 요청했던 데이터 읽어오기
    if(in.readUTF().equals("success")) {
      System.out.println("게시글을 등록했습니다.");
    } else {
      System.out.println("게시글 등록에 실패했습니다!");
    }
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














