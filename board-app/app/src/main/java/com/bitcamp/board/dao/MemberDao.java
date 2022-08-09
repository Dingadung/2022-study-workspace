package com.bitcamp.board.dao;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Member;

// 회원 목록을 관리하는 역할
//
public class MemberDao {
  List<Member> list = new LinkedList<Member>();

  String fileName;
  public MemberDao(String fileName) {
    this.fileName = fileName;
  }//MemberDao{}

  public void load() throws Exception {
    try(FileInputStream in = new FileInputStream(fileName);){

      // => 먼저 회원 개수를 읽는다.
      int size = (in.read() << 24) + (in.read() << 16) + (in.read() << 8) + in.read();

      for (int i = 0; i < size; i++) {
        Member member = new Member();

        // => 저장된 순서로 데이터를 읽는다.
        // 1) 회원 번호 읽기
        int value = 0;
        value += in.read() << 24; // 예) 12 => 12000000
        value += in.read() << 16; // 예) 34 => 00340000
        value += in.read() << 8;  // 예) 56 => 00005600
        value += in.read();       // 예) 78 => 00000078
        member.no = value;

        // 2) 회원 이름 읽기
        int len = 0;
        len = (in.read() << 24) + (in.read() << 16) + (in.read() << 8) + in.read();
        byte[] bytes = new byte[len];
        in.read(bytes);
        member.name = new String(bytes, "UTF-8");

        // 3) 회원 이메일 읽기
        len = (in.read() << 24) + (in.read() << 16) + (in.read() << 8) + in.read();
        bytes = new byte[len];
        in.read(bytes);
        member.email = new String(bytes, "UTF-8");

        // 4) 회원 암호 읽기
        len = (in.read() << 24) + (in.read() << 16) + (in.read() << 8) + in.read();
        bytes = new byte[len];
        in.read(bytes);
        member.password = new String(bytes, "UTF-8");

        // 5) 게시글 등록일 읽기
        member.createdDate = 
            (((long)in.read()) << 56) + 
            (((long)in.read()) << 48) +
            (((long)in.read()) << 40) +
            (((long)in.read()) << 32) +
            (((long)in.read()) << 24) +
            (((long)in.read()) << 16) +
            (((long)in.read()) << 8) +
            ((in.read()));

        list.add(member);

      }
    }
    //in.close();
  }

  public void save() throws Exception{ 
    FileOutputStream out = new FileOutputStream(fileName); // try-catch로 묶어주거나, 묶기 싫으면, 오류를 위임해버려 줘야 한다. 둘 중 하나는 해줘야 함.

    // 첫 번째로 먼저 게시글의 개수를 4바이트 int 값으로 먼저 출력한다.
    out.write(list.size()>>> 24);
    out.write(list.size() >>> 16);
    out.write(list.size() >>> 8);
    out.write(list.size()); 


    for (Member member :list) {
      out.write(member.no >>> 24); //0x00000012|345678 / 0x12345678 
      out.write(member.no >>> 16); // 0x00001234|5678 /  0x12345678 
      out.write(member.no >>> 8); // 0x00123456|78 /  0x12345678 
      out.write(member.no); //  0x12345678 

      // 출력할 바이트 배열의 개수를 먼저 출력한다. (2 바이트)
      byte[] bytes = member.name.getBytes("UTF-8");
      out.write(bytes.length >> 24);
      out.write(bytes.length>> 16);
      out.write(bytes.length>> 8);
      out.write(bytes.length);
      out.write(bytes);


      //out.write(board.title.getBytes("UTF-8")); // 무엇으로 인코딩할지 강제로 알려주기 -> UTF-8로 인코딩해라!!
      System.out.printf("%s\n", member.email); // String 데이터 타입 객체가 byte 타입으로 반환된다.
      //out.write(board.content.getBytes("UTF-8"));
      bytes = member.email.getBytes("UTF-8");
      out.write(bytes.length >> 24);
      out.write(bytes.length>> 16);
      out.write(bytes.length>> 8);
      out.write(bytes.length);
      out.write(bytes);


      System.out.printf("%s\n", member.password);
      //out.write(board.password.getBytes("UTF-8"));
      bytes = member.password.getBytes("UTF-8");
      out.write(bytes.length >> 24);
      out.write(bytes.length>> 16);
      out.write(bytes.length>> 8);
      out.write(bytes.length);
      out.write(bytes);

      // long ==> byte[]
      System.out.printf("%016x\n", member.createdDate);
      out.write((int) (member.createdDate>> 56));
      out.write((int) (member.createdDate>> 48));
      out.write((int) (member.createdDate>> 40));
      out.write((int) (member.createdDate>> 32));
      out.write((int) (member.createdDate>> 24));
      out.write((int) (member.createdDate>> 16));
      out.write((int) (member.createdDate>> 8));
      out.write((int) (member.createdDate));

    }
    out.close();
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














