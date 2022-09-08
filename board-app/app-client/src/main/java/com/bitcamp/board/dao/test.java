package com.bitcamp.board.dao;

public class test {

  public static void main(String[] args) throws Exception{
    MariaDBBoardDao dao= new MariaDBBoardDao();

    long start = System.currentTimeMillis();
    for(int i=0;i<10000;i++) {
      dao.findAll();
    }
    long end = System.currentTimeMillis();
    System.out.println("findAll() 걸린시간: "+(end-start));

    System.out.println("-------------------------------------------------------------------");

    start = System.currentTimeMillis();
    for(int i=0;i<10000;i++) { // connection을 공유하지 않는 경우
      dao.findAll2();
    }
    end = System.currentTimeMillis();
    System.out.println("findAll2() 걸린시간: "+(end-start));
  }

}
