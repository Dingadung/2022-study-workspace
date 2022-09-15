package com.bitcamp.board;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MiniWebServer {

  public static void main(String[] args) throws Exception{
    class MyHttpHandler implements HttpHandler{

      @Override
      public void handle(HttpExchange exchange) throws IOException {

      }

    }

    HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
    server.createContext("/",  new MyHttpHandler());
    server.setExecutor(null); //  HttpServer에 기본으로 설정되어 있는 Executor 사용
    // Executor란? 멀티 스레딩을 수행하는 객체이다.
    // Runnable 객체를 실행 한다. -> thread를 다룬다. 
    server.start(); // HttpServer를 시작시킨 후 즉시 리턴한다.
    System.out.println("서버 시작!");
    // main() 메서드 호출이 끝났다 하더라도, 
    // 내부에서 생성한 스레드(HttpServer)가 종료되지 않으면, JVM도 종료되지 않는다.
  }

}
