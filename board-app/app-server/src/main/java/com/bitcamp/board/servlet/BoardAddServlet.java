/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.dao.MariaDBBoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet(value="/board/add")
public class BoardAddServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;
  private BoardDao boardDao;

  public BoardAddServlet() throws Exception{
    Class.forName("org.mariadb.jdbc.Driver");
    Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
    boardDao = new MariaDBBoardDao(con);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    PrintWriter out = resp.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>JWS</title>");
    out.println("<meta http-equiv='Refresh' content ='3; url=list'>"); // 3초 후에 url이 list인 곳으로!
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지민이의 게시글 등록하기!</h1>");

    Board board = new Board();
    board.title = req.getParameter("title");
    board.content = req.getParameter("content");
    board.memberNo = Integer.parseInt(req.getParameter("writerNo"));

    try{
      if(boardDao.insert(board) == 0) {
        out.println("<p>게시글을 등록할 수 없습니다!.</p>");
      }else {
        out.println("<p>게시물을 등록했습니다.</p>");
      }
    } catch(Exception e) {
      System.out.println("<p>실행 중 오류 발생!</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }
}




