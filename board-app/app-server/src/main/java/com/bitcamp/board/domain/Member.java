package com.bitcamp.board.domain;

import java.io.Serializable;

public class Member implements Serializable{
  private static final long serialVersionUID = 1L;
  public int no;
  public String name;
  public String email;
  public String password;
  public long createdDate;

  //GoF의 Factory Method 패턴
  // - 객체의 생성 과정이 복잡할 때 별도의 메서드로 캡슐화 한다.
  public static Member create(String csv) { // 인스턴스를 리턴해야하므로 static으로 선언한다.
    String[] values = csv.split(",");

    Member member = new Member();
    member.no = Integer.parseInt(values[0]);
    member.name = values[1];
    member.email = values[2];
    member.password = values[3];
    member.createdDate = Long.parseLong(values[4]);

    return member;
  }

  // 인스턴스 필드를 사용해야 하는 메서드이므로  논스태틱으로 선언한다.
  // GRASP 패턴의 InformationExpert 패턴.
  // => 정보 생성은 그 데이터를 가지고 있는 전문가에게 맡긴다.
  public  String toCsv() {
    return String.format("%d,%s,%s,%s,%d",
        this.no,
        this.name,
        this.email,
        this.password,
        this.createdDate);
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "Board [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", createdDate=" + new java.sql.Date(createdDate)
        + "]";
  }
}
