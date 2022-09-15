/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;
import com.bitcamp.util.Prompt;

public class BoardHandler   {

  private BoardDao boardDao;

  public BoardHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public void list(Map<String, String> paramMap, PrintWriter out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>JWS</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지민이의 게시글 상세정보!</h1>");
    out.println("<h2>지민이테이블><</h2>");
    out.println("<table border = '1'>");
    out.println("   <tr>");
    out.println("       <th>번호</th>");
    out.println("       <th>제목</th>");
    out.println("       <th>조회수</th>");
    out.println("       <th>작성자</th>");
    out.println("       <th>등록일</th>");
    out.println("   </tr>");

    List<Board> boards = boardDao.findAll();

    for (Board board : boards) {
      out.printf(" <tr>");
      out.printf("    <td>%d</td>", board.no);
      out.printf("    <td><a href='detail?no=%d'>%s</a></td>", board.no, board.title); // 같은 서버, 같은 포트 번호 면 링크 다 써줄 필요 없다.
      out.printf("    <td>%d</td>", board.viewCount);
      out.printf("    <td>%d</td>", board.memberNo);
      out.printf("    <td>%s</td>", board.createdDate);
      out.printf("</tr>");
    }
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
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
    System.out.println(boardNo);
    Board board = boardDao.findByNo(boardNo);

    if(board == null) {
      out.println("<p>해당 번호의 게시글이 없습니다!.</p>");
    }else {
      out.println("<form action='update'>");
      out.println("<h2>지민이게시글 상세보기>o<</h2>");
      out.println("<table border = '1'>");
      out.println("   <tr>");
      out.println("       <th>번호</th>");
      out.printf("       <td><input name ='no' type='text' value='%d' readonly></td>", board.no);
      out.println("   </tr>");
      out.println("   <tr>");
      out.println("       <th>제목</th>");
      out.printf("       <td><input name='title' type='text' value='%s' size='60'></td>", board.title);
      out.println("   </tr>");
      out.println("   <tr>");
      out.println("       <th>내용</th>");
      out.printf("       <td><textarea name='content' rows='10' cols='60'>%s</textarea></td>", board.content);
      out.println("   </tr>");
      out.println("   <tr>");
      out.println("       <th>조회수</th>");
      out.printf("       <td>%d</td>", board.viewCount);
      out.println("   </tr>");
      out.println("   <tr>");
      out.println("       <th>작성자</th>");
      out.printf("       <td>%s</td>", board.memberNo);
      out.println("   </tr>");
      out.println("   <tr>");
      out.println("       <th>등록일</th>");
      out.printf("       <td>%s</td>", board.createdDate);
      out.println("   </tr>");
      out.println("</table>");
      out.println("<p>");
      out.println("<button type='submit'>변경</button>");
      out.println("<a href='delete?no=%d'>삭제</a>");
      out.println("</p>");
      out.println("</form>");
    }
    out.println("</body>");
    out.println("</html>");
  }

  private void onInput(DataInputStream in, DataOutputStream out) throws Exception {

    Prompt prompt = new Prompt(in, out);

    Board board = new Board();

    board.title = prompt.inputString("제목? ");
    board.content = prompt.inputString("내용? ");
    board.memberNo = prompt.inputInt("작성자? ");

    boardDao.insert(board);
    out.writeUTF("게시글을 등록했습니다.");
  }

  public void delete(Map<String, String> paramMap, PrintWriter out)  throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>JWS</title>");
    out.println("<meta http-equiv='Refresh' content ='3; url=list'>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지민이의 게시글 변경하기!</h1>");

    int no = Integer.parseInt(paramMap.get("no"));

    if(boardDao.delete(no) == 0) {
      out.println("<p>해당 번호의 게시글이 없습니다!.</p>");
    }else {
      out.println("<p>해당 게시물을 삭제했습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  } // delete()

  public void update(Map<String, String> paramMap, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>JWS</title>");
    out.println("<meta http-equiv='Refresh' content ='3; url=list'>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지민이의 게시글 변경하기!</h1>");

    Board board = new Board();
    board.no = Integer.parseInt(paramMap.get("no"));
    board.title = paramMap.get("title");
    board.content = paramMap.get("content");

    if(boardDao.update(board) == 0) {
      out.println("<p>해당 번호의 게시글이 없습니다!.</p>");
    }else {
      out.println("<p>해당 게시물을 변경했습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  } // update()
}




