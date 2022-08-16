package com.bitcamp.board;

import java.net.ServerSocket;

public class ServerApp {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("[게시글 데이터 관리 서버]");

    try {
      // 네트워크 준비
      ServerSocket serverSocket = new ServerSocket(8888);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }//try-catch()
    System.out.println("서버 종료!");
  }//main()

}
