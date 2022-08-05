// 제네릭 적용
package com.eomcs.design_pattern.iterator.after5;

// Queue가 보관하는 데이터 타입을 E 라고 가정하자.
// => E라고 가정한 상태에서 코드를 작성한다.
//
public class Queue<E> extends LinkedList<E> {

  public void offer(E value) {
    this.add(value);
  }

  public E poll() {
    return this.remove(0);
  }

  public boolean empty() {
    return this.size == 0;
  }


  // Iterator 구현체를 제공한다.
  @Override
  public Iterator<E> iterator(){
    //Queue에서 데이터를 꺼내줄 객체//
    Iterator<E> iterator = new Iterator<>() {
      @Override
      public boolean hasNext() {
        return !Queue.this.empty(); // 유효한 인덱스!
      }

      @Override
      public E next() {
        return Queue.this.poll();
      }
    };
    return iterator;
  }

}


