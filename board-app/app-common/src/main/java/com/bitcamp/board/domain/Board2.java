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

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }



}