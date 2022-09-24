<%@page import="com.bitcamp.board.dao.BoardDao"%>
<%@ page language="java" 
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>JWS</title>
    <meta http-equiv='Refresh' content ='3; url=list'>
    </head>
    <body>
    <h1>지민이의 게시글 삭제하기!</h1>
<%
    int no = Integer.parseInt(request.getParameter("no"));

    try {
      if(boardDao.delete(no) == 0) {
%>
        <p>해당 번호의 게시글이 없습니다!.</p>
<%      
      }else {
  %> 
        <p>해당 게시물을 삭제했습니다.</p>
<%      
      }
    } catch(Exception e) {
%>
      <p>실행 중 오류 발생!</p>
<%    
    }
    %>
    </body>
    </html>
    
  <%!
BoardDao boardDao;

//init(ServletConfig) 메서드에 코드를 넣고 싶으면 
//다음과 같이 JspPage.jspInit() 메서드를 오버라이딩 하라!
public void jspInit() {
  boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
}
%>