package com.bitcamp.board;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.dao.MariaDBBoardDao;
import com.bitcamp.board.dao.MariaDBMemberDao;
import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.handler.BoardHandler;
import com.bitcamp.board.handler.ErrorHandler;
import com.bitcamp.board.handler.WelcomeHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

// MiniWebServer class - 게시글 및 회원 관리 처리

public class MiniWebServer {

  public static void main(String[] args) throws Exception{
    Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
    BoardDao boardDao = new MariaDBBoardDao(con);
    MemberDao memberDao = new MariaDBMemberDao(con);

    WelcomeHandler welcomeHandler = new WelcomeHandler();
    ErrorHandler errorHandler = new ErrorHandler();
    BoardHandler boardHandler = new BoardHandler(boardDao);

    class MyHttpHandler implements HttpHandler{
      @Override
      public void handle(HttpExchange exchange) throws IOException { // client가 요청할 때 마다 실행되는 method
        System.out.println("클라이언트가 call함");

        URI requestUri = exchange.getRequestURI();

        String path = requestUri.getPath();
        String query = requestUri.getQuery();
        byte[] bytes = null;

        try(StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter)) // try()
        {

          Map<String, String> paramMap = new HashMap<>();
          if(query != null && query.length() > 0) {  // 예)no =1 & title=aaaa&content=bbb
            String[] entries = query.split("&");
            for(String entry:entries) { // 예) no=1
              String[] kv = entry.split("=");
              paramMap.put(kv[0], kv[1]);
            }
          }
          System.out.println(paramMap);

          if(path.equals("/")) {
            welcomeHandler.service(paramMap, printWriter);
          }
          else if(path.equals("/board/list")) {
            boardHandler.list(paramMap, printWriter);
          }
          else if(path.equals("/board/detail")) {
            boardHandler.detail(paramMap, printWriter);
          }
          else {
            errorHandler.error(paramMap, printWriter);
          }

          bytes  = strWriter.toString().getBytes("UTF-8");
        } catch(Exception e) {
          bytes = "요청 처리 중 오류 발생!".getBytes("UTF-8");
          e.printStackTrace(); // 서버 콘솔창에 오류에 대한 자세한 내용을 출력한다. -> 클라이언트에게 전송
        } //try(){}catch{}

        // 보내는 콘텐트의 MIME 타입이 무엇인지 응답 헤더를 추가로 설정한다.
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream out = exchange.getResponseBody();
        out.write(bytes);

        out.close();
      }

    }

    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/",  new MyHttpHandler());
    server.setExecutor(null); 
    server.start(); 
    System.out.println("서버 시작!");
  }

}
