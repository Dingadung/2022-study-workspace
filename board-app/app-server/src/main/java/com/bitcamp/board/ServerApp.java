package com.bitcamp.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import com.bitcamp.board.servlet.BoardServlet;
import com.bitcamp.board.servlet.MemberServlet;
import com.bitcamp.servlet.Servlet;

public class ServerApp {

  public static void main(String[] args) {

    // 클라이언트 요청을 처리할 객체 준비
    Hashtable<String,Servlet> servletMap = new Hashtable<>();
    servletMap.put("board", new BoardServlet("board"));
    servletMap.put("reading", new BoardServlet("reading"));
    servletMap.put("visit", new BoardServlet("visit"));
    servletMap.put("notice", new BoardServlet("notice"));
    servletMap.put("daily", new BoardServlet("daily"));
    servletMap.put("member", new MemberServlet("member"));

    System.out.println("[게시글 데이터 관리 서버]");

    try (ServerSocket serverSocket = new ServerSocket(8888);) {

      System.out.println("서버 소켓 준비 완료!");

      while (true) {
        // 여러 클라이언트의 요청을 동시에 처리하기 위해서 클라이언트가 연결되면,
        //람다 문법에서는 인스턴스 필드는 처리할 수 없다. 따라서, 다시 로컬변수로 전환한다.
        Socket socket = serverSocket.accept();  // 생성자가 생성될 때 함께 실행된다.,  클라이언트가 들어올때까지 넘어가지 않는다. blocking method

        new Thread( () -> {
          try (Socket socket2 = socket; // 위에 있는데 또 선언하는 이유: () 하는 곳 안에서만 자동으로 Close 되기 때문.
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
            System.out.println("클라이언트 요청 중 오류 발생!");
            e.printStackTrace();
          }
        } 
            ).start(); 
      }
    } catch (Exception e) {
      e.printStackTrace();
    } // 바깥 쪽 try 

    System.out.println("서버 종료!");
  }

}

