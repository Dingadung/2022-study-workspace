/*
 * 회원 메뉴 처리 클래스
 */
package com.bitcamp.board.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.domain.Member;
import com.bitcamp.servlet.Servlet;
import com.google.gson.Gson;

public class MemberServlet implements Servlet {

  private MemberDao memberDao;
  private String fileName;

  public MemberServlet(String dataName) {
    fileName = dataName + ".json";
    memberDao = new MemberDao(fileName);
    try {
      memberDao.load();
    }catch(Exception e) {
      System.out.printf("%s 파일 로딩 중 오류 발생!\n", fileName);
      e.printStackTrace();
    }
  }

  @Override
  public void service(DataInputStream in, DataOutputStream out){
    try {
      String command = in.readUTF();

      // 여러 군데에서 사용하기 위해 여기서 선언
      Member member = null; 
      String email = null;
      String json = null;

      switch (command) {
        case "findAll": 
          Member[] members = memberDao.findAll();
          out.writeUTF(SUCCESS); // client에게 응답
          out.writeUTF(new Gson().toJson(members)); //client에게 전송
          break;

        case "findByEmail":
          email = in.readUTF(); // client가 보낸 데이터 추가로 읽기
          member = memberDao.findByEmail(email);
          if(member != null) {
            out.writeUTF(SUCCESS);
            out.writeUTF(new Gson().toJson(member));
          }else {
            out.writeUTF(FAIL);
          }
          break;

        case "insert": 
          json = in.readUTF(); // json 형식의 문자열 읽기
          member = new Gson().fromJson(json, Member.class); // 객체의 타입정보 알려주기
          memberDao.insert(member);
          memberDao.save();
          out.writeUTF(SUCCESS);
          break;

        case "update":
          json = in.readUTF(); // json 형식의 문자열 읽기
          member = new Gson().fromJson(json, Member.class); // 객체의 타입정보 알려주기
          if(memberDao.update(member)) {
            memberDao.save();
            out.writeUTF(SUCCESS);
          }else {
            out.writeUTF(FAIL);
          }
          break;

        case "delete": 
          email = in.readUTF(); // client가 보낸 데이터 추가로 읽기
          if(memberDao.delete(email)) {
            memberDao.save();
            out.writeUTF(SUCCESS);
          } else {
            out.writeUTF(FAIL);
          }
          break;
        default:
          out.writeUTF(FAIL);
      }//switch
    }catch(Exception e) {
      throw new RuntimeException(e); 
    }//try-catch

  }

}





