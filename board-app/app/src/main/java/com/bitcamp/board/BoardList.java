package com.bitcamp.board;

//寃뚯떆湲� 紐⑸줉�쓣 愿�由ы븯�뒗 �뿭�븷�쓣 留↔퀬 �엳�뒗 �겢�옒�뒪
public class BoardList {
  //-------------------蹂��닔 �꽑�뼵-----------------------\\
  static final int DEFAULT_SIZE = 3;
  int boardCount; 
  Board[] boards; 
  int no=0;
  //-------------------蹂��닔 �꽑�뼵-----------------------\\

  //-------------------�깮�꽦�옄 �꽑�뼵-----------------------\\
  public BoardList(){
    this.boards = new Board[DEFAULT_SIZE];
  }
  BoardList(int initCapacity) {
    this.boards = new Board[initCapacity];
  }
  //-------------------�깮�꽦�옄 �꽑�뼵-----------------------\\

  // 紐⑸줉�뿉 ���옣�맂 �씤�뒪�꽩�뒪瑜� 爰쇰궡�꽌 由ы꽩�븳�떎.
  Board[] toArray() {
    Board[] arr = new Board[this.boardCount];
    for(int i=0;i<arr.length;i++) {
      arr[i] = this.boards[i];
    }
    return arr;
  }

  // 寃뚯떆湲� 踰덊샇�뿉 �빐�떦�븯�뒗 Board �씤�뒪�꽩�뒪瑜� 李얠븘 由ы꽩�븳�떎.
  Board get(int boardNo) {
    for (int i = 0; i <this.boardCount; i++) {
      if (this.boards[i].no == boardNo) {
        return this.boards[i];
      }
    }
    return null;
  }

  // Board �씤�뒪�꽩�뒪瑜� 諛곗뿴�뿉 ���옣�븳�떎.
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
        System.out.println("�궘�젣�릺�뿀�뒿�땲�떎.");
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

  int getIdx(int editNo) {
    int editIdx=-1;
    for(int i=0;i<this.boardCount;i++) {
      if(editNo == this.boards[i].no) {
        editIdx=i;
        //        edit(editIdx);
        break;
      }
    }
    return editIdx;
  }




  void delete(int deleteIdx) {
    // �궘�젣�븷 �빆紐⑹쓽 �떎�쓬 �빆紐⑹쓣 �븵�쑝濡� �떦湲대떎.
    for(int i=deleteIdx+1;i<this.boardCount;i++) {
      this.boards[i-1] = this.boards[i];
    }
    // 寃뚯떆湲� 媛쒖닔瑜� �븳 媛� 以꾩씤 �썑, 留� �뮘�뿉 �엳�뜕 �빆紐⑹쓽 二쇱냼瑜� 0�쑝濡� �꽕�젙�븳�떎.
    this.boards[--this.boardCount]=null;
  }



  void grow(){
    // ���옣�븷 �닔 �엳�뒗 寃뚯떆湲� 異붽�
    int newSize = this.boards.length + (this.boards.length >> 1);
    Board[] newArray = new Board[newSize];
    for(int i=0;i<this.boards.length;i++) {
      newArray[i] = this.boards[i];
    }
    boards = newArray;
  }

  int nextNo() {
    // 異붽� �벑濡앺븯�뒗 寃뚯떆湲� 踰덊샇 �꽕�젙
    return ++no;
  }
}
