package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import com.bitcamp.board.domain.Board;

public class BoardDaoProxyTest {

  public static void main(String[] args) throws Exception {
    try(Socket socket = new Socket("127.0.0.1", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      BoardDaoProxy boardDao = new BoardDaoProxy("board", in, out);

      // Test1 목록 가져오기
      Board[] boards =  boardDao.findAll();
      for(Board b : boards) {
        System.out.println(b);
      }
      System.out.println("--------------------------------------------------");

      // Test2 상세 데이터 가져오기
      Board board = boardDao.findByNo(5);
      System.out.println(board);
      System.out.println("--------------------------------------------------");

      // Test3 데이터 등록하기
      board = new Board();
      board.title ="Xxxxx";
      board.content = "xxxxxxxxxxxxxx";
      board.viewCount = 111;
      board.createdDate = System.currentTimeMillis();
      board.writer = "PArk";
      board.password = "1111";

      System.out.println(boardDao.insert(board));
      System.out.println("--------------------------------------------------");
      // insert() 잘작동 되는지 목록 확인
      boards =  boardDao.findAll();
      for(Board b : boards) {
        System.out.println(b);
      }
      System.out.println("--------------------------------------------------");


      // Test4 데이터 변경하기
      board = boardDao.findByNo(3);
      board.title = "okokok";
      board.content ="jimijijmi";
      System.out.println(boardDao.update(board));    
      System.out.println("--------------------------------------------------");
      // 변경된 게시물 확인
      board = boardDao.findByNo(3);
      System.out.println(board);
      System.out.println("--------------------------------------------------");

      //Test5 데이터 삭제하기
      System.out.println(boardDao.delete(6));
      System.out.println("--------------------------------------------------");
      // delete() 잘작동 되는지 목록 확인
      boards =  boardDao.findAll();
      for(Board b : boards) {
        System.out.println(b);
      }
      System.out.println("--------------------------------------------------");


      out.writeUTF("exit");
    }
  }

}