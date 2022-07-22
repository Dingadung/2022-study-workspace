/*
 * �Խñ� �޴� ó�� Ŭ����
 */
package com.bitcamp.board; // ��Ű���� �׻� ��� �ڵ�� �ֻ����� �����ؾ��Ѵ�.

import java.text.SimpleDateFormat;
import java.util.Date; 

public class BoardHandler {
  //-------------------���� ����-----------------------\\//
  // �� �Խ����� ������ �����ؾ� �� �����ʹ� �ν��Ͻ� ������ �����Ѵ�.
  // ��? �ν��Ͻ� ������, �Խ��� ���� ������ �� �ֱ� �����̴�.
  String title;

  // �Խñ� ����� ������ ��ü �غ�
  BoardList boardList = new BoardList();
  //-------------------���� ����-----------------------\\


  //-------------------������ ����-----------------------\\
  public BoardHandler() {

  }
  //������ �Է� �޴� ������
  BoardHandler(String title) {
    this.title=title;
  }
  //-------------------������ ����-----------------------\\

  void execute() {
    while(true) {
      // �Խ��� �޴� ���
      System.out.println();
      System.out.printf("%s : \n", this.title);
      System.out.println("  1: ���");
      System.out.println("  2: �󼼺���");
      System.out.println("  3: ���");
      System.out.println("  4: ����");
      System.out.println("  5: ����");
      System.out.println();
      int menuNo = Prompt.inputInt("�޴��� �����ϼ���[1..5] (0: ����) ");
      displayHeadLine('-');

      // �ٸ� �ν��Ͻ� �޼��带 ȣ���� �� this�� ������ �ν��Ͻ� �ּҸ� ����Ѵ�.
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
          //�޴� ����
          this.onDelete();
          break;
        case 5:
          //�Խñ� ����
          this.onUpdate();
          break;
        default:
          Print.wrongMessage();
      }
      displayBlankLine();
    }//�Խ��� while
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
    //�ν��Ͻ� �޼���� ȣ��� �� �Ѱ� ���� �ν��Ͻ� �ּҸ� this��� ���� ������ �����Ѵ�.

    //------------------- menu 1 --------------------------
    SimpleDateFormat formatter = new SimpleDateFormat(
        "yyyy-MM-dd hh:mm:ss"
        );

    System.out.printf("[%s ���]\n", this.title);
    System.out.println("��ȣ\t����\t\t��ȸ��\t�ۼ���\t�����");


    // boardList �ν��Ͻ��� ��� �ִ� ������ ����� �����´�.
    Board[] list = this.boardList.toArray();
    for (Board board : list) { // �� �����Ͱ� ��� �ִ� ���� ����
      // ������ ÷���� ������ �ݺ��Ҷ��� ������ ���� for�������ϱ�
      Date date = new Date(board.createdDate);
      String dateStr = formatter.format(date);
      System.out.printf(
          "%d\t%s\t\t%d\t%s\t%s\n",
          board.no,
          board.title,
          board.viewCount,
          board.writer,
          dateStr
          );
    }
  }

  void onDetail() {
    //------------------- menu 2 --------------------------
    System.out.printf("[%s �󼼺���]\n", this.title);
    int noBoard = Prompt.inputInt("�� �� �Խù��� ��ȸ�Ͻðڽ���? ");

    // �ش� ��ȣ�� �Խñ��� �� �� �迭�� ��� �ִ��� �˾Ƴ���
    Board board = this.boardList.get(noBoard);

    if (board == null) {
      System.out.println("�ش� ��ȣ�� �Խù��� �������� �ʽ��ϴ�!");
      return; // �޼ҵ带 ȣ���� ������ ���ư��� ���� ��. 
    }

    board.viewCount++;

    System.out.printf("��ȣ: %d\n", board.no);
    System.out.printf("����: %s\n", board.title);
    System.out.printf("����: %s\n", board.content);
    System.out.printf("��ȸ��: %d\n", board.viewCount);
    System.out.printf("�ۼ���: %s\n", board.writer);

    java.util.Date date = new java.util.Date(board.createdDate);
    System.out.printf("�����: %1$tY-%1$tm-%1$td/%1$tA %1$tH:%1$tM\n", date);
  }

  void onInput() {
    //------------------- menu 3 --------------------------
    System.out.printf("[%s ���]\n", this.title);

    Board board = new Board();
    board.title = Prompt.inputString("����? ");
    board.content = Prompt.inputString("����? ");
    board.writer= Prompt.inputString("�ۼ���? ");
    board.password = Prompt.inputString("��ȣ? ");
    board.createdDate = System.currentTimeMillis();
    board.viewCount = 0;
    // ���θ��� �ν��Ͻ� �ּҸ� ���۷��� �迭�� �����Ѵ�.
    this.boardList.add(board);

    System.out.println("�Խñ��� ���������� ����߽��ϴ�.");
  }

  void onDelete() {
    //------------------- menu 4 --------------------------
    System.out.printf("[%s ����]\n", this.title);
    if(boardList.boardCount ==0) {
      System.out.println("���� �����ϴ� �Խñ��� �����ϴ�!");
      return;
    }
    int deleteNo = Prompt.inputInt("�����Ͻð� ���� �Խñ��� ��ȣ�� �Է��� �ֽñ� �ٶ��ϴ�.");
    if(!boardList.remove(deleteNo)) {
      System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�. "
          + "�ش� �Խñ� ��ȣ�� �������� �ʽ��ϴ�."
          + "\n�ùٸ� ��ȣ�� �Է��� �ֽʽÿ�.");
      return;
    }else {
      System.out.println("�����Ͽ����ϴ�!");
    }
  }

  void onUpdate() {
    //------------------- menu 5 --------------------------
    System.out.printf("[%s ����]\n", this.title);
    if(boardList.boardCount ==0) {
      System.out.println("���� �����ϴ� �Խñ��� �����ϴ�!");
      return;
    }
    int editNo = Prompt.inputInt("������ �Խñ� ��ȣ?");
    Board board = this.boardList.get(editNo);
    if(board == null) {
      System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�. �ش� �Խñ� ��ȣ�� �������� �ʽ��ϴ�.\n�ùٸ� ��ȣ�� �Է��� �ֽʽÿ�.");
      return;
    }else {
      edit(board);
      this.onList();
      System.out.println(board.title + board.content + board.writer);
    }
  }

  void edit(Board board) {
    String title= Prompt.inputString("����? " + "("+board.title+")");
    String content= Prompt.inputString("����? "+ "("+board.content+")");
    String writer = Prompt.inputString("�ۼ���? "+ "("+board.writer+")");
    String password = Prompt.inputString("��ȣ? "+ "("+board.password+")");
    if(isEdit()) {
      board = makeBoard(title, content, writer, password);
     System.out.println("���������� ����Ǿ����ϴ�!");
    }else {
      System.out.println("������ ����Ͽ����ϴ�.");
      return;
    }
  }
  static boolean isEdit() {
    char ans = Prompt.inputChar("�����Ͻðڽ��ϱ�?(y/n) ");
    if(ans=='y')return true;
    else if(ans=='n') return false;
    return ans =='y'? true:false;
  }
  Board makeBoard(String title, String content, String writer, String pwd) {
    Board board2 = new Board();
    board2.title =title;
    board2.content =content;
    board2.writer=writer;
    board2.password = pwd;
    
    return board2;
  }
}