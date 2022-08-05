// 제네릭 적용하기
package com.eomcs.design_pattern.iterator.after3;

public class LinkedList<E> {

  protected Node<E> head;
  protected Node<E> tail;
  protected int size;

  public LinkedList() {
    head = new Node<>();
    tail = head;
    size = 0;
  }

  public void add(E value) {
    tail.value = value;

    Node<E> node = new Node<>();

    tail.next = node;

    node.prev = tail;

    tail = node;

    size++;
  }

  public int size() {
    return size;
  }

  public E get(int index) {
    if (index < 0 || index >= size)
      return null;

    Node<E> cursor = head;

    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }

    return cursor.value;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size()];

    Node<E> cursor = head;

    int i = 0; 
    while (cursor != tail) {
      arr[i++] = cursor.value;
      cursor = cursor.next;
    }

    return arr;
  }

  public E set(int index, E value) {
    if (index < 0 || index >= size) 
      return null;

    Node<E> cursor = head;

    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }

    E old = cursor.value;

    cursor.value = value;

    return old;
  }

  public int insert(int index, E value) {
    if (index < 0 || index >= size)
      return -1;

    Node<E> node = new Node<>(value);

    Node<E> cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }

    node.next = cursor;

    node.prev = cursor.prev;

    cursor.prev = node;

    if (node.prev != null) {
      node.prev.next = node;
    } else {
      head = node;
    }

    size++;

    return 0;
  }

  public E remove(int index) {
    if (index < 0 || index >= size)
      return null;

    Node<E> cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }

    if (cursor.prev != null) {
      cursor.prev.next = cursor.next;
    } else {
      head = cursor.next;
    }

    cursor.next.prev = cursor.prev;

    E old = cursor.value;
    cursor.value = null;
    cursor.prev = null;
    cursor.next = null;

    size--;

    return old;
  }

  private static class Node<E> {

    E value;
    Node<E> prev;
    Node<E> next;

    Node() {
    }

    Node(E value) {
      this.value = value;
    }
  }

  // Iterator 구현체를 제공한다.
  public Iterator<E> iterator(){
    return new LinkedListIterator<E>();
  }

  //LinkedList에서 데이터를 꺼내줄 객체//
  // non-스태틱 중첩 클래스로 정의한다.
  // 논 스태틱 중첩 클래스로 정의하여 바깥 클래스의 인스턴스 주소를 받는 필드와 생성자 파라미터를 자동으로 추가하게 한다.
  class LinkedListIterator<T> implements Iterator<T> {
    //    LinkedList<E> list;
    int index=0;

    //    public LinkedListIterator(LinkedList  <E> list) {
    //      this.list = list;
    //    }
    @Override
    public boolean hasNext() {
      return index < LinkedList.this.size();
    }
    @Override
    public T next() {
      return (T)LinkedList.this.get(index++);
    }
  }


}















