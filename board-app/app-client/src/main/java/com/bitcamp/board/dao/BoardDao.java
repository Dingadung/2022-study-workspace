package com.bitcamp.board.dao;

import java.util.List;
import com.bitcamp.board.domain.Board;

// interface 생성 -> method signature만 남기도 다 지우기
public interface BoardDao  {
  int insert(Board board)throws Exception ;

  int update(Board board) throws Exception;

  Board findByNo(int no)  throws Exception;


  int delete(int no) throws Exception;

  List<Board> findAll() throws Exception;

}














