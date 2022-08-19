package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import com.bitcamp.board.domain.Member;
import com.google.gson.Gson;

// BoardDao와 통신을 담당할 대행 객체 <- server 개발자 입장에서 개발 클라이언트 개발자를 위하여!
// 모든 out은 서버로 보내는 것이다.

public class MemberDaoProxy {
  String dataName;
  String ip;
  int port;
  public MemberDaoProxy(String dataName, String ip, int port) {
    this.dataName = dataName;
    this.ip = ip;
    this.port = port;
  }

  public boolean insert(Member email)throws Exception {
    try(Socket socket =  new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      out.writeUTF(dataName);
      out.writeUTF("insert");
      out.writeUTF(new Gson().toJson(email)); // json을 서버로 보내기
      // 서버로부터 요청했던 데이터 읽어오기
      return in.readUTF().equals("success");
    }
  }

  public boolean update(Member email) throws Exception{
    try(Socket socket =  new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      out.writeUTF(dataName);
      out.writeUTF("update");
      out.writeUTF(new Gson().toJson(email));
      return in.readUTF().equals("success");
    }
  }

  public Member findByEmail(String email)  throws Exception{
    try(Socket socket =  new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      out.writeUTF(dataName);
      out.writeUTF("findByEmail");
      out.writeUTF(email);
      if(in.readUTF().equals("fail")) {
        return null;
      }
      return  new Gson().fromJson( in.readUTF(), Member.class);
    }
  }

  public boolean delete(String email) throws Exception{
    try(Socket socket =  new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      out.writeUTF(dataName);
      out.writeUTF("delete");
      out.writeUTF(email);
      return in.readUTF().equals("success");
    }
  }

  public Member[] findAll() throws Exception{
    try(Socket socket =  new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      out.writeUTF(dataName);
      out.writeUTF("findAll");

      if(in.readUTF().equals("fail")) {
        return null;
      }

      return new Gson().fromJson( in.readUTF(), Member[].class);
    }
  }



}














