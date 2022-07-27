// String - String.equals() vs Object.equals()
package com.eomcs.basic.ex02;

public class Exam0122  {
  // 현재 특정 클래스를 상속 받고 있지 않기 때문에 결국 Exam0122클래스는 Object 클래스를 상속받고 있다. 또한 오버라이딩이나 오버로딩이 이루어지고 있지 않기 때문에, Object 클래스 메소드 자체를
  // 그대로 사용한다.
  // 특히, equals가 Object에서는 인스턴스를 비교하기 때문에 여기서도 같은 문자열 값을 갖고 있더라도, 
  //String 클래스를 통해 접근하고 있지 않기 때문에, 인스턴스가 다르기 때문에 다르다는 결과값을 반환해준다.
  //String은 toString()을 오버라이딩해서 우리가 아는 문자열 값을비교해주는 결과값을 얻을수 있게 해준다.

  static class Member {
    String name;
    int age;

    public Member(String name, int age) {
      this.name = name;
      this.age = age;
    }
  }

  public static void main(String[] args) {

    Member m1 = new Member("홍길동", 20);
    Member m2 = new Member("홍길동", 20);

    //Member 인스턴스를 통해 호출하는 메서드는 모두 Object클래스의 메서드이다.

    // 비록 m1과 m2는 같은 값을 갖고 있지만 인스턴스가 다르다.
    System.out.println(m1 == m2); // false

    // Member는 Object에서 상속 받은 equals()를 오버라이딩 하지 않았다.
    // 따라서 단순히 인스턴스가 같은지를 비교할 것이다.
    System.out.println(m1.equals(m2)); // false
    System.out.println(m1.toString());
    System.out.println(m2.toString());
  }
}


