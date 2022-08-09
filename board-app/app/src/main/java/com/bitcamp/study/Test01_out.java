// FileOutputStream: 문자열출력
package com.bitcamp.study;

import java.io.FileOutputStream;
import com.bitcamp.board.domain.Board;

public class Test01_out {

  public static void main(String[] args) throws Exception{ // FIleOutputStream의 사용법을 배우는 것이 목적이지, 오류 처리하는 것이 목적이 아니기 때문이다.
    // TODO Auto-generated method stub
    FileOutputStream out = new FileOutputStream("test8.data");

    DataOutputStream out2 = new DataOutputStream(out) ;   

    Board board = new Board();
    board.no = 100;
    board.title = "제목입니다.";
    board.content = "내용입니다.";
    board.writer = "hong@test.com";
    board.password = "1111";
    board.viewCount = 11;
    board.createdDate = System.currentTimeMillis();

    out2.writeInt(board.no);
    out2.writeUTF(board.content);
    out2.writeUTF(board.writer);
    out2.writeUTF(board.password);
    out2.writeUTF(board.viewCount);
    out2.writeUTF(board.createdDate);
    out2.close();
    out.close();

    System.out.println("finish!");
  }

}
