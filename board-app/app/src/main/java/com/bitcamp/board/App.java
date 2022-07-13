/*
 * 게시판 관리 애플리케이션
 * 비트캠프-20220704
 */
package com.bitcamp.board;

public class App {

  public static void main(String[] args) {
    System.out.println("[게시판 애플리케이션]");
    System.out.println();
    System.out.println("환영합니다!");

    java.util.Scanner keyboardInput = new java.util.Scanner(System.in);

    final int SIZE = 3;
    int[] no = new int[SIZE];
    String[] title = new String[SIZE];
    String[] content = new String[SIZE];
    String[] writer = new String[SIZE];
    String[] password = new String[SIZE];
    int[] viewCount = new int[SIZE];
    long[] createdDate = new long[SIZE];

    int boardCount = 0; // 저장된 게시글의 개수

    while (true) {
      System.out.println();
      System.out.println("메뉴: ");
      System.out.println("  1: 게시글 목록");
      System.out.println("  2: 게시글 상세보기");
      System.out.println("  3: 게시글 등록");
      System.out.println();
      System.out.print("메뉴를 선택하세요[1..3] (0: 종료) ");
      int menuNo = keyboardInput.nextInt();
      keyboardInput.nextLine();

      System.out.println(
        "------------------------------------------------------"
      );

      if (menuNo == 0) {
        System.out.println("안녕히 가세요!");
        break;
      } else if (menuNo == 1) {
        System.out.println("[게시글 목록]");
        java.text.SimpleDateFormat formatter = 
          new java.text.SimpleDateFormat("yyyy-MM-dd");
        
        
        System.out.println("번호\t제목\t\t조회수\t작성자\t등록일");
        for(int i=0;i<boardCount;i++){
          java.util.Date date = new java.util.Date(createdDate[i]);
        String dateStr = formatter.format(date); 
          System.out.printf(
            "%d\t%s\t\t%d\t%s\t%s\n",
            no[i],
            title[i],
            viewCount[i],
            writer[i],
            dateStr
          );
        }
        
        System.out.println(
          "------------------------------------------------------"
        );
        System.out.println();
      } else if (menuNo == 2) {
        System.out.println("게시판 상세보기");
        System.out.println("몇 번 게시물을 조회하시겠습까? ");
        String noStr = keyboardInput.nextLine();
        int noBoard = Integer.parseInt(noStr);
        int idx = -1;
        for (int i = 0; i < boardCount; i++) {
          if (no[i] == noBoard) {
            idx = i;
          }
        }
        if (idx == -1) {
          System.out.println("해당 번호의 게시물은 존재하지 않습니다!");
          continue;
        }

        viewCount[idx]++;

        System.out.printf("번호: %d\n", no[idx]);
        System.out.printf("제목: %s\n", title[idx]);
        System.out.printf("내용: %s\n", content[idx]);
        System.out.printf("조회수: %d\n", viewCount[idx]);
        System.out.printf("작성자: %s\n", writer[idx]);

        java.util.Date date = new java.util.Date(createdDate[idx]);
        System.out.printf(
          "등록일: %1$tY-%1$tm-%1$td/%1$tA %1$tH:%1$tM\n",
          date
        );
        System.out.println(
          "------------------------------------------------------"
        );
        System.out.println();
      } else if (menuNo == 3) {
        System.out.println("[게시글 등록]");

        if (SIZE == boardCount) {
          System.out.println(
            "더 이상 게시글을 등록할 수 없습니다. 최대 게시물 등록 수를 초과했습니다!"
          );
          continue;
        }
        System.out.print("제목? ");
        title[boardCount] = keyboardInput.nextLine();
        System.out.print("내용? ");
        content[boardCount] = keyboardInput.nextLine();
        System.out.print("작성자? ");
        writer[boardCount] = keyboardInput.nextLine();
        System.out.print("암호? ");
        password[boardCount] = keyboardInput.nextLine();
        no[boardCount] = boardCount == 0 ? 1 : no[boardCount - 1] + 1;
        createdDate[boardCount] = System.currentTimeMillis();
        viewCount[boardCount]=0;
        boardCount++;
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다.");
        System.out.println(
          "------------------------------------------------------"
        );
        System.out.println();
      }
    } //while문 끝

    keyboardInput.close();
  }
}
