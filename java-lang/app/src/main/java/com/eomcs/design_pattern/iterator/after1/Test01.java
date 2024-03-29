// iterator 디자인 패턴 : 1) 패키지 멤버로 Iterator 클래스 정의하기
package com.eomcs.design_pattern.iterator.after1;

// Iterator 디자인 패턴
// => 데이터 목록을 관리하는 방식에 상관없이 일관된 방식으로 데이터를 꺼낼 수 있게 해주는 설계 기법
// => 즉 데이터 목록을 관리하는 객체를 직접 사용하여 값을 꺼내는 것이 아니라,
//    값을 꺼내는 주는 별도의 객체의 도움을 받아 값을 꺼낸다.
// => 값을 꺼내주는 객체를 "Iterator"라 부른다.
//    값을 꺼내주는 객체의 사용법을 통일하기 위하여 인터페이스로 사용 규칙을 정의한다.
// => 각각의 데이터 목록 관리 객체는 Iterator 규칙에 따라 값을 꺼내는 객체를 리턴한다.
public class Test01 {

  public static void main(String[] args) {
    ArrayList<String> list1 = new ArrayList<>();
    list1.add("aaa");
    list1.add("bbb");
    list1.add("ccc");
    list1.add("ddd");

    // 목록에서 값 꺼내기
    // 1) ArrayList 에서 값 꺼내기
    // => ArrayList에게 값을 꺼내는 일을 할 객체를 달라고 요구한다.
    Iterator<String> iterator1 = list1.iterator();
    while(iterator1.hasNext()) {
      System.out.println(iterator1.next());
    }
    System.out.println("-----------------------------------------------");

  }

  // Iterator 설계 패턴의 특
  // - 자료 구조에 상관없이 꺼내는 방식이 같다.
  // - 즉, 프로그래밍의 일관성을 제공한다.
  // 결론!
  // - 자료 구조에 따라 데이터를 꺼내는 방식이 다르다!
  // - 데이터 조회에 일관성이 없다!
  //
  // - 문제점
  // - ArrayListIterator는 오직 ArrayList 클래스에서만 생성한다.
  // 즉, ArrayList가 아닌 클래스에서 생성할 일이 없다.
  // - 그럼에도 불구하고 패키지 멤버이기 때문에 전체 패키지에 공개되어 있다.
  //
  // 해결책!
  // - 각 Iterator 클래스를 그 Iterator를 생성하는 클래스 안으로 넣어서 쓸데없는 노출을 막는다.
  // - 외부의 객체는 Iterator 인터페이스 규칙에 따라 사용할 수 있어 중첩클래스로 만들어도 괜찮다.
}









