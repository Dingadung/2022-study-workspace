package com.bitcamp.board;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import com.bitcamp.board.servlet.BoardServlet;
import com.bitcamp.board.servlet.MemberServlet;
import com.bitcamp.servlet.Servlet;

public class ServerApp {
  public static void main(String[] args) {
    System.out.println("[게시글 데이터 관리 서버]");

    try (ServerSocket serverSocket = new ServerSocket(8888);) {

      System.out.println("서버 소켓 준비 완료!");

      // 클라이언트 요청을 처리할 객체 준비
      Hashtable<String,Servlet> servletMap = new Hashtable<>();
      servletMap.put("board", new BoardServlet("board"));
      servletMap.put("reading", new BoardServlet("reading"));
      servletMap.put("visit", new BoardServlet("visit"));
      servletMap.put("notice", new BoardServlet("notice"));
      servletMap.put("daily", new BoardServlet("daily"));
      servletMap.put("member", new MemberServlet("member"));

      while (true) {
        Socket socket = serverSocket.accept();
        RequestThread t =new RequestThread(socket, servletMap);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } // 바깥 쪽 try 

    System.out.println("서버 종료!");
  }
}

