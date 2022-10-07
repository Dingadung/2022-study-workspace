package com.bitcamp.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.MemberService;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet{

    private static final long serialVersionUID = 1L;

    MemberService memberService;

    @Override
    public void init() {
        memberService = (MemberService) this.getServletContext().getAttribute("memberService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Member member = memberService.get(email, password);

            if(member != null) {
                HttpSession session = request.getSession(); // 요청한 클라이언트의 전용 HttpSession 보관소를 얻는다.
                session.setAttribute("loginMember", member); // 로그인한 멤버 정보를 세션 보관소에 저장한다.
            }
            Cookie cookie = new Cookie("email", email); // 클라이언트 쪽에 저장할 쿠키 생성 

            if(request.getParameter("saveEmail") == null) {
                cookie.setMaxAge(0); // 클라이언트에게 해당 이름의 쿠키를 지우라고 명령한다.
            } else {
                cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
            }

            // include 되는 서블릿에서는 응답헤더에 쿠키를 포함시킬 수 없다.
            //            response.addCookie(cookie); // 응답헤더에 쿠키를 포함시킨다.

            // 프론트 컨트롤러에서 쿠키를 응답 헤더에 포함시키도록 ServletRequest 보관소에 저장한다.
            List<Cookie> cookies = new ArrayList<>();
            cookies.add(cookie);

            request.setAttribute("cookies", cookies);
            request.setAttribute("member", member);
            request.setAttribute("viewName", "/auth/loginResult.jsp");

        } catch(Exception e) {
            request.setAttribute("exception", e);
        }
    }
}
