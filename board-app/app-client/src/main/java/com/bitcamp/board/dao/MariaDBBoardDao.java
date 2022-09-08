package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.Board;

public class MariaDBBoardDao {
  Connection con;
  // DAO가 사용할 의존 객체 Connection을 생성자의 파라미터로 받는다. 의존객체: 이 객체가 작업하는데 사용하는 것.
  public MariaDBBoardDao(Connection con) throws Exception {
    this.con = con;
  }

  public int insert(Board board)throws Exception {
    try(
        PreparedStatement pstmt = con.prepareStatement( 
            "insert into app_board(title, content, mno) values(?, ?, ?)");
        )
    {
      pstmt.setString(1, board.title); 
      pstmt.setString(2, board.content);
      pstmt.setInt(3, board.memberNo);
      return pstmt.executeUpdate();
    } //  try() {}
  }

  public int update(Board board) throws Exception{
    try(
        PreparedStatement pstmt = con.prepareStatement(
            "update app_board set title=?, content = ? where bno = ?") // 값을 넣어야 할 자리를 ?로 표시한다. (in-parameter)
        ){
      pstmt.setString(1, board.title);
      pstmt.setString(2, board.content);
      pstmt.setInt(3, board.no);

      return pstmt.executeUpdate();
    }
  }

  public Board findByNo(int no)  throws Exception{
    try(
        PreparedStatement pstmt = con.prepareStatement(
            "select bno, title, content, mno, cdt, vw_cnt from app_board where bno =" + no); // 값을 넣어야 할 자리를 ?로 표시한다. (in-parameter)
        // no 자체가 int 타입이라 이런식으로 가능,String은 공격 위험 있어서 불가.
        // pstmt.setInt(1, no); // 변수 선언만 try  () 안에 들어갈 수 있다.
        ResultSet rs = pstmt.executeQuery() // select 문
        ) // try()
    {
      if(!rs.next()) return null; // 결과가 존재하지 않는 경우, 일치하는 번호가 없는 것이다.
      Board board = new Board();
      board.no = rs.getInt("bno");
      board.title = rs.getString("title");
      board.content = rs.getString("content");
      board.memberNo = rs.getInt("mno");
      board.createdDate = rs.getDate("cdt");
      board.viewCount = rs.getInt("vw_cnt");
      return board;
    }// try() {}
  }


  public int delete(int no) throws Exception{
    try(
        PreparedStatement pstmt = con.prepareStatement( "delete from app_board where bno = ?")
        ) //try ()
    {
      pstmt.setInt(1, no); 
      return pstmt.executeUpdate();
    } // try() {}
  }

  public List<Board> findAll() throws Exception{
    try(
        PreparedStatement pstmt = con.prepareStatement( "select bno, title, mno, cdt, vw_cnt from app_board");
        ResultSet rs = pstmt.executeQuery()
        ) // try()
    {
      ArrayList<Board> list = new ArrayList<>();
      while(rs.next()) {
        Board board = new Board();
        board.no = rs.getInt("bno");
        board.title = rs.getString("title");
        board.memberNo = rs.getInt("mno");
        board.createdDate = rs.getDate("cdt");
        board.viewCount = rs.getInt("vw_cnt");
        list.add(board); 
      }
      return list;
    }// try() {}
  }

}














