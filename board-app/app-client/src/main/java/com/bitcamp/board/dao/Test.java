package com.bitcamp.board.dao;

import java.util.List;
import com.bitcamp.board.domain.Member;

public class Test {

  public static void main(String[] args) throws Exception{
    // TODO Auto-generated method stub
    MariaDBMemberDao dao = new MariaDBMemberDao();

    // list Test
    List<Member> list = dao.findAll();
    for(Member m : list) {
      System.out.println(m);
    }

    //    Member member = new Member();
    //    member.name = "Park";
    //    member.email = "jimin@test.com";
    //    member.password = "1111";
    //    dao.insert(member);


    System.out.println("-------------------------------------------------------------");
    //    list = dao.findAll();
    //    for(Member m : list) {
    //      System.out.println(m);
    //    }
    System.out.println("-------------------------------------------------------------");


    //    Member member = new Member();
    //    member.no = 1;
    //    member.name = "xxx";
    //    member.email = "xxx@test.com";
    //    member.password = "2222";
    //    dao.update(member);
    dao.delete(6);
    Member member2= dao.findByNo(6);
    System.out.println(member2);



  }


}
