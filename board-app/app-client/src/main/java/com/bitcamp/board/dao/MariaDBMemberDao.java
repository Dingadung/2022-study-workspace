package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.Member;

public class MariaDBMemberDao {
  Connection con;
  public MariaDBMemberDao() throws Exception{
    con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
  }

  public int insert(Member member)throws Exception {
    try(
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
        PreparedStatement pstmt = con.prepareStatement(
            "update app_member set name = ?, email=?, pwd=sha2(?, 256) where mno = ?")
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
        PreparedStatement pstmt = con.prepareStatement(
            "select mno, name, email, cdt from app_member where mno=" + no);
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
  }


  public int delete(int no) throws Exception{
    try(
        PreparedStatement pstmt1 = con.prepareStatement( "delete from app_board where mno = ?"); // 자식 데이터 지우기
        PreparedStatement pstmt2 = con.prepareStatement( "delete from app_member where mno = ?") // 부모 데이터 지우기
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

  public List<Member> findAll() throws Exception{
    try(
        PreparedStatement pstmt = con.prepareStatement( "select mno, name, email from app_member");
        ResultSet rs = pstmt.executeQuery()
        ) // try()
    {
      ArrayList<Member> list = new ArrayList<>();
      while(rs.next()) {
        Member member = new Member();
        member.no = rs.getInt("mno");
        member.name = rs.getString("name");
        member.email = rs.getString("email");
        list.add(member); 
      }
      return list;
    }// try() {}
  }

}














