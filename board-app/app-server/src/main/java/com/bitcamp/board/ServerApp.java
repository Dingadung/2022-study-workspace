package com.bitcamp.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import com.bitcamp.board.servlet.BoardServlet;
import com.bitcamp.servlet.Servlet;

public class ServerApp {

  public static void main(String[] args) {
    System.out.println("[게시글 데이터 관리 서버]");

    try (
        ServerSocket serverSocket = new ServerSocket(8888);)
    {
      System.out.println("서버 소켓 준비 완료!");

      try(
          Socket socket = serverSocket.accept(); 


          DataInputStream in = new DataInputStream( socket.getInputStream()); 

          DataOutputStream out = new DataOutputStream(socket.getOutputStream());
          )//try()
      {
        System.out.println(" 클라이언트와 연결 되었음!");

        // 클라이언트 요청을 처리할 객체 준비
        Hashtable<String, Servlet> servletMap = new Hashtable<>();
        servletMap.put("board", new BoardServlet("board"));
        servletMap.put("reading", new BoardServlet("reading"));
        servletMap.put("visit", new BoardServlet("visit"));
        servletMap.put("notice", new BoardServlet("notice"));
        servletMap.put("daily", new BoardServlet("daily"));
        servletMap.put("member", new BoardServlet("member"));

        while(true) {
          String dataName = in.readUTF(); 
          // 명령 읽기

          if(dataName.equals("exit")) { 
            break;
          }

          Servlet servlet = servletMap.get(dataName);
          if(servlet != null) {
            servlet.service(in, out);
          } else {
            out.writeUTF("fail");
            //          }
            //          switch(dataName) {
            //            case "board":  boardServlet.service(in, out); break;
            //            case "reading": readingServlet.service(in, out); break;
            //            case "visit": visitServlet.service(in, out); break;
            //            case "notice": noticeServlet.service(in, out); break;
            //            case "daily": dailyServlet.service(in, out); break;
            //            case "member":memberServlet.service(in, out);break;
            //            default:
            //              out.writeUTF("fail");
            //          }
          }


          System.out.println("클라이언트와 연결을 끊었음!");
        }// 안쪽 try

      }catch (Exception e) {
        e.printStackTrace();
      }// 바깥쪽 try-catch()

      System.out.println("서버 종료!");
    }//main()
  }

