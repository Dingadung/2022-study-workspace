package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import com.bitcamp.board.domain.Member;

public class MemberDaoProxyTest {

  public static void main(String[] args) throws Exception {
    try(Socket socket = new Socket("127.0.0.1", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      MemberDaoProxy memberDao = new MemberDaoProxy("member", in, out);

      // Test1 목록 가져오기
      Member[] members =  memberDao.findAll();
      for(Member b : members) {
        System.out.println(b);
      }
      System.out.println("--------------------------------------------------");

      // Test2 상세 데이터 가져오기
      Member member = memberDao.findByEmail("aa@test.com");
      System.out.println(member);
      System.out.println("--------------------------------------------------");

      //      // Test3 데이터 등록하기
      member = new Member();
      member.name ="A";
      member.email = "A@test.com";
      member.password = "1111";
      member.createdDate = System.currentTimeMillis();

      System.out.println(memberDao.insert(member));
      System.out.println("--------------------------------------------------");
      members =  memberDao.findAll();
      for(Member b : members) {
        System.out.println(b);
      }
      System.out.println("--------------------------------------------------");
      //
      //      // Test4 데이터 변경하기
      member = memberDao.findByEmail("aa@test.com");
      member.name = "jim";
      System.out.println(memberDao.update(member));    
      System.out.println("--------------------------------------------------");
      // 변경된 게시물 확인
      member = memberDao.findByEmail("aa@test.com");
      System.out.println(member);
      System.out.println("--------------------------------------------------");

      //Test5 데이터 삭제하기
      System.out.println(memberDao.delete("A@test.com"));
      System.out.println("--------------------------------------------------");
      // delete() 잘작동 되는지 목록 확인
      members =  memberDao.findAll();
      for(Member b : members) {
        System.out.println(b);
      }
      System.out.println("--------------------------------------------------");


      out.writeUTF("exit");
    }
  }

}
