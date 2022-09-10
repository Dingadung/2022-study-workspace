package com.bitcamp.handler;

import com.bitcamp.board.ClientApp;
import com.bitcamp.util.Prompt;

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

  protected void printMenus() {
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("  %d: %s\n", i + 1, menus[i]);
    }
  }

  protected static void printHeadline() {
    System.out.println("=========================================");
  }

  protected static void printBlankLine() {
    System.out.println(); // 메뉴를 처리한 후 빈 줄 출력
  }

  protected static void printTitle() {
    StringBuilder builder = new StringBuilder();
    for(String title: ClientApp.breadcrumbMenu) {
      if(!builder.isEmpty()) {
        builder.append(" > ");
      }
      builder.append(title);
    }
    System.out.printf("%s:\n", builder.toString());
  }

  @Override
  public void execute() {
    while (true) {
      printTitle();
      printMenus();
      printBlankLine();

      try {
        int menuNo = Prompt.inputInt(String.format(
            "메뉴를 선택하세요[1..%d](0: 이전) ", menus.length));

        if (menuNo < 0 || menuNo > menus.length) {
          System.out.println("메뉴 번호가 옳지 않습니다!");
          continue; // while 문의 조건 검사로 보낸다.

        } else if (menuNo == 0) {
          return; // 메인 메뉴로 돌아간다.
        }

        // 메뉴에 진입할 때 breadcrumb 메뉴바에 그 메뉴를 등록한다.
        ClientApp.breadcrumbMenu.push(menus[menuNo - 1]);

        printHeadline();

        // 서브 메뉴의 제목을 출력한다.
        printTitle();

        // 사용자가 입력한 메뉴 번호에 대해 작업을 수행한다.
        service(menuNo);

        printBlankLine();

        ClientApp.breadcrumbMenu.pop();

      } catch (Exception ex) {
        System.out.printf("예외 발생: %s\n", ex.getMessage());
        ex.printStackTrace();
      }
    } // while

  }

  public abstract void service(int menuNo);
}






