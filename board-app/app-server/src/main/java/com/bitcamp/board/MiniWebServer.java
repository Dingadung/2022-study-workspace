package com.bitcamp.board;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import com.bitcamp.board.handler.WelcomeHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

// MiniWebServer class - 메인화면을 출력하는 요청 처리 객체를 분리하기

public class MiniWebServer {

  public static void main(String[] args) throws Exception{
    class MyHttpHandler implements HttpHandler{


      @Override
      public void handle(HttpExchange exchange) throws IOException {
        System.out.println("클라이언트가 call함");

        WelcomeHandler welcomeHandler = new WelcomeHandler();

        byte[] bytes = null;

        try(StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter)){
          welcomeHandler.service(printWriter);
          bytes  = strWriter.toString().getBytes("UTF-8");
        }

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
