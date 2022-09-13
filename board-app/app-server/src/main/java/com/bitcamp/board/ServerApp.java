package com.bitcamp.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;
import com.bitcamp.board.handler.BoardHandler;
import com.bitcamp.board.handler.MemberHandler;
import com.bitcamp.handler.Handler;

// ServerApp class - 메인 메뉴 선택에 따라 핸들러를 실행하여 클라이언트에게 하위 메뉴를 출력한다.
//      - Handler Interface 변경

public class ServerApp {

  //breadcrumb 메뉴를 저장할 스택을 준비
  public static Stack<String> breadcrumbMenu = new Stack<>();
  //메인 메뉴 목록 준비
  static String[] menus = {"게시판","회원"};

  public static void main(String[] args) {
    try(ServerSocket serverSocket = new ServerSocket(8888)){
      System.out.println("서버 실행중 ...");

      // 핸들러를 담을 컬렉션을 준비한다.
      ArrayList<Handler> handlers = new ArrayList<>();
      handlers.add(new BoardHandler(null));
      handlers.add(new MemberHandler(null));

      while(true) {
        Socket socket = serverSocket.accept();

        new Thread(() -> { // Runnable interface 익명 클래스
          // 스레드를 시작하는 순간 별도의 실행 흐름에서 병행으로 실행된다.
          try(
              DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // 실제 client에게 출력하기 위해서는 이 out을 이용해야한다.
              DataInputStream in = new DataInputStream(socket.getInputStream())
              ){
            System.out.println("클라이언트 접속!");

            // 접속 후 환영 메시지와 메인 메뉴를 출력한다.
            try(
                StringWriter strOut = new StringWriter(); // 여기에 buffer로 다 쌓여있다가 밑에 toString()으로 다 쏟아낸다.
                PrintWriter tempOut = new PrintWriter(strOut)
                ) // try()
            {
              welcome(tempOut);
              printMainMenus(tempOut); // main menu 출력
              out.writeUTF(strOut.toString());  // client로 전송
            } //try(){}

            while(true) {
              // 클라이언트가 보낸 요청을 읽는다.
              String request = in.readUTF();
              if(request.equals("quit")) break;

              // 클라이언트에게 응답한다.
              try( // 응답 내용을 출력할 임시 출력 스트림 준비 
                  StringWriter strOut = new StringWriter();
                  PrintWriter tempOut = new PrintWriter(strOut)
                  ) // try()
              {
                try {
                  int mainMenuNo = Integer.parseInt(request);
                  if (mainMenuNo >= 1 && mainMenuNo <= menus.length) { // 메뉴 번호가 유효한 경우
                    // 메뉴 번호로 Handler  객체를 찾아 실행한다.
                    handlers.get(mainMenuNo-1).execute();

                  }else {
                    tempOut.println("해당 번호의 메뉴가 없습니다!");
                  }
                }catch(Exception e) {
                  tempOut.println("입력값이 옳지 않습니다. \n알맞은 숫자값을 입력해주십시오.");
                } // try{}catch{}

                // 클라이언트에게 전송
                tempOut.println();
                printMainMenus(tempOut);
                out.writeUTF(strOut.toString());
              }
            }

            System.out.println("클라이언트와 접속 종료!");
          } catch (Exception e) {
            System.out.println("클라이언트와 통신하는 중 오류 발생!");
            e.printStackTrace();
          } // Socket.accept() try(){}
        }).start(); // Thread()
      } // while()

      //      System.out.println("서버 종료!");

    }catch (Exception e) {
      System.out.println("서버 실행 중 오류 발생!");
      e.printStackTrace();
    } // ServerSocket try(){}

  } // main()






  /*public static void main2(String[] args) {
      try (
          // DAO가 사용할 커넥션 객체 준비
          Connection con = DriverManager.getConnection(
              "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
          )
      {
        System.out.println("[게시글 관리 클라이언트]");

        welcome();

        // DAO 객체를 준비한다.
        MariaDBMemberDao memberDao = new MariaDBMemberDao(con);
        MariaDBBoardDao boardDao = new MariaDBBoardDao(con);



        // "메인" 메뉴의 이름을 스택에 등록한다.
        breadcrumbMenu.push("메인");



        loop: 
          while (true) {

            // 메인 메뉴 출력
            printTitle();

            printMenus(menus);

            System.out.println();

            try {




              // 메뉴에 진입할 때 breadcrumb 메뉴바에 그 메뉴를 등록한다.
              breadcrumbMenu.push(menus[mainMenuNo - 1]);



              breadcrumbMenu.pop();

            } catch (Exception ex) {
              System.out.println("입력 값이 옳지 않습니다.");
            }


          } // while

        Prompt.close();

        System.out.println("종료!\n");
      }catch (Exception e) {
        System.out.println("시스템 오류 발생!");
        e.printStackTrace();
      }
    }//main()*/


  static void welcome(PrintWriter out) throws Exception { 
    out.println("[게시판 애플리케이션]");
    out.println();
    out.println( "환영합니다!");
    out.println();
  }

  static void printMainMenus(PrintWriter out) {
    // 메뉴 목록 출력
    for (int i = 0; i < menus.length; i++) {
      out.printf("  %d: %s\n", i + 1, menus[i]);
    }

    // 메뉴 번호 입력을 요구하는 문장 출력
    out.printf("메뉴를 선택하세요[1..%d](quit: 종료) ", menus.length);
  }

  protected static void printTitle() {
    StringBuilder builder = new StringBuilder();
    for(String title: breadcrumbMenu) {
      if(!builder.isEmpty()) {
        builder.append(" > ");
      }
      builder.append(title);
    }
    System.out.printf("%s:\n", builder.toString());
  }
}
