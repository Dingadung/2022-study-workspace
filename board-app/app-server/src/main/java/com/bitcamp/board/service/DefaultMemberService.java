package com.bitcamp.board.service;

import java.util.List;

import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.domain.Member;

// 비즈니스 로직을 수행하는 객체
// - 메서드의 이름은 업무와 관련된 이름을 사용한다.
// 
public class DefaultMemberService implements MemberService{
    MemberDao memberDao;

    public DefaultMemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public void add(Member member) throws Exception {
        memberDao.insert(member);
    } // add()

    @Override
    public boolean update(Member member) throws Exception{
        return memberDao.update(member) >0 ;
    } // update()

    @Override
    public Member get(int no) throws Exception {
        return memberDao.findByNo(no);
    }

    @Override
    public Member get(String email, String pwd) throws Exception {
        return memberDao.findByEmailPassword(email, pwd);
    }

    @Override
    public boolean delete(int no) throws Exception {
        return memberDao.delete(no) > 0 ;
    }

    @Override
    public List<Member> list() throws Exception {
        return memberDao.findAll();
    }

}
