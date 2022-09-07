package com.bitcamp.board.domain;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{
  private static final long serialVersionUID = 1L;
  public int no;
  public String name;
  public String email;
  public String password;
  public Date createdDate;

  @Override
  public String toString() {
    return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
        + ", createdDate=" + createdDate + "]";
  }

}
