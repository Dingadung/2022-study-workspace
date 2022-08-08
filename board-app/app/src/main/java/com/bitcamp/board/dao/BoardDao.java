package com.bitcamp.board.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Board;

// 게시글 목록을 관리하는 역할
//
public class BoardDao {

  List<Board> list = new LinkedList<>();
  private int boardNo = 0;

  String fileName;

  // 인스턴스 값을 유효한 값으로 초기화 시키기 위해 생성자를 사용한다.
  public  BoardDao(String fileName) throws Exception{ 
    this.fileName = fileName;
    load();
  }

  public void load() throws Exception{ // 두 오류 모두 넘긴다.
    FileInputStream in = new FileInputStream(fileName);
    // FileInputStream 도구를 사용하여 파일로부터 데이터를 읽어들인다.
    in.close();
  }

  public void save() throws Exception{ 
    FileOutputStream out = new FileOutputStream(fileName); // try-catch로 묶어주거나, 묶기 싫으면, 오류를 위임해버려 줘야 한다. 둘 중 하나는 해줘야 함.
    for (Board board :list) {
      // int => byte[] 로 변경하여 출력하기
      // 예: board.no = 0x12345678
      out.write(board.no >>> 24); //0x00000012|345678 / 0x12345678 
      out.write(board.no >>> 16); // 0x00001234|5678 /  0x12345678 
      out.write(board.no >>> 8); // 0x00123456|78 /  0x12345678 
      out.write(board.no); //  0x12345678 

      // String (UTF -16 )=> UTF -8
      out.write(board.title.getBytes("UTF-8")); // 무엇으로 인코딩할지 강제로 알려주기 -> UTF-8로 인코딩해라!!
      out.write(board.content.getBytes("UTF-8"));
      out.write(board.writer.getBytes("UTF-8"));
      out.write(board.password.getBytes("UTF-8"));

      // int ==> byte[]
      out.write(board.viewCount >> 24);
      out.write(board.viewCount >> 16);
      out.write(board.viewCount >> 8);
      out.write(board.viewCount);

      // long ==> byte[]
      out.write((int) (board.createdDate>> 56));
      out.write((int) (board.createdDate>> 48));
      out.write((int) (board.createdDate>> 40));
      out.write((int) (board.createdDate>> 32));
      out.write((int) (board.createdDate>> 24));
      out.write((int) (board.createdDate>> 16));
      out.write((int) (board.createdDate>> 8));
      out.write((int) (board.createdDate));



    }
    out.close();
  }

  public void insert(Board board) {
    board.no = nextNo();
    list.add(board);
  }

  public Board findByNo(int boardNo) {
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }

  public boolean delete(int boardNo) {
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return list.remove(i) != null;
      }
    }
    return false;
  }

  public Board[] findAll() {
    // 목록에서 값을 꺼내는 일을 할 객체를 준비한다.
    // Iterator는 값을 꺼내는 일을 한다!!!
    Iterator<Board> iterator = list.iterator();

    // 역순으로 정렬하여 리턴한다.
    Board[] arr = new Board[list.size()];

    int index = list.size() -1;
    while(iterator.hasNext()) {
      arr[index--] = iterator.next();
    }
    return arr;
  }

  private int nextNo() {
    return ++boardNo;
  }
}














