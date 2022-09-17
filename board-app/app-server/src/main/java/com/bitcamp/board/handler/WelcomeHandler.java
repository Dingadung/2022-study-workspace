package com.bitcamp.board.handler;

import java.io.PrintWriter;
import java.util.Map;
import com.bitcamp.servlet.Servlet;
import com.bitcamp.servlet.annotation.WebServlet;

@WebServlet(value="/")
public class WelcomeHandler implements Servlet{
  @Override
  public void service(Map<String, String> paramMap, PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>JWS</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지민이의 웹 서비스!</h1>");
    out.println("<p>지민이의 게시판 관리 시스템 프로젝트 입니다.</p>");
    out.println("<ul>");
    out.println("  <li><a href='/board/list'>게시글</a></li>");
    out.println("  <li><a href='/member/list'>회원</a></li>");
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");
  }
}
