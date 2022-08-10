package com.bitcamp.board.dao;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Member;
import com.bitcamp.util.DataInputStream;
import com.bitcamp.util.DataOutputStream;

// 회원 목록을 관리하는 역할
//
public class MemberDao {
  List<Member> list = new LinkedList<Member>();

  String fileName;
  public MemberDao(String fileName) {
    this.fileName = fileName;
  }//MemberDao{}

  public void load() throws Exception {
    try(DataInputStream in = new DataInputStream(new FileInputStream(fileName))){
      // => 먼저 회원 개수를 읽는다.
      int size = in.readInt();

      for (int i = 0; i < size; i++) {
        Member member = new Member();
        // => 저장된 순서로 데이터를 읽는다.
        // 1) 회원 번호 읽기

        member.no = in.readInt();

        // 2) 회원 이름 읽기
        member.name = in.readUTF();

        // 3) 회원 이메일 읽기
        member.email = in.readUTF();

        // 4) 회원 암호 읽기
        member.password =in.readUTF();

        // 5) 게시글 등록일 읽기
        member.createdDate = in.readLong();

        list.add(member);

      }
    } // try() ==> try block을 벗어나기 전에 자동으로 in.close()가 실행된다.
  }

  public void save() throws Exception{ 
    try(DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))){
      out.writeInt(list.size());

      for (Member member :list) {
        out.writeInt(member.no);
        out.writeUTF(member.name);
        out.writeUTF(member.email);
        out.writeUTF(member.password);
        out.writeLong(member.createdDate);
      }
    }// try() ==> try block을 벗어나기 전에 out.close()가 자동으로 실행된다.
  }

  public void insert(Member member) {
    list.add(member);
  }

  public Member findByEmail(String email) {
    for (int i = 0; i < list.size(); i++) {
      Member member = list.get(i);
      if (member.email.equals(email)) {
        return member;
      }
    }
    return null;
  }

  public boolean delete(String email) {
    for (int i = 0; i < list.size(); i++) {
      Member member = list.get(i);
      if (member.email.equals(email)) {
        return list.remove(i) != null;
      }
    }
    return false;
  }

  public Member[] findAll() {
    Iterator<Member> iterator = list.iterator();
    Member[] arr = new Member[list.size()];
    int i = 0;
    while(iterator.hasNext()) {
      arr[i++] = iterator.next();
    }
    return arr;
  }


}














