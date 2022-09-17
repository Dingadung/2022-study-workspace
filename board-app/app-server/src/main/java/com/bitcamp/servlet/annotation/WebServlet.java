package com.bitcamp.servlet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.TYPE) // annotation을 붙일 수 있는 범위를 설정한다.
@Retention(value=RetentionPolicy.RUNTIME) // annotation 값을 추출할 때를 지정 -> 프로그램이 실행 중일 때 추출하겠다.

// 다음 annotation은 HTTP 요청을 처리하는 객체에 대해 경로를 설정할 때 사용한다.
public @interface WebServlet {
  String value(); // annotation의 필수 속성을 설정
}
