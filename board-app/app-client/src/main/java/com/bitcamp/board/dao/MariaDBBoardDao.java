package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.Board;

public class MariaDBBoardDao {

  public int insert(Board board)throws Exception {
    try(
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        ){
      if(board.memberNo >0) { // 회원인 경우
        try(
            PreparedStatement pstmt = con.prepareStatement( // 회원
                "insert into app_board(title, content, mno) values(?, ?, ?, ?)");
            )
        {
          pstmt.setString(1, board.title); 
          pstmt.setString(2, board.content);
          pstmt.setInt(3, board.memberNo);
          return pstmt.executeUpdate();
        }
      }/*if*/else { // 비회원인 경우
        try(PreparedStatement pstmt = con.prepareStatement( // 비회원
            "insert into app_board(title, content, pwd) values(?, ?, ?, ?)");
            )
        {
          pstmt.setString(1, board.title); 
          pstmt.setString(2, board.content);
          pstmt.setString(3, board.password);
          return pstmt.executeUpdate();
        }
      } // else
    } // 제일 바깥 try() {}
  }

  public int update(Board board) throws Exception{
    try(
        Connection con = DriverManager.getConnection( // 얘네가 네트워크 통신을 대신 처리해서 우리가 socket 처리를 일일히 할 필요가 없다.
            "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement(
            "update app_board set name = ?, email=?, pwd=sha2(?, 256) where mno = ?") // 값을 넣어야 할 자리를 ?로 표시한다. (in-parameter)
        ){
      pstmt.setString(1, board.name);
      pstmt.setString(2, board.email);
      pstmt.setString(3, board.password);
      pstmt.setInt(4, board.no);

      return pstmt.executeUpdate();
    }
  }

  public Board findByNo(int no)  throws Exception{
    try(
        Connection con = DriverManager.getConnection( // 얘네가 네트워크 통신을 대신 처리해서 우리가 socket 처리를 일일히 할 필요가 없다.
            "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement(
            "select mno, name, email, cdt from app_board where mno=" + no); // 값을 넣어야 할 자리를 ?로 표시한다. (in-parameter)
        // no 자체가 int 타입이라 이런식으로 가능,String은 공격 위험 있어서 불가.
        // pstmt.setInt(1, no); // 변수 선언만 try  () 안에 들어갈 수 있다.
        ResultSet rs = pstmt.executeQuery() // select 문
        ) // try()
    {
      if(!rs.next()) return null; // 결과가 존재하지 않는 경우, 일치하는 번호가 없는 것이다.
      Board board = new Board();
      board.no = rs.getInt("mno");
      board.name = rs.getString("name");
      board.email = rs.getString("email");
      board.createdDate = rs.getDate("cdt");
      return board;
    }// try() {}
  }


  public int delete(int no) throws Exception{
    try(
        Connection con = DriverManager.getConnection( // 얘네가 네트워크 통신을 대신 처리해서 우리가 socket 처리를 일일히 할 필요가 없다.
            "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt1 = con.prepareStatement( "delete from app_ where mno = ?"); // 자식 데이터 지우기
        PreparedStatement pstmt2 = con.prepareStatement( "delete from app_board where mno = ?") // 부모 데이터 지우기
        ) //try ()
    {
      // 자식 데이터 지우기 - 회원이 작성한 게시글 삭제
      pstmt1.setInt(1, no); 
      pstmt1.executeUpdate();
      // 부모 데이터 지우기 - 회원 데이터 삭제
      pstmt2.setInt(1, no); 
      return pstmt2.executeUpdate();
    } // try() {}
  }

  public List<Board> findAll() throws Exception{
    try(
        Connection con = DriverManager.getConnection( "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement( "select mno, name, email from app_board");
        ResultSet rs = pstmt.executeQuery()
        ) // try()
    {
      ArrayList<Board> list = new ArrayList<>();
      while(rs.next()) {
        Board board = new Board();
        board.no = rs.getInt("mno");
        board.name = rs.getString("name");
        board.email = rs.getString("email");
        list.add(board); 
      }
      return list;
    }// try() {}
  }

}














