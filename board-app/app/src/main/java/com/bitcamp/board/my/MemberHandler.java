/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board.my; // 패키지는 항상 모든 코드들 최상위에 존재해야한다.

import java.text.SimpleDateFormat;
import java.util.Date; 

public class MemberHandler {
  //-------------------변수 선언-----------------------\\//
  // 각 게시판이 별도로 관리해야 할 데이터는 인스턴스 변수에 저장한다.
  // 왜? 인스턴스 변수는, 게시판 별로 생성할 수 있기 때문이다.
  String title; // 일기장

  // 게시글 목록을 관리할 객체 준비
  MemberList memberList = new MemberList();
  //-------------------변수 선언-----------------------\\


  //-------------------생성자 선언-----------------------\\
  public MemberHandler() {

  }
  //제목을 입력 받는 생성자
  MemberHandler(String title) {
    this.title=title;
  }
  //-------------------생성자 선언-----------------------\\

  void execute() {
    while(true) {
      // 게시판 메뉴 출력
      System.out.println();
      System.out.printf("%s : \n", this.title);
      System.out.println("  1: 목록");
      System.out.println("  2: 상세보기");
      System.out.println("  3: 등록");
      System.out.println("  4: 삭제");
      System.out.println("  5: 변경");
      System.out.println();
      int menuNo = Prompt.inputInt("메뉴를 선택하세요[1..5] (0: 이전) ");
      displayHeadLine('-');

      // 다른 인스턴스 메서드를 호출할 때 this에 보관된 인스턴스 주소를 사용한다.
      switch (menuNo) {
        case 0:
          Print.bye();
          return;
        case 1:
          this.onList();
          break;
        case 2:
          this.onDetail();
          break;
        case 3:
          this.onInput();
          break;
        case 4:
          //메뉴 삭제
          this.onDelete();
          break;
        case 5:
          //게시글 변경
          this.onUpdate();
          break;
        default:
          Print.wrongMessage();
      }
      displayBlankLine();
    }//게시판 while
  }


  static void displayHeadLine(char c) {
    for (int i = 0; i < 45; i++) {
      System.out.print(c);
    }
    System.out.println();
  }

  static void displayBlankLine() {
    displayHeadLine('=');
    System.out.println();
  }


  /////////////////////////////////////////////////


  void onList() {
    //인스턴스 메서드는 호출될 때 넘겨 받은 인스턴스 주소를 this라는 내장 변수에 보관한다.

    //------------------- menu 1 --------------------------
    SimpleDateFormat formatter = new SimpleDateFormat(
        "yyyy-MM-dd hh:mm:ss"
        );

    System.out.printf("[%s 목록]\n", this.title);
    System.out.println("번호\t이름\t이메일\t\t등록일");


    // boardList 인스턴스에 들어 있는 데이터 목록을 가져온다.
    Member[] list = this.memberList.toArray();
    for (Member member : list) { // 딱 데이터가 들어 있는 값만 존재
      // 데이터 첨부터 끝까지 반복할때는 위에와 같이 for문선언하기
      Date date = new Date(member.createdDate);
      String dateStr = formatter.format(date);
      System.out.printf(
          "%d\t%s\t%s\t%s\n",
          member.no,
          member.name,
          member.email,
          dateStr
          );
    }
  }

  void onDetail() {
    //------------------- menu 2 --------------------------
    System.out.printf("[%s 상세보기]\n", this.title);
    int memberNo = Prompt.inputInt("몇 번 회원을 조회하시겠습까? ");

    // 해당 번호의 게시글이 몇 번 배열에 들어 있는지 알아내기
    Member member = this.memberList.get(memberNo);

    if (member == null) {
      System.out.println("해당 번호의 회원은 존재하지 않습니다!");
      return; // 메소드를 호출할 곳으로 돌아가고 싶을 때. 
    }

    member.viewCount++;

    System.out.printf("번호: %d\n", member.no);
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("이메일: %s\n", member.email);

    Date date = new Date(member.createdDate);
    System.out.printf("등록일: %1$tY-%1$tm-%1$td/%1$tA %1$tH:%1$tM\n", date);
  }

  void onInput() {
    //------------------- menu 3 --------------------------
    System.out.printf("[%s 등록]\n", this.title);

    Member member = new Member();
    member.name = Prompt.inputString("이름? ");
    member.email = Prompt.inputString("이메일? ");
    member.password = Prompt.inputString("암호? ");
    member.createdDate = System.currentTimeMillis();
    member.viewCount = 0;
    // 새로만든 인스턴스 주소를 레퍼런스 배열에 저장한다.
    this.memberList.add(member);

    System.out.println("회원을 성공적으로 등록했습니다.");
  }

  void onDelete() {
    //------------------- menu 4 --------------------------
    System.out.printf("[%s 삭제]\n", this.title);
    if(memberList.memberCount ==0) {
      System.out.println("현재 존재하는 회원이 없습니다!");
      return;
    }
    int deleteNo = Prompt.inputInt("삭제하시고 싶은 회원의 번호를 입력해 주시길 바랍니다.");
    if(!memberList.remove(deleteNo)) {
      System.out.println("잘못된 회원 번호를 입력하셨습니다. "
          + "해당 회원 번호는 존재하지 않습니다."
          + "\n올바른 번호를 입력해 주십시오.");
      return;
    }else {
      System.out.println("삭제하였습니다!");
    }
  }

  void onUpdate() {
    //------------------- menu 5 --------------------------
    System.out.printf("[%s 수정]\n", this.title);
    if(memberList.memberCount ==0) {
      System.out.println("현재 존재하는 회원이 없습니다!");
      return;
    }
    int editNo = Prompt.inputInt("변경할 회원 번호?");
    Member member = this.memberList.get(editNo);
    if(member == null) {
      System.out.println("잘못된 회원 번호를 입력하셨습니다. "
          + "해당 회원 번호는 존재하지 않습니다."
          + "\n올바른 번호를 입력해 주십시오.");
      return;
    }else {
      edit(member);
    }
  }
  void edit(Member member) {
    String name= Prompt.inputString("이름? " + "("+member.name+")");
    String email= Prompt.inputString("이메일? "+ "("+member.email+")");
    if(isEdit()) {
      makeMember(member, name, email);
      System.out.println("성공적으로 변경되었습니다!");
    }else {
      System.out.println("변경을 취소하였습니다.");
      return;
    }
  }
  static boolean isEdit() {
    char ans = Prompt.inputChar("변경하시겠습니까?(y/n) ");
    if(ans=='y')return true;
    else if(ans=='n') return false;
    return ans =='y'? true:false;
  }
  void makeMember(Member member, String name, String email) {
    member.name =name;
    member.email =email;
  }
}
