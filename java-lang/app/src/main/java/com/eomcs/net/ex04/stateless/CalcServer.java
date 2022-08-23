// stateless 방식 - 계산기 서버 만들기
// 가장치명적인 문제? 그 다음 클라이언트가 요청했을 떄 그 이전에 요청했던 클라이언트인지 알아보는 것이 어렵다. 즉 기록을 남기기가어렵다.
// 네이버나 이런브라우저도 stateless인데 로그인을 통해 그저 내가 누군지 알려줘서 내 정보에 관련된 정보를 돌려주는 것이다.
package com.eomcs.net.ex04.stateless;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer {
  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행 중...");

    ServerSocket ss = new ServerSocket(8888);

    while (true) {
      Socket socket = ss.accept();
      System.out.println("클라이언트 요청 처리!");
      try {
        processRequest(socket);
      } catch (Exception e) {
        System.out.println("클라이언트 요청 처리 중 오류 발생!");
        System.out.println("다음 클라이언트의 요청을 처리합니다.");
      }
    }
    // ss.close();
  }

  static void processRequest(Socket socket) throws Exception {
    try (Socket socket2 = socket; // 여기서 socket2로 해도 밑에서 socket으로 그냥 사용해도 된다 -> 주소가 같으므로 상관 노
        DataInputStream in = new DataInputStream(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream());) {

      int a = in.readInt();
      String op = in.readUTF();
      int b = in.readInt();
      int result = 0;

      switch (op) {
        case "+":
          result = a + b;
          break;
        case "-":
          result = a - b;
          break;
        case "*":
          result = a * b;
          break;
        case "/":
          result = a / b;
          break;
        default:
          out.println("해당 연산을 지원하지 않습니다.");
          return;
      }
      out.printf("%d %s %d = %d\n", a, op, b, result);
    }
  }
}


