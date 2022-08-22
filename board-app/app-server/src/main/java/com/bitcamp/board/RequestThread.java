package com.bitcamp.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
  @Override
  public void run() {
    try (Socket socket = this.socket; // 위에 있는데 또 선언하는 이유: () 하는 곳 안에서만 자동으로 Close 되기 때문.
        // socekt을 가지고 입출력 stream 얻기
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {

      System.out.println("클라이언트와 연결 되었음!");

      // 클라이언트와 서버 사이에 정해진 규칙(protocol)에 따라 데이터를 주고 받는다.
      String dataName = in.readUTF();

      Servlet servlet = servletMap.get(dataName);
      if (servlet != null) {
        servlet.service(in, out);
      } else {
        out.writeUTF("fail");
      }

      System.out.println("클라이언트와 연결을 끊었음!");
    } // 안쪽 try
    catch(Exception e) {
      System.out.println("클라이언트 여청 중 오류 발생!");
      e.printStackTrace();
    }
    super.run();
  }//run()

}
