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

    int no = 0;
    String title1 = " ", title2 = " ", title3 = " ";
    String content1 = " ", content2 = " ", content3 = " ";
    String writer1 = " ", writer2 = " ", writer3 = " ";
    String password1 = " ", password2 = " ", password3 = " ";
    int viewCount1 = 0, viewCount2 = 0, viewCount3 = 0;
    long createdDate1 = 0, createdDate2 = 0, createdDate3 = 0;

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

        System.out.println("번호\t제목\t\t조회수\t작성자\t등록일");
        System.out.print(1);
        System.out.print("\t");
        System.out.print("제목입니다1");
        System.out.print('\t');
        System.out.print(20 + "\t");
        System.out.print("홍길동\t");
        System.out.print("2022-07-08\r\n");
        System.out.print(
          2 +
          "\t" +
          "제목입니다2" +
          '\t' +
          11 +
          "\t" +
          "홍길동\t" +
          "2022-07-08\n"
        );
        System.out.println(3 + "\t제목입니다3\t" + 99 + "\t임꺽정\t2022-07-08");
        System.out.printf(
          "%d\t%s\t%d\t%s\t%s\n",
          4,
          "제목입니다4",
          12,
          "유관순",
          "2022-07-08"
        );
        System.out.println(
          "------------------------------------------------------"
        );
        System.out.println();
      } else if (menuNo == 2) {
        viewCount++;

        System.out.println("게시판 상세보기");

        if(boardCount==0){
          System.out.printf("번호: %d\n", no1);
          System.out.printf("제목: %s\n", title1);
          System.out.printf("내용: %s\n", content1);
          System.out.printf("조회수: %d\n", viewCount1);
          System.out.printf("작성자: %s\n", writer1);
        }
        

        java.util.Date date = new java.util.Date(createdDate);
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

        if (boardCount == 0) {
          System.out.print("제목? ");
          title1 = keyboardInput.nextLine();
          System.out.print("내용? ");
          content1 = keyboardInput.nextLine();
          System.out.print("작성자? ");
          writer1 = keyboardInput.nextLine();
          System.out.print("암호? ");
          password1 = keyboardInput.nextLine();
        }else if(boardCount==1){
          System.out.print("제목? ");
          title2 = keyboardInput.nextLine();
          System.out.print("내용? ");
          content2 = keyboardInput.nextLine();
          System.out.print("작성자? ");
          writer2 = keyboardInput.nextLine();
          System.out.print("암호? ");
          password2 = keyboardInput.nextLine();
        }else if(boardCount==2){
          System.out.print("제목? ");
          title3 = keyboardInput.nextLine();
          System.out.print("내용? ");
          content3 = keyboardInput.nextLine();
          System.out.print("작성자? ");
          writer3 = keyboardInput.nextLine();
          System.out.print("암호? ");
          password3 = keyboardInput.nextLine();
        }else if(boardCount>2){
          System.out.println("게시글을 더 이상 저장할 수 없습니다.");
        }

        boardCount++;
        no = 1;
        createdDate = System.currentTimeMillis();
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
