package com.bitcamp.board;

import java.net.Socket;
import java.util.Map;
import com.bitcamp.servlet.Servlet;

// 클라이언트 요청을 main 실행 흐름과 분리하여 별도의 실행으로 다루는 클래스
public class RequestThread extends Thread{
  private Socket socket;
  private Map<String, Servlet> servletMap;
  public RequestThread(Socket socket, Map<String, Servlet> servletMap) {
    this.socket = socket;
    this.servletMap = servletMap;
  }

  // 별도의 실행흐름에서 수행할 작업 정의
}
