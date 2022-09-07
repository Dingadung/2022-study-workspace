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

  }

}
