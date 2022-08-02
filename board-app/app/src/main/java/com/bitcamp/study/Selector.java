package com.bitcamp.study;

import java.util.ArrayList;
import java.util.Scanner;

public class Selector {
  public static void main(String[] args) {

    String[] names = {
        "조주연","김라윤","최지연","이헌식","조영아","안치운",
        "장현경","박병민","유제동","김봉경","최종찬","장슬기",
        "김원태","유성민","문채원","유종현","박지민","신우섭",
        "주  영","황의찬","임은지","임동현","김민구",
        "박진우","임가온","김연정","최유민","윤주현"
    };

    ArrayList<String> list = new ArrayList<>();

    for (String name : names) {
      list.add(name);
    }

    for (int i = 0; i < 100; i++) {
      int i1 = (int)(Math.random() * list.size());
      int i2 = (int)(Math.random() * list.size());

      String temp = list.get(i1);
      list.set(i1, list.get(i2));
      list.set(i2, temp);
    }

    Scanner keyboardInput = new Scanner(System.in);

    System.out.println("준비!!!");
    keyboardInput.nextLine();

    while (list.size() > 0) {
      int no = (int)(Math.random() * list.size());
      System.out.println("===> " + list.remove(no));
      keyboardInput.nextLine();
    }

    keyboardInput.close();
  }
}

//   ------------  칠 판  --------------->    문    <-----
//      1       2       3             4       5       6
//1:[임동현][조영아][유성민]      [주  영][안치운][황의찬]
//2:[장슬기][이헌식][최지연]      [김봉경][최종찬][      ]
//3:[박지민][조주연][유종현]      [박병민][김원태][      ]
//4:[임은지][김라윤][문채원]      [장현경][유제동][신우섭]
//5:[윤주현][최유민][김연정]      [임가온][김민구][박진우]

