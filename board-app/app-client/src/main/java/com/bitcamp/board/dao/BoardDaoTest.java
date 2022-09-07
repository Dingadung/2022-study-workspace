package com.bitcamp.board.dao;

import java.util.List;
import com.bitcamp.board.domain.Board;

public class BoardDaoTest {

  public static void main(String[] args) throws Exception{
    // TODO Auto-generated method stub
    MariaDBBoardDao dao = new MariaDBBoardDao();

    // list Test
    List<Board> list = dao.findAll();
    for(Board m : list) {
      System.out.println(m);
    }
    System.out.println("-------------------------------------------------------------");

    // insert test
    //    Board board = new Board();
    //    board.title = "aaaa";
    //    board.content = "bbbb";
    //    board.memberNo = 2;
    //    dao.insert(board);

    // delete test
    //dao.delete(21);



    //    list = dao.findAll();
    //    for(Board m : list) {
    //      System.out.println(m);
    //    }
    System.out.println("-------------------------------------------------------------");

    Board board = new Board();
    board.no = 12;
    board.title = "xxx";
    board.content = "okokok";
    dao.update(board);

    Board board2= dao.findByNo(12);
    System.out.println(board2);



  }


}
