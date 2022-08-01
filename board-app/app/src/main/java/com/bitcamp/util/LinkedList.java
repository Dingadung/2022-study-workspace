package com.bitcamp.util;

public class LinkedList {
  private Node Head;
  private Node Tail;
  private int size;

  public void append(Object value) {
    Node node = new Node(value);
    size++;
    if(Tail == null) {
      // 아무것도 없는 경우
      Head = Tail = node;
      //      node.prev = null;
      //      node.next = null;
      // 해줄 필요없다. 자동으로 null로처리
      return;
    }

    Tail.next = node;
    node.prev = Tail; 
    node.next = null;
    Tail = node;
  }


  public Object retrieve(int index) {
    if(index <0 || index >= size) {
      throw new ListException("인덱스의 범위를 초과하였습니다!");
    }
    Node cursor = Head;
    for(int i=0;i<index;i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }

  public Object delete(int index) {
    if(index <0 || index >= size) {
      throw new ListException("인덱스의 범위를 초과하였습니다!");
    }
    Object deleted = null;
    size--;
    if(Head == Tail) {
      // 노드가 하나만 남은 경우,
      deleted = Head.value;
      Head.value = null;
      Head = null;
      Tail = null;
      return deleted;
    }

    Node cursor = Head;
    for(int i=0;i<index;i++) {
      cursor = cursor.next;
    }
    deleted = Head.value;

    if(cursor.prev != null) {
      // 만약 삭제하려는 노드가 맨앞 노드가 아닌 경우,
      cursor.prev.next = cursor.next;
    }else {
      // 만약 삭제하려는 노드가 맨앞 노드인 경우,
      Head = cursor.next;
      Head.prev = null;
    }

    if(cursor.next != null) {
      // 만약 삭제하려는 노드가 맨 뒤 노드가 아닌 경우,
      cursor.next.prev = cursor.prev;
    }else {
      // 만약 삭제하려는 노드가 맨 뒤 노드인 경우,
      Tail = cursor.prev;
      Tail.next=null;
    } 

    cursor.prev=null;
    cursor.next = null;
    cursor.value = null;

    return deleted;
  }

  public int length() {
    return size;
  }

  public Object[] getArray() {
    Object[] arr = new Object[size];
    Node cursor = Head;
    for(int i=0;i<size;i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }
}
