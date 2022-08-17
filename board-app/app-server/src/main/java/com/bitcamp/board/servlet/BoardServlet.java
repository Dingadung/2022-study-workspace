/*
 * board 데이터 처리
 */
package com.bitcamp.board.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;
import com.bitcamp.servlet.Servlet;
import com.google.gson.Gson;

public class BoardServlet implements Servlet{


  // 게시글 목록을 관리할 객체 준비
  private BoardDao boardDao;
  private String fileName;

  public BoardServlet(String dataName){
    fileName = dataName + ".json";
    boardDao = new BoardDao(fileName); 

    try{
      boardDao.load();
    }catch(Exception e) {
      System.out.printf("%s 파일 로딩 중 오류 발생!\n", fileName);
      e.printStackTrace();
    }
  }

  @Override
  public void service(DataInputStream in, DataOutputStream out) {
    try {
      String command = in.readUTF();

      // 여러 군데에서 사용하기 위해 여기서 선언
      Board board = null; 
      int no = 0;
      String json = null;

      switch (command) {
        case "findAll": 
          Board[] boards = boardDao.findAll();
          out.writeUTF(SUCCESS); // client에게 응답
          out.writeUTF(new Gson().toJson(boards)); //client에게 전송
          break;

        case "findByNumber":
          no = in.readInt(); // client가 보낸 데이터 추가로 읽기
          board = boardDao.findByNo(no);
          if(board != null) {
            out.writeUTF(SUCCESS);
            out.writeUTF(new Gson().toJson(board));
          }else {
            out.writeUTF(FAIL);
          }
          break;

        case "insert": 
          json = in.readUTF(); // json 형식의 문자열 읽기
          board = new Gson().fromJson(json, Board.class); // 객체의 타입정보 알려주기
          boardDao.insert(board);
          out.writeUTF(SUCCESS);
          break;

        case "update":
          json = in.readUTF(); // json 형식의 문자열 읽기
          board = new Gson().fromJson(json, Board.class); // 객체의 타입정보 알려주기
          if(boardDao.update(board)) {
            out.writeUTF(SUCCESS);
          }else {
            out.writeUTF(FAIL);
          }
          break;

        case "delete": 
          no = in.readInt(); // client가 보낸 데이터 추가로 읽기
          if(boardDao.delete(no)) {
            out.writeUTF(SUCCESS);
          } else {
            out.writeUTF(FAIL);
          }
          break;

        default:
          out.writeUTF(FAIL);
      }
    }catch(Exception e) {
      throw new RuntimeException(e); 
    }//switch
  }// service


}




