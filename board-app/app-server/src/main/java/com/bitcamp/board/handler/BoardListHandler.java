package com.bitcamp.board.handler;

import java.io.PrintWriter;

public class BoardListHandler {
  public void execute(PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>JWS</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지민이의 게시글!</h1>");
    out.println("<p>지민이의 게시글 content loading...</p>");
    out.println("</body>");
    out.println("</html>");
  }
}
