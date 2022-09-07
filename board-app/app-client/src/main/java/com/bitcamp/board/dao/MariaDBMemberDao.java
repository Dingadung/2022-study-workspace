package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.bitcamp.board.domain.Member;
import com.google.gson.Gson;

public class MariaDBMemberDao {

  public int insert(Member member)throws Exception {
    try(
        Connection con = DriverManager.getConnection( // 얘네가 네트워크 통신을 대신 처리해서 우리가 socket 처리를 일일히 할 필요가 없다.
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

  public int update(Member member) throws Exception{
    try(
        Connection con = DriverManager.getConnection( // 얘네가 네트워크 통신을 대신 처리해서 우리가 socket 처리를 일일히 할 필요가 없다.
            "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement(
            "update app_member set name = ?, email=?, pwd=sha2(?, 256) where mno = ?") // 값을 넣어야 할 자리를 ?로 표시한다. (in-parameter)
        ){
      pstmt.setString(1, member.name);
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);
      pstmt.setInt(4, member.no);

      return pstmt.executeUpdate();
    }
  }

  public Member findByNo(int no)  throws Exception{
    try(
        Connection con = DriverManager.getConnection( // 얘네가 네트워크 통신을 대신 처리해서 우리가 socket 처리를 일일히 할 필요가 없다.
            "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement(
            "select mno, name, email, cdt from app_member where mno=" + no); // 값을 넣어야 할 자리를 ?로 표시한다. (in-parameter)
        // no 자체가 int 타입이라 이런식으로 가능,String은 공격 위험 있어서 불가.
        // pstmt.setInt(1, no); // 변수 선언만 try  () 안에 들어갈 수 있다.
        ResultSet rs = pstmt.executeQuery() // select 문
        ) // try()
    {
      if(!rs.next()) return null; // 결과가 존재하지 않는 경우, 일치하는 번호가 없는 것이다.
      Member member = new Member();
      member.no = rs.getInt("mno");
      member.name = rs.getString("name");
      member.email = rs.getString("email");
      member.createdDate = rs.getDate("cdt");
      return member;
    }// try() {}
  } // findByNo()


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














