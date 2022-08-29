package com.bitcamp.board.domain;

import java.io.Serializable;

public class Board2 implements Serializable{ 
  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String content;
  private String writer;
  private String password;
  private int viewCount;
  private long createdDate;

  static int count = 0;

  public Board2(String title) { // 생성자의 목적: 파라미터가 null 이 될 수 없도록 설정
    if(title == null) {
      throw new RuntimeException("제목이 비어 있습니다.");
    }
    this.no = ++count;
    this.viewCount = 0;
    this.createdDate = System.currentTimeMillis();
  }

  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
        + ", password=" + password + ", viewCount=" + viewCount 
        + ", createdDate=" + new java.sql.Date(createdDate)
        + "]";
  }

}