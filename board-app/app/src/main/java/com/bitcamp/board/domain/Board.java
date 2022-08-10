package com.bitcamp.board.domain;

import java.io.Serializable;

//java.io.Seroalizable 인터페이스
//  - 인스턴스를 통째로 입출력 할 수 있도록표시하는 용도이다.
// - 인터페이스에 추상 메서드가 선언되어 있지 않기 때문에 따로 메서드를 구현할 필요는 없다.
public class Board implements Serializable{ 
  // java.io.Serializable interface 구현
  // - Board class 필드 값을 통쨰로 입출력할 수 있다고 설정하는 용도
  // 즉, Board 클래스의 필드 값을 통째로 입출력할 수 있다고 설정하는 용도로
  // java.io.Serializable 인터페이스를 구현한다.(비어있는 인터페이스로, 표시해두는 용도로  구현된다.)
  // 인스턴스를 저장할 때 클래스의 변화 여부를 검증하기 위해
  // 이 클래스에 대해 임의의 버전을 지정한다.
  private static final long serialVersionUID = 1L;
  // serialVersionUID  필드 설정
  // - 인스턴스를 저장하고 읽을 때 클래스의 변화 여부를 검증하기 위해 버전 번호를 지정한다.
  // - 처음 버전 번호를부여한 후 클래스의 필드를 추가하거나 삭제할 때마다 버전번호를 증가시킨다.

  // 인스턴스를 생성할 때 준비되는 메모리를 선언
  public int no;
  public String title;
  public String content;
  public String writer;
  public String password;
  public int viewCount;
  public long createdDate;
  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
        + ", password=" + password + ", viewCount=" + viewCount 
        + ", createdDate=" + new java.sql.Date(createdDate)
        + "]";
  }

  public static Board create(String csv) { // 인스턴스를 리턴해야하므로 static으로 선언한다.
    String[] values = csv.split(",");

    Board board = new Board();
    board.no = Integer.parseInt(values[0]);
    board.title = values[1];
    board.content = values[2];
    board.writer = values[3];
    board.password = values[4];
    board.viewCount = Integer.parseInt(values[5]);
    board.createdDate = Long.parseLong(values[6]);

    return board;
  }
}