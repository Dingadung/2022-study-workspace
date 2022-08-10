package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
  public  BoardDao(String fileName){ 
    this.fileName = fileName;
  }

  public void load() throws Exception {
    try( DataInputStream in = new DataInputStream(new FileInputStream(fileName))){
      // => 먼저 게시글 개수를 읽는다.
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Board board = new Board();
        // 1) 게시글 번호 읽기
        board.no = in.readInt();
        // 2) 게시글 제목 읽기
        board.title =in.readUTF();
        // 3) 게시글 내용 읽기
        board.content = in.readUTF();
        // 4) 게시글 작성자 읽기
        board.writer =in.readUTF();

        // 5) 게시글 암호 읽기
        board.password =in.readUTF();

        // 6) 게시글 조회수 읽기
        board.viewCount = in.readInt();

        // 6) 게시글 등록일 읽기
        board.createdDate = in.readLong();

        // 게시글 데이터가 저장된 Board 객체를 목록에 추가한다.
        list.add(board);

        boardNo = board.no;
      }
    }
  }

  public void save() throws Exception{ 
    try(DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))){
      out.writeInt(list.size());
      for (Board board :list) {
        out.writeInt(board.no);
        out.writeUTF(board.title);
        out.writeUTF(board.content);
        out.writeUTF(board.writer);
        out.writeUTF(board.password);
        out.writeInt(board.viewCount);
        out.writeLong(board.createdDate);
      }
    }
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














