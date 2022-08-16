package com.bitcamp.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Stack;
import com.bitcamp.board.handler.BoardHandler;
import com.bitcamp.board.handler.MemberHandler;
import com.bitcamp.handler.Handler;

public class ClientApp {

  //breadcrumb 메뉴를 저장할 스택을 준비
  public static Stack<String> breadcrumbMenu = new Stack<>();

  public static void main(String[] args) {
    System.out.println("[게시글 관리 클라이언트]");

    // 네트워크 준비
    //=> 정상적으로 연결되었으면, Socket 객체를 리턴한다.
    try (Socket socket = new Socket("127.0.0.1", 8888 ); 
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());){// 같은 컴퓨터 내에 서버와 클라이언트가 존재하므로 정확한 IP address 대신, 127.0.0.1(local host)이 가능하다. (접속할 IP address, port num)

      System.out.println("연결되었음!");

      welcome();


      // 핸들러를 담을 레퍼런스 배열을 준비한다.
      Handler[] handlers = new Handler[] { // 파일명을 목적에 맞게 각각 전달
          new BoardHandler("board.json"), // 게시판
          new BoardHandler("reading.json"), // 독서록
          new BoardHandler("visit.json"), // 방명록
          new BoardHandler("notice.json"), // 공지사항
          new BoardHandler("daily.json"), // 일기장
          new MemberHandler("member.json") // 회원
      };

      // "메인" 메뉴의 이름을 스택에 등록한다.
      breadcrumbMenu.push("메인");

      // 통신 프로토콜에 따라 요청한다. 먼저 서버에 전송!
      out.writeUTF("board");
      out.writeUTF("insert");

      // 두개 전송후, 서버 응답할때까지 기다리기!
      // 통신 프로토콜에 따라 응답을 처리한다.
      String status = in.readUTF();
      System.out.println(status);

      // 네트워크 끊기
      // => 서버와 연결된 것을 끊는다.
      //socket.close();
      System.out.println("연결을 끊었음!");
    } catch (Exception e) {
      e.printStackTrace();
    }//try-catch()

    System.out.println("종료");
  }//main()


  static void welcome() {
    System.out.println("[게시판 애플리케이션]");
    System.out.println();
    System.out.println("환영합니다!");
    System.out.println();
  }

  static void printMenus(String[] menus) {
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("  %d: %s\n", i + 1, menus[i]);
    }
  }

  protected static void printTitle() {
    StringBuilder builder = new StringBuilder();
    for(String title:App.breadcrumbMenu) {
      if(!builder.isEmpty()) {
        builder.append(" > ");
      }
      builder.append(title);
    }
    System.out.printf("%s:\n", builder.toString());
  }
}
