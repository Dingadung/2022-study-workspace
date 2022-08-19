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

  public boolean insert(Board board)throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("insert");
    out.writeUTF(new Gson().toJson(board)); // json을 서버로 보내기
    // 서버로부터 요청했던 데이터 읽어오기
    return in.readUTF().equals("success");
  }

  public boolean update(Board board) throws Exception{
    out.writeUTF(dataName);
    out.writeUTF("update");
    out.writeUTF(new Gson().toJson(board));
    return in.readUTF().equals("success");
  }

  public Board findByNo(int boardNo)  throws Exception{
    out.writeUTF(dataName);
    out.writeUTF("findByNo");
    out.writeInt(boardNo);
    if(in.readUTF().equals("fail")) {
      return null;
    }
    String json = in.readUTF();
    Board board = new Gson().fromJson(json, Board.class);
    return board;
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














