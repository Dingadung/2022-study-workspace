package com.bitcamp.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.bitcamp.util.BreadCrumb;

// Handler 규격에 맞춰 서브 클래스에게 물려줄 공통 필드나 메서드를 구현한다.
// 
public abstract class AbstractHandler implements Handler {

  // 핸들러가 사용할 메뉴 목록을 담을 배열을 준비한다.
  // => 메뉴 목록은 생성자를 통해 초기화시킨다.
  //
  private String[] menus;

  // 반드시 메뉴 목록을 초기화시키도록 강제하기 위해 
  // 기본 생성자 대신 메뉴 목록을 배열로 받는 생성자를 준비한다.
  public AbstractHandler(String[] menus) {
    this.menus = menus;
  }

  protected void printMenus(PrintWriter out) {
    for (int i = 0; i < menus.length; i++) {
      out.printf("  %d: %s\n", i + 1, menus[i]);
    }

    out.printf("메뉴를 선택하세요[1..%d](0: 이전) ", menus.length);
  }

  protected static void printHeadline(PrintWriter out) {
    out.println("=========================================");
  }

  protected static void printBlankLine(PrintWriter out) {
    out.println(); // 메뉴를 처리한 후 빈 줄 출력
  }

  @Override
  public void execute(DataInputStream in, DataOutputStream out) throws Exception {
    // 현재 스레드를 위해 보관된 BreadCrumb 객체를 꺼낸다.
    BreadCrumb breadCrumb = BreadCrumb.getBreadCrumbOfCurrentThread();

    String message = null;
    while (true) {
      // 핸들러의 메뉴를 클라이언트에게 보낸다.
      try(StringWriter strOut = new StringWriter();
          PrintWriter tempOut = new PrintWriter(strOut)
          ){

        if(message != null) {
          tempOut.println(message);
          message = null;
        }
        tempOut.println();
        tempOut.println(breadCrumb.toString());
        printMenus(tempOut);
        out.writeUTF(strOut.toString());
      }

      // 클라이언트가 보낸 요청을 읽는다.
      String request = in.readUTF();
      if(request.equals("0")) break;

      // 클라이언트가 선택한 메뉴를 처리한다.
      int menuNo =Integer.parseInt(request);

      if (menuNo < 0 || menuNo > menus.length) {
        message = "메뉴 번호가 옳지 않습니다!";
        continue; // while 문의 조건 검사로 보낸다.
      } 


      message = "해당 메뉴를 준비 중 입니다.";








      /*
      try {




        // 메뉴에 진입할 때 breadcrumb 메뉴바에 그 메뉴를 등록한다.
        ServerApp.breadcrumbMenu.push(menus[menuNo - 1]);

        printHeadline(out);

        // 서브 메뉴의 제목을 출력한다.
        printTitle(out);

        // 사용자가 입력한 메뉴 번호에 대해 작업을 수행한다.
        service(menuNo);

        printBlankLine(out);

        ServerApp.breadcrumbMenu.pop();

      } catch (Exception ex) {
        out.printf("예외 발생: %s\n", ex.getMessage());
        ex.printStackTrace();
      }*/
    } // while

  }

  public abstract void service(int menuNo);
}






