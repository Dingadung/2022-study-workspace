package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.bitcamp.board.domain.Member;
import com.google.gson.Gson;

public class MariaDBMemberDao {

  public int insert(Member member)throws Exception {
    try(
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement(
            "insert into app_member(name, email, pwd) values(?, ?, sha2(?,  256))") // 값을 넣어야 할 자리를 ?로 표시한다. (in-parameter)
        ){
      pstmt.setString(1, member.name); // 인덱스는 1부터 시작한다.
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);

      return pstmt.executeUpdate();
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














