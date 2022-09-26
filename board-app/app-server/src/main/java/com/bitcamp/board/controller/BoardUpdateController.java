package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet{

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
      board.no = Integer.parseInt(request.getParameter("no"));
      board.title = request.getParameter("title");
      board.content = request.getParameter("content");

      if(boardDao.update(board) == 0) {
        throw new Exception("게시글 변경 실패"); 
      }

      // Refresh
      response.setHeader("Refresh", "1;url=list");
      response.setContentType("text/html;charset=UTF-8"); 
      request.getRequestDispatcher("/board/update.jsp").include(request, response); 

    } catch(Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
