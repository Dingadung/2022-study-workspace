/*
 * board 데이터 처리
 */
package com.bitcamp.board.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.servlet.Servlet;

public class BoardServlet implements Servlet{

  // 게시글 목록을 관리할 객체 준비
  private BoardDao boardDao;

  public BoardServlet(String dataName){
    //    boardDao = new BoardDao(dataName); // 객체 생성 따로, 로딩 따로!  생성자에서 예외를 던지는 것은 조심해야한다.
    //    // 생성자에서 객체가 저장되지도 못하고 null인상태로 남을 수 있기 때문이다.!
    //    try{
    //      boardDao.load();
    //    }catch(Exception e) {
    //      System.out.printf("%s 파일 로딩 중 오류 발생!\n", e.getMessage());
    //      // e.printStackTrace();
    //    }
  }

  @Override
  public void service(DataInputStream in, DataOutputStream out) { //상위 클래스에서 service메소드가 예외를 던지지 않았기 때문에 여기서도 직접적으로 던질 수는 없지만,
    // 예외를 상위 호출자에게 보내고 싶으면 RuntimeException으로 던질  수 있다!!!
    try {

      String command = in.readUTF();

      switch (command) {
        case "findAll": 
          out.writeUTF("success");
          break;
        case "findByNumber":
          out.writeUTF("success");
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




