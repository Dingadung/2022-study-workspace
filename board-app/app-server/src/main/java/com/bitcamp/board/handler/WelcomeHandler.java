package com.bitcamp.board.handler;

import java.io.PrintWriter;

public class WelcomeHandler {
  public void service(PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>JWS</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지민이의 웹 서비스!</h1>");
    out.println("<p>지민이의 게시판 관리 시스템 프로젝트 입니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }
}
