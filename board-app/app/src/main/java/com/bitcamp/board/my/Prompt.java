/*
 * �궎蹂대뱶 �엯�젰�쓣 諛쏅뒗 �룄援щ�� 援щ퉬�븯怨� �엳�떎.
 */
package com.bitcamp.board.my;

public class Prompt {

  //-------------------蹂��닔 �꽑�뼵-----------------------\\
  static java.util.Scanner keyboardInput = new java.util.Scanner(System.in);
  //-------------------蹂��닔 �꽑�뼵-----------------------\\

  static int inputInt() {
    /*int value = keyboardInput.nextInt();
    keyboardInput.nextLine();
    return value;*/
    String str = keyboardInput.nextLine();
    return Integer.parseInt(str);
  }

  static int inputInt(String title) {
    System.out.print(title);
    String str = keyboardInput.nextLine();
    return Integer.parseInt(str);
  }

  static String inputString() {
    return keyboardInput.nextLine();
  }

  static String inputString(String title) {
    System.out.print(title);
    return keyboardInput.nextLine();
  }

  static char inputChar(String title) {
    System.out.print(title);
    char ans = keyboardInput.next().charAt(0);
    keyboardInput.nextLine();
    return ans;
  }


  // �겢�옒�뒪 �븞�뿉 close媛� �엳�쑝硫� �씠 �겢�옒�뒪�뒗 close�븷 �븘�슂媛� �엳�쓬�쓣 �븣 �닔 �엳�떎.
  static void close() {
    keyboardInput.close();
  }
}
