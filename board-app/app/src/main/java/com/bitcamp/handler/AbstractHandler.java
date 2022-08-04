package com.bitcamp.handler;

import com.bitcamp.board.App;
import com.bitcamp.util.Prompt;

// Handler 규격에 맞추어 핸들러의 서브 클래스에게 물려줄 공통 필드가 메서드를 구현한다.
public abstract class AbstractHandler implements Handler {

  // 같은 타입의 (board, member)핸들러가 사용할 메뉴 목록을 담을 배열을 준비한다.
  // => 메뉴 목록은 생성자를 통해 초기화시킨다.
  // 
  private String [] menus ;

  // 반드시 메뉴 목록을 초기화 시키도록 강제하기 위해
  // 기본 생성자 대신  메뉴 목록을 배열로 받는  생성자를 준비한다.
  public AbstractHandler(String[] menus) {
    this.menus = menus;
  }

  // static이 아니라, 인스터스 멤버를 사용하려고 인스턴스 메서드로 변경   
  // 다음 메서드는 execute()에서 메뉴를 출력할 때 사용된다.
  // 다만 서브 클래스에서 출력 형식을 바꾸기 위해 오버라이딩 할 수 있도록
  // 접근 범위를 protected로 설정한다. -> 공개하는 메서드는 아니지만, 서브 클래스 중에서 재정의 기회를 주고 싶을 때 사용
  protected void printMenus() {
    for(int i=0;i<menus.length; i++) {
      System.out.printf("  %d: %s\n", i + 1, menus[i]);
    }
    printBlankLine();
  }

  protected static void printHeadline() {
    System.out.println("=========================================");
  }

  protected static void printBlankLine() {
    System.out.println(); // 메뉴를 처리한 후 빈 줄 출력
  }

  protected static void printTitle() {
    System.out.printf("%s:\n", App.breadcrumbMenu);    
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    while (true) {
      printTitle();
      printMenus();

      try {
        int menuNo = Prompt.inputInt(String.format(" 메뉴를 선택하세요[1..%d](0: 이전) ", menus.length));

        if(menuNo > 0 && menuNo <= menus.length) {
          //메뉴에 진입할 때 breadCrumb메뉴바에 그 메뉴를 등록한다.
          App.breadcrumbMenu.push(menus[menuNo-1]);
        } else if(menuNo == 0){
          return; // 메인 메뉴로 돌아간다.
        }else {
          System.out.println("메뉴 번호가 옳지 않습니다!");
          continue; // while문의 조건 검사로 보낸다.
        }

        printHeadline();

        // 서브 메뉴의 제목을 출력한다.
        printTitle();

        // 사용자가 입력한 메뉴 번호에 대해 작업을 수행한다! 원래 switch 문이었던 것
        service(menuNo);

        printBlankLine();
        App.breadcrumbMenu.pop();
      } catch (Exception ex) {
        System.out.printf("예외 발생: %s\n", ex.getMessage());
      }
    } // while
  }

  // 서브 클래스가 반드시 만들어야 할 메서드
  // => 메뉴 번호를 받으면 그 메뉴에 해당하는 작업을수행한다.
  // 서브 클래스에게 구현을 강제하기 위해 추상 메서드로 선언한다. => 추상 메서드의 목표!
  public abstract void service(int menuNo);
}