package com.bitcamp.board.service;

import java.util.List;

import com.bitcamp.board.domain.Member;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
// - 메서드의 이름은 업무와 관련된 이름을 사용한다.
// 
public interface MemberService {
    void add(Member member) throws Exception;

    boolean update(Member member) throws Exception;

    Member get(int no) throws Exception ;

    Member get(String email, String pwd) throws Exception ;

    boolean delete(int no) throws Exception;

    List<Member> list() throws Exception ;

}
