package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;
import com.bitcamp.board.domain.Member;

@WebServlet("/board/add")
public class BoardAddController extends HttpServlet{

  private static final long serialVersionUID = 1L;

  BoardDao boardDao;

  @Override
  public void init() {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      Board board = new Board();
      board.title = request.getParameter("title");
      board.content = request.getParameter("content");

      // 로그인 사용자 정보는 파라미터로 받아서는 안된다.
      // 반드시 세션에서 꺼내 써야 한다.
      // 왜? 그래야만 클라이언트가 다른 사용자 정보를 보낼 수 있기 때문이다.
      Member loginMember = (Member)request.getSession().getAttribute("loginMember");
      board.memberNo = loginMember.getNo();

      if(boardDao.insert(board)==0) {
        throw new Exception("게시글 등록 실패"); 
      }

      // Refresh
      response.setContentType("text/html;charset=UTF-8"); 
      request.getRequestDispatcher("/board/add.jsp").include(request, response); 

      // Redirect
      // - client에게 콘텐트를 보내지 않는다.
      // - 응답 상태 프로토콜

      //      response.sendRedirect("list");

    } catch(Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
