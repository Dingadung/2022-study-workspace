package com.bitcamp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.MemberService;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션
public class MemberController {

    MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/form") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String form(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/member/form.jsp";
    }

    @PostMapping("/member/add") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        Member member = new Member();
        member.setName(request.getParameter("name"));
        member.setEmail(request.getParameter("email"));
        member.setPassword(request.getParameter("password"));

        memberService.add(member);

        return "redirect:list";
    }

    @GetMapping("/member/list") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("members", memberService.list());
        return "/member/list.jsp";
    }

    @PostMapping("/member/update") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        Member member = new Member();
        member.setNo(Integer.parseInt(request.getParameter("no")));
        member.setName(request.getParameter("name"));
        member.setEmail(request.getParameter("email"));
        member.setPassword(request.getParameter("password"));

        if (!memberService.update(member)) {
            throw new Exception("회원 변경 오류입니다!");
        }

        return "redirect:list";
    }

    @GetMapping("/member/delete") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int no = Integer.parseInt(request.getParameter("no"));

        if (!memberService.delete(no)) {
            throw new Exception("회원 삭제 오류입니다!");
        }

        return "redirect:list";
    }
}






