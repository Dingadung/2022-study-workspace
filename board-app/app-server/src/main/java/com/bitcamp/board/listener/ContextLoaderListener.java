package com.bitcamp.board.listener;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebListener;
import com.bitcamp.servlet.DispatcherServlet;

// 웹애플리케이션이 시작되었을 때 공유할 자원을 준비시키거나 해제하는 일을 한다.
//
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) 
@WebListener
public class ContextLoaderListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("공유 자원을 준비 중!!");
    try {
      ServletContext ctx = sce.getServletContext();


      // 자바 코드로 프론트 컨트롤러를 직접 생성하여 서버에 등록하기
      DispatcherServlet servlet = new DispatcherServlet();
      Dynamic config = ctx.addServlet("DispatcherServlet", servlet);
      config.addMapping("/service/*");
      config.setMultipartConfig(new MultipartConfigElement(
          this.getClass().getAnnotation(MultipartConfig.class)));

      // 서블릿, 서비스, DAO, 트랜잭션 관리자, 데이터소스 객체는
      // 프론트 컨트롤러에서 준비한다.
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
