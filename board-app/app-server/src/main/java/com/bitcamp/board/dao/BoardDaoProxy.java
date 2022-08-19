package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    return  new Gson().fromJson( in.readUTF(), Board.class);
  }

  public boolean delete(int boardNo) throws Exception{
    out.writeUTF(dataName);
    out.writeUTF("delete");
    out.writeInt(boardNo);
    return in.readUTF().equals("success");
  }

  public Board[] findAll() throws Exception{
    out.writeUTF(dataName);
    out.writeUTF("findAll");

    if(in.readUTF().equals("fail")) {
      return null;
    }

    return new Gson().fromJson( in.readUTF(), Board[].class);
  }

}














