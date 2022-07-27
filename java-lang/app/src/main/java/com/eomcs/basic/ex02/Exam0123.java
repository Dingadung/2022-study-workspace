// String - String.equals() 처럼 동작하게 만들기
package com.eomcs.basic.ex02;

import java.util.Objects;

public class Exam0123 {

  static class Member {
    String name;
    int age;

    public Member(String name, int age) {
      this.name = name;
      this.age = age;
    }

    // String의 equals()처럼 내용이 같은지를 비교하도록 만들고 싶다면,
    // Object에서 상속 받은 메서드를 오버라이딩 하라.
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass()) // 둘의 클래스가 다르면 둘의 값은 당연히 다르다!
        return false;
      Member other = (Member) obj;
      return age == other.age && Objects.equals(name, other.name);
    }
    // 지우고 오른쪽 버튼, 소스, 제너레이트 이쿼스 , age, name이 같을 떄 트루 리턴하도록,  마지막 생성자 바로 밑에 생성-> 바로 위의 메소드가 생성된다.
    // equals 는 도구의 도움을 받아서 생성하자. 내가 생성XXXXXX!!!
  }

  public static void main(String[] args) {

    Member m1 = new Member("홍길동", 20);
    Member m2 = new Member("홍길동", 20);

    // 인스턴스는 다르지만,
    System.out.println(m1 == m2); // false

    // 인스턴스의 내용물이 같기 때문에 결과는?
    System.out.println(m1.equals(m2)); // true
  }
}


