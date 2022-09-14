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

  protected void printMenus(DataOutputStream out) throws Exception {
    try (StringWriter strOut = new StringWriter();
        PrintWriter tempOut = new PrintWriter(strOut)) {

      tempOut.println(BreadCrumb.getBreadCrumbOfCurrentThread().toString());
      for (int i = 0; i < menus.length; i++) {
        tempOut.printf("  %d: %s\n", i + 1, menus[i]);
      }

      tempOut.printf("메뉴를 선택하세요[1..%d](0: 이전) ", menus.length);

      out.writeUTF(strOut.toString());
    }
  }
  protected static void printHeadline(PrintWriter out) {
    out.println("=========================================");
  }

  protected static void printBlankLine(PrintWriter out) {
    out.println(); // 메뉴를 처리한 후 빈 줄 출력
  }

  static void error(DataOutputStream out, Exception e) {
    try (StringWriter strOut = new StringWriter();
        PrintWriter tempOut = new PrintWriter(strOut)) {
      tempOut.printf("실행 오류:%s\n", e.getMessage());
      out.writeUTF(strOut.toString());
    } catch (Exception e2) {
      e2.printStackTrace();
    }
  }


  @Override
  public void execute(DataInputStream in, DataOutputStream out) throws Exception {
    printMenus(out);
    // 현재 스레드를 위해 보관된 BreadCrumb 객체를 꺼낸다.
    BreadCrumb breadCrumb = BreadCrumb.getBreadCrumbOfCurrentThread();

    String message = null;

    while (true) {
      String request = in.readUTF();
      if(request.equals("0")) break;
      else if(request.equals("menu")) {
        printMenus(out);
        continue;
      }
      try {
        int menuNo =Integer.parseInt(request);
        if (menuNo < 0 || menuNo > menus.length) {
          throw new Exception("메뉴 번호가 옳지 않습니다.");
        } 
        // 메뉴에 진입할 때 breadCrumb 메뉴바에 그 메뉴를 등록한다.
        BreadCrumb.getBreadCrumbOfCurrentThread().put(menus[menuNo - 1]);

        service(menuNo, in, out);

        // 메뉴에서 나올 때 breadCrumb 메뉴바에서 그 메뉴를 제거한다.
        BreadCrumb.getBreadCrumbOfCurrentThread().pickUp();
      }catch(Exception e) {
        error(out, e);
      }//try{}catch{}
    } // while
  }//execute()

  public abstract void service(int menuNo, DataInputStream in, DataOutputStream out);
}






