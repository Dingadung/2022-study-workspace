package com.bitcamp.util;

public class ObjectListTest {
  public static void main(String[] args)throws Throwable {
    class Member{}
    //
    //    Object obj = new Object();
    //    System.out.println(obj.toString()); // Object의 toString() =>   "클래스명@해시값"
    //
    //    Member obj2 = new Member();
    //    System.out.println(obj2.toString()); //Object의 toString() => "클래스명@해시값"
    //
    //    String obj3 = new String("Hello");
    //    System.out.println(obj3.toString()); //String의 toString() => "Hello"
    //
    //        Object obj4 = new String("Hello");
    //        System.out.println(obj4.toString());
    ObjectList list = new ObjectList(); // 추상 클래스가 아니여서 객체로 직접 사용 가능!
    list.add("홍길동");
    list.add("임꺽정");
    list.add("유관순");
    list.add(null); //목록에 null을 넣을 수 있다.
    list.add("안중근");

    Object obj = list.get(0); //  실제 get()이 리턴하는 것은 String 객체이다.
    System.out.println(obj.toString()); 

    System.out.println(list.get(0)); // 따로레퍼런스 변수 안만들고 바로 넣어줘도 된다.
    //원래 Object에도 toString()이 있기 때문에 컴파일러에게 안걸리는 것이다. 컴파일러는  이 메소드가 Object에 존재하느냐만 따짐! 컴파일러는 레퍼런스 변수가 어디거인지만 따진다.
    // obj가 실제 가르키는것은 String 클래스이다. ㄱobj는 Object였던 것이 맞지만, String클래스가 오버라이딩 했기 때문에 String클래스에서 가져온다.
    //obj.가 가르키는실제 클래스를찾아간다.
    //obj.char(0) 이 메소드는 컴파일 오류가 난다. 하지만 컴파일이 통과 됐으면 obj는 실제 가르키는 클래스의 메소드를 가져온다.
    // 실행을 할 때는 jvm 는 인스턴스 타입부터 따져서 올라간다. 근데 오버라이딩 했기떄문에 String 클래스의 toString() 메소드를 가져온다.
    // 인스턴스 타입의 클래스가 오버라이딩 앙ㄴ했으면 그냥 Object - toString()을 가져왔을 것!
    obj = list.get(1);
    System.out.println(obj.toString());
    System.out.println(list.get(1)); // 쓸데 없이 변수에 담을 필요 없다. 바로println에게 넘겨주면 된다. 

    obj = list.get(2);
    System.out.println(obj.toString());

    //println():
    //파라미터 값이 유효한 인스턴스 주소라면 toString()을 호출하여 그 리턴값을 출력한다.
    // String.valueOf()를 해줘서 얘가 알아서 String값으로 바꿔서 출력한다.
    //따라서 굳이 toString()을 명시적으로호출할 필요가 없다.
    //파라미터 값이 null이라면 그대로 Null을 출력한다.
    System.out.println(list.get(3)); 
    System.out.println(list.get(4)); 
    System.out.println(list.get(4).toString());  // 그래서toString() 없어도 되는 것!
    // 생각해볼 문제!
    // get()이 목록에서 null을 리턴한다면, 예외 상황인가 아니면 그냥 정상적인 상황인가?
    //결론!
    // null을 리턴한다고 해서 무조건 예외상황이 아니다.
    // 의도적으로 null을 저장해놓고 꺼낼 수 있기 때문이다.
    // 따라서 인덱스를 잘못 지정한 경우와 구분해야한다!
    // 인덱스를 잘못 지정했으면, 예외상황이지만, 그밖에는 정상적인 상황이다.



    //3번 인덱스에 null이 들어 있기 떄문에 꺼낸 값도 null이다.
    // println()에 전달하는 값이 null이면 "null"이라고 출력한다.
    System.out.println(list.get(3)); 
    //100번 인덱스는 유효한 인덱스가 아니기 때문에 get()메소드가 null을 리턴한다.
    System.out.println(list.get(100));
    //생각해볼 문제!
    // get()이 목록에서 null을 리턴한다면, 예외 상황인가 아니면 그냥 정상적인 상황인가?
    //결론!
    // null을 리턴한다고 해서 무조건 예외상황이 아니다.
    // 결론적으로 예외 상황인지 아닌지는 알 수 없다.
    // 따로 구분하지 않았기 때문이다.
    // 리턴값으로는 정상과 예외 상황을 표현할 수 없다.
    // 리턴값으로는 두 상황을 구분할 수 없다.

    // 해결책!
    // - 정상적인 경우는 정상적으로  해당 값을 리턴한다.
    // - 예외 상황일 경우에는 예외 정보를 던진다.  => 예외를 발생시킨다.
  }
}
