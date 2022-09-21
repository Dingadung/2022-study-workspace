package com.bitcamp.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/welcome") // 얘는 tomcat server가 보는 애라 app/을 안붙여줘도 된다. -> context root (web application root)
public class WelcomeServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // 콘텐트를 출력하는 출력 스트림을 준비하기 전에 어떤 인코딩으로 출력할 것인지 먼저 설정해야 한다.
    resp.setContentType("text/html; charset=UTF-8");

    PrintWriter out = resp.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>JWS</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지민이의 웹 서비스! </h1>");
    out.println("<p>지민이의 게시판 관리 시스템 프로젝트 입니다. Servlet이용했답니당~</p>");
    out.println("<ul>");
    out.println("  <li><a href='board/list'>게시글</a></li>"); // 얘는 웹브라우저가 보는 링크라 정확히 서버 루트까지 써줘야 한다. /app/board/list -> server root welcome, / 안붙이면, 위의 리스트가 앞에 추가됨
    out.println("  <li><a href='member/list'>회원</a></li>");
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");
  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    // TODO Auto-generated method stub
    System.out.println("WelcomeServlet.init()");
    this.config = config;
  }

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
    System.out.println("WelcomeServlet.destroy()");
  }

  @Override
  public String getServletInfo() {
    // TODO Auto-generated method stub
    System.out.println("WelcomeServlet.getServletInfo()");
    return "환영 인사를 하는 서블릿";
  }

  @Override
  public ServletConfig getServletConfig() {
    System.out.println("WelcomeServlet.getServletConfig()");
    return this.config;
  }

}
