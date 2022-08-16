package com.bitcamp.board;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("[게시글 데이터 관리 서버]");

    try {
      // 네트워크 준비
      // => 클라이언트 연결을 관리할 객체를 준비한다.
      ServerSocket serverSocket = new ServerSocket(8888);
      System.out.println("서버 소켓 준비 완료!");

      // 클라이언트의 연결을 기다림
      // => 클라이언트와 연결되면 그 클라이언트와 통신할 준비를 한다.
      // 즉, Socket 객체 리턴
      Socket socket = serverSocket.accept();
      System.out.println(" 클라이언트와 연결 되었음!");

      // 클라이언트와 연결된 것을 끊는다.
      // => 클라이언트와 연결될 때까지 리턴하지 않는다.
      socket.close();
      System.out.println("클라이언트와 연결 되었음!");

      // 네트워크 종료
      // => 더 이상 클라이언트와 연결하고 싶지 않다면 네트워크를 종료한다.
      serverSocket.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }//try-catch()
    System.out.println("서버 종료!");
  }//main()

}
