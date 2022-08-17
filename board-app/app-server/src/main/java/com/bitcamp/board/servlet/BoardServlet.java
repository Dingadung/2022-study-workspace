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
  public void service(DataInputStream in, DataOutputStream out) { //상위 클래스에서 service메소드가 예외를 던지지 않았기 때문에 여기서도 직접적으로 던질 수는 없지만,
    // 예외를 상위 호출자에게 보내고 싶으면 RuntimeException으로 던질  수 있다!!!
    try {

      String command = in.readUTF();

      switch (command) {
        case "findAll": 
          Board[] boards = boardDao.findAll();
          out.writeUTF("success"); // client에게 응답
          out.writeUTF(new Gson().toJson(boards)); //client에게 전송
          break;
        case "findByNumber":
          int no = in.readInt(); // client가 보낸 데이터 추가로 읽기
          Board board = boardDao.findByNo(no);
          if(board != null) {
            out.writeUTF("success");
            out.writeUTF(new Gson().toJson(board));
          }else {
            out.writeUTF("fail");
          }
          break;
        case "insert": 
          out.writeUTF("success");
          break;
        case "update":
          out.writeUTF("success");
          break;
        case "delete": 
          out.writeUTF("success");
          break;
        default:
          out.writeUTF("fail");
      }
    }catch(Exception e) {
      throw new RuntimeException(e); // 
    }
  }



}




