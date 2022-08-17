package com.bitcamp.board.dao;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Member;
import com.google.gson.Gson;

// 회원 목록을 관리하는 역할
//
public class MemberDao {
  List<Member> list = new LinkedList<Member>();

  String fileName;
  public MemberDao(String fileName) {
    this.fileName = fileName;
  }//MemberDao{}

  public void load() throws Exception {
    try(BufferedReader in = new BufferedReader(new FileReader(fileName))){
      StringBuilder stringBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        stringBuilder.append(str);
      }
      Member[] members = new Gson().fromJson(stringBuilder.toString(),Member[].class);

      for(int i=0;i<members.length;i++) {
        list.add(members[i]);
      }

    } // try() ==> try block을 벗어나기 전에 자동으로 in.close()가 실행된다.
  }

  public void save() throws Exception{ 
    try(FileWriter out = new FileWriter(fileName)){
      Member[] members = list.toArray(new Member[0]);
      out.write(new Gson().toJson(members));
    }// try() ==> try block을 벗어나기 전에 out.close()가 자동으로 실행된다.
  }

  public void insert(Member member) {
    list.add(member);
  }

  public boolean update(Member member) {
    for (int i = 0; i < list.size(); i++) {
      Member m = list.get(i);
      if (m.email.equals(member.email)) {
        list.set(i, member);
        return true;
      }
    }
    return false;
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














