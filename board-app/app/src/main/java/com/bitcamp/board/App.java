/*
<<<<<<< HEAD
 * 寃뚯떆�뙋 愿�由� �븷�뵆由ъ��씠�뀡 鍮꾪듃罹좏봽-20220704
=======
 * 게시판 관리 애플리케이션
 * 비트캠프-20220704
>>>>>>> d63849e30df5b0d4b67c5188546521a49eeca2db
 */
package com.bitcamp.board;

import com.bitcamp.board.handler.BoardHandler;
import com.bitcamp.board.handler.MemberHandler;
import com.bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) {
<<<<<<< HEAD
    Print.welcome();
=======
    welcome();

    // 인스턴스를 생성할 때 생성자가 원하는 값을 반드시 줘야 한다.
    // 주지 않으면 컴파일 오류이다!
    //
>>>>>>> d63849e30df5b0d4b67c5188546521a49eeca2db
    BoardHandler boardHandler = new BoardHandler("게시판");
    BoardHandler readingHandler = new BoardHandler("독서록");
    BoardHandler visitHandler = new BoardHandler("방명록");
    BoardHandler noticeHandler = new BoardHandler("공지사항");
    BoardHandler diaryHandler = new BoardHandler("일기장");
    MemberHandler memberHandler = new MemberHandler();

    loop: while (true) {
<<<<<<< HEAD
      //硫붿씤 硫붾돱 異쒕젰
      System.out.println();
      System.out.println("메뉴: ");
      System.out.printf("  1: %s\n", boardHandler.title);
      System.out.printf("  2: %s\n", readingHandler.title);
      System.out.printf("  3: %s\n", visitingHandler.title);
      System.out.printf("  4: %s\n", noticeHandler.title);
      System.out.printf("  5: %s\n", diaryHandler.title);
      int mainMenuNo = Prompt.inputInt("메뉴 선택[1..4] (0: 종료) ");
=======

      // 메인 메뉴 출력
      System.out.println("메뉴:");
      System.out.println("  1: 게시판");
      System.out.println("  2: 독서록");
      System.out.println("  3: 방명록");
      System.out.println("  4: 공지사항");
      System.out.println("  5: 일기장");
      System.out.println("  6: 회원");
>>>>>>> d63849e30df5b0d4b67c5188546521a49eeca2db
      System.out.println();
      int mainMenuNo = Prompt.inputInt("메뉴를 선택하세요[1..6](0: 종료) ");

      switch (mainMenuNo) {
<<<<<<< HEAD
        case 0:
          Print.bye();
          break loop;
        case 1: // 寃뚯떆�뙋
          boardHandler.execute();
          break; // 硫붿씤 硫붾돱 �솕硫댁쑝濡� �룎�븘媛�湲�
        case 2: // �룆�꽌濡�
          readingHandler.execute();
          break;
        case 3: // 諛⑸챸濡�
          visitingHandler.execute();
=======
        case 0: break loop;
        case 1: // 게시판
          boardHandler.execute();
          break;
        case 2: // 독서록
          readingHandler.execute();
          break;
        case 3: // 방명록
          visitHandler.execute();
>>>>>>> d63849e30df5b0d4b67c5188546521a49eeca2db
          break;
        case 4: // 怨듭��궗�빆
          noticeHandler.execute();
          break;
        case 5: // �씪湲곗옣
          diaryHandler.execute();
          break;
<<<<<<< HEAD
        default:
          Print.wrongMessage();
      }//switch �걹
    } // 硫붿씤硫붾돱 while臾� �걹 -----------------------------------------------
    Prompt.close();

  } // main �걹 ---------------------------------------
}
=======
        case 6: // 회원
          memberHandler.execute();
          break;
        default: System.out.println("메뉴 번호가 옳지 않습니다!");
      } // switch


    } // while

    System.out.println("안녕히 가세요!");
    Prompt.close();
  } // main

  static void welcome() {
    System.out.println("[게시판 애플리케이션]");
    System.out.println();
    System.out.println("환영합니다!");
    System.out.println();
  }
}







>>>>>>> d63849e30df5b0d4b67c5188546521a49eeca2db
