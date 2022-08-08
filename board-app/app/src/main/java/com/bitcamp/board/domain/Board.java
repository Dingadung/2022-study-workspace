package com.bitcamp.board.domain;

public class Board {
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
  } // 오른ㄷ쪽 마우스 키, gernerate, toString()
  // 중간중간 domain의 변수의 값들을 확인하곳 ㅣㅍ을 때 toString을오버라딩한다.


}
