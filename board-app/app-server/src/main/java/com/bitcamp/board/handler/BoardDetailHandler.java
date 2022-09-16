/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board.handler;

import java.io.PrintWriter;
import java.util.Map;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

public class BoardDetailHandler   {

  private BoardDao boardDao;

  public BoardDetailHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public void detail(Map<String, String> paramMap, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>JWS</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지민이의 게시글 상세정보!</h1>");

    int boardNo = Integer.parseInt(paramMap.get("no"));
    //System.out.println(boardNo);
    Board board = boardDao.findByNo(boardNo);

    if(board == null) {
      out.println("<p>해당 번호의 게시글이 없습니다!.</p>");
    }else {
      out.println("<form action='update'>");
      out.println("<h2>지민이게시글 상세보기>o<</h2>");

      out.println("<table border = '1'>");

      out.println("   <tr>");
      out.printf("       <th>번호</th>\n       <td><input name ='no' type='text' value='%d' readonly></td>", board.no);
      out.println("   </tr>");

      out.println("   <tr>");
      out.printf("       <th>제목</th>\n       <td><input name='title' type='text' value='%s' size='60'></td>", board.title);
      out.println("   </tr>");

      out.println("   <tr>");
      out.printf("       <th>내용</th>\n       <td><textarea name='content' rows='10' cols='60'>%s</textarea></td>", board.content);
      out.println("   </tr>");

      out.println("   <tr>");
      out.printf("       <th>조회수</th>\n       <td>%d</td>", board.viewCount);
      out.println("   </tr>");

      out.println("   <tr>");
      out.printf("       <th>작성자</th>\n       <td>%s</td>", board.memberNo);
      out.println("   </tr>");

      out.println("   <tr>");
      out.printf("       <th>등록일</th>\n       <td>%s</td>", board.createdDate);
      out.println("   </tr>");

      out.println("</table>");
      out.println("<p>");
      out.println("<button type='submit'>변경</button>");
      out.printf("<a href='delete?no=%d'>삭제</a>", board.no);
      out.println("</p>");
      out.println("</form>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}




