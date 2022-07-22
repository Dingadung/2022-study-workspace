package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;

// 게시글 목록을 관리하는 역할
//
public class BoardList extends ObjectList {

  private int no = 0;

  // 상속 이용!
  // 수퍼 클래스의 get() 메서드는 인덱스로 항목을 찾는다
  // 그래서 Board 객체를 다루기에 적합하지 않다.
  // 따라서 다음 메서드 처럼 Board 객체를 조회하는데 적합한 메서드를 추가한다.
  // 이 메서드는 게시글 번호에 해당하는 Board 인스턴스를 찾아 리턴한다.

  // 슈퍼 클래스의 get() 메서드를 BoardList에 맞게 재정의한다.
  // -> 파라미터는 인덱스가 아닌 게시글 번호가 되게 한다.
  // --> Override이라고 부른다. (슈퍼를 서브에서 재정의)
  @Override
  public Board get(int boardNo) {
    for (int i = 0; i < this.length; i++) {
      Board board = (Board)this.list[i]; // Object배열에 실제 들어 있는 것은 Board라고 컴파일러에게 알린다.
      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }



  // 수퍼 클래스의 add(Object)를 BoardList에 맞게끔 정의한다.
  // 같은 이름의 유사 기능을 수행하는 메서드를 추가 정의한다.

  // => 파라미터로 받은 Board 인스턴스의 no 변수값을 설정한 다음 배열에 저장한다.
  // => Overriding
  @Override
  public void add(Object object) {
    Board board = (Board) object;
    board.no = nextNo(); // 게시글 추가 전에 게시글의 번호 먼저 설정해주는 것으 원해서 오버라이딩
    // 수퍼 클래스의 add()를 사용하여 처리
    super.add(board);
  }
  //상속 받은 클래스의 메서드를 서브 클래스의 역할에 맞춰서 사용하려고
  // 오버라이딩을 진행한다! 이것이 오버라이딩을 하는 이유이다!



  // 수퍼 클래스의 remove()를 BoardList 클래스의 역할을 맞춰 재정의한다.
  @Override
  public boolean remove(int boardNo) {
    int boardIndex = -1;
    for (int i = 0; i < this.length; i++) {
      Board board = (Board) this.list[i];
      if (board.no == boardNo) {
        boardIndex = i;
        break;
      }
    }
    if (boardIndex == -1) {
      return false;
    }
    return super.remove(boardIndex); // 재정의 하기 전의 수퍼 클래스의 메서드를 호출한다.
  }


  private int nextNo() {
    return ++no;
  }
}














