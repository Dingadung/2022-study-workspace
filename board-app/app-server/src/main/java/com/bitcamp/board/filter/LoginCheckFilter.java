package com.bitcamp.board.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.board.domain.Member;

@WebFilter("/service/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginCheckFilter.init() 실행");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("LoginCheckFilter.doFilter() 실행!");
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        //        String servletPath = httpRequest.getServletPath();

        // URL 매핑이 "/service/*"  형식으로 되어 있을 때
        // * 경로를 알아내려면 다음의 메서드를 호출해야 한다.
        String servletPath = httpRequest.getPathInfo();
        System.out.println(servletPath);

        if(servletPath.endsWith("add") || servletPath.endsWith("update") ||  servletPath.endsWith("delete") ) {
            Member loginMember =(Member)httpRequest.getSession().getAttribute("loginMember");
            if(loginMember == null) { // 로그인 하지 않았다면
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/service/auth/form");
                return;
            }
        }

        chain.doFilter(request, response);
    }

}
