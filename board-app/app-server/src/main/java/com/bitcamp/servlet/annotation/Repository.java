package com.bitcamp.servlet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.TYPE) // annotation을 붙일 수 있는 범위를 설정한다.
@Retention(value=RetentionPolicy.RUNTIME) // annotation 값을 추출할 때를 지정 -> 프로그램이 실행 중일 때 추출하겠다.

// 다음 annotation은 DAO 객체를 표시할 때 사용한다.
public @interface Repository {
  String value();
}
