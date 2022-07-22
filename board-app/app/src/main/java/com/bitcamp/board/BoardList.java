package com.bitcamp.board;

//게시글 목록을 관리하는 역할을 맡고 있는 클래스
public class BoardList {
  //-------------------변수 선언-----------------------\\
  static final int DEFAULT_SIZE = 3;
  int boardCount; 
  Board[] boards; 
  int no=0;
  //-------------------변수 선언-----------------------\\

  //-------------------생성자 선언-----------------------\\
  public BoardList(){
    this.boards = new Board[DEFAULT_SIZE];
  }
  BoardList(int initCapacity) {
    this.boards = new Board[initCapacity];
  }
  //-------------------생성자 선언-----------------------\\

  // 목록에 저장된 인스턴스를 꺼내서 리턴한다.
  Board[] toArray() {
    Board[] arr = new Board[this.boardCount];
    for(int i=0;i<arr.length;i++) {
      arr[i] = this.boards[i];
    }
    return arr;
  }

  // 게시글 번호에 해당하는 Board 인스턴스를 찾아 리턴한다.
  Board get(int boardNo) {
    for (int i = 0; i <this.boardCount; i++) {
      if (this.boards[i].no == boardNo) {
        return this.boards[i];
      }
    }
    return null;
  }

  // Board 인스턴스를 배열에 저장한다.
  void add(Board board) {
    if (this.boards.length == this.boardCount) {
      grow();
    }
    board.no = nextNo();
    this.boards[this.boardCount++] = board;
  }

  boolean remove(int deleteNo) {
    int deleteIdx = -1;
    for(int i=0;i<this.boardCount;i++) {
      if(deleteNo == this.boards[i].no) {
        deleteIdx=i;
        delete(deleteIdx);
        break;
      }
    }
    if(deleteIdx == -1) {
      return false;
    }
    return true;
  }

  void delete(int deleteIdx) {
    // 삭제할 항목의 다음 항목을 앞으로 당긴다.
    for(int i=deleteIdx+1;i<this.boardCount;i++) {
      this.boards[i-1] = this.boards[i];
    }
    // 게시글 개수를 한 개 줄인 후, 맨 뒤에 있던 항목의 주소를 0으로 설정한다.
    this.boards[--this.boardCount]=null;
  }



  void grow(){
    // 저장할 수 있는 게시글 추가
    int newSize = this.boards.length + (this.boards.length >> 1);
    Board[] newArray = new Board[newSize];
    for(int i=0;i<this.boards.length;i++) {
      newArray[i] = this.boards[i];
    }
    boards = newArray;
  }

  int nextNo() {
    // 추가 등록하는 게시글 번호 설정
    return ++no;
  }
}
