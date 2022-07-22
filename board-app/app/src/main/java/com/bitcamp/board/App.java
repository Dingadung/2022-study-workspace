/*
 * 寃뚯떆�뙋 愿�由� �븷�뵆由ъ��씠�뀡 鍮꾪듃罹좏봽-20220704
 */
package com.bitcamp.board;

public class App {


  public static void main(String[] args) {
    Print.welcome();
    BoardHandler boardHandler = new BoardHandler("게시판");
    BoardHandler readingHandler = new BoardHandler("독서록");
    BoardHandler visitingHandler = new BoardHandler("방명록");
    BoardHandler noticeHandler = new BoardHandler("공지사항");
    BoardHandler diaryHandler = new BoardHandler("일기장");
    loop: while (true) {
      //硫붿씤 硫붾돱 異쒕젰
      System.out.println();
      System.out.println("메뉴: ");
      System.out.printf("  1: %s\n", boardHandler.title);
      System.out.printf("  2: %s\n", readingHandler.title);
      System.out.printf("  3: %s\n", visitingHandler.title);
      System.out.printf("  4: %s\n", noticeHandler.title);
      System.out.printf("  5: %s\n", diaryHandler.title);
      int mainMenuNo = Prompt.inputInt("메뉴 선택[1..4] (0: 종료) ");
      System.out.println();
      switch (mainMenuNo) {
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
          break;
        case 4: // 怨듭��궗�빆
          noticeHandler.execute();
          break;
        case 5: // �씪湲곗옣
          diaryHandler.execute();
          break;
        default:
          Print.wrongMessage();
      }//switch �걹
    } // 硫붿씤硫붾돱 while臾� �걹 -----------------------------------------------
    Prompt.close();

  } // main �걹 ---------------------------------------
}