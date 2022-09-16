/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board.handler;

import java.io.PrintWriter;
import java.util.Map;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;
import com.bitcamp.servlet.Servlet;

public class BoardAddHandler implements Servlet{

  private BoardDao boardDao;

  public BoardAddHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Map<String, String> paramMap, PrintWriter out)  throws Exception {
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
    board.title = paramMap.get("title");
    board.content = paramMap.get("content");
    board.memberNo = Integer.parseInt(paramMap.get("writerNo"));

    if(boardDao.insert(board) == 0) {
      out.println("<p>게시글을 등록할 수 없습니다!.</p>");
    }else {
      out.println("<p>게시물을 등록했습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }
}




