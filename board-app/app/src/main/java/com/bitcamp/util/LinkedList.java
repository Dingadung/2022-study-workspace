package com.bitcamp.util;
/*
 * Node를 이용해 값의 목록을 관리하는 일을 한다.
 * author: jimin Park
 * */

public class LinkedList {
  private Node head; // 첫 노드의 주소를 저장
  private Node tail; // 마지막 노드의주소를 저장
  private int size; // 저장된 데이터의 개수


  /*
   *  add():    파라미터로 주어진 값을 노드에 담아 리스트 끝에 연결한다.
   * */
  public void add(Object value) {
    // Node 생성 후, 값을 저장한다.
    Node node = new Node(value);
    size++; // 목록의 크기를 한 개 증가시킨다.

    // 리스트의 끝에 노드를 붙인다.
    // 만약, 리스트에 한 개의 노드도 없다면, 
    if(tail == null) {
      head = tail = node; // 첫 노드를 등록한다.
      return;
    }

    tail.next = node;  // 리스트 끝에 새 노드를 연결한다.
    node.prev = tail; // 새 노드가 현재의 끝 노드를 가르키게 한다.
    tail = node; // 새 노드가 끝 노드가 되게 설정한다.
  }

  public Object get(int index) {
    // 인덱스의 유효 여부 검사
    if(index < 0 || index >= size) {
      throw new ListException("인덱스의 범위를 초과했습니다!");
    }
    // 인덱스에 해당하는 노드를 찾을 때, 헤드부터 시작하므로 for문을 1부터돌려주면 된다. 이미 0 번째 인덱스로 시작 설정이 되어있으므로 0번째 인덱스는 검사할 필요 없음.
    Node cursor = head;
    for(int i=0;i<index;i++) {
      cursor = cursor.next;
    }
    // cursor가 가르키는  노드의  값을 꺼내 리턴한다.
    return cursor.value;
  }

  public void remove(int index) {
    // 인덱스의 유효 여부 검사
    if(index < 0 || index >= size) {
      throw new ListException("인덱스의 범위를 초과했습니다!");
    }

    // 목록 크기를 한 개 줄인다.
    size--;

    // 삭제할노드를 찾기 위해 시작 노드를 head로 설정한다.
    Node cursor = head;

    // 지정된 인덱스의 노드 주소를 알아낸다.
    for(int i=0;i<index;i++) {
      cursor = cursor.next;
    }
    // 찾은 노드의 앞 뒤 노드를 바로 연결한다.
    if(cursor.prev != null) { // 삭제할 노드 앞에 노드가 있다면, 
      cursor.prev.next = cursor.next; // 현재 노드의 다음 노드주소를 현재 노드의 이전노드의 next에 저장하여 연결한다.
    }else {// 맨 앞 노드라면,  => 
      // 삭제할 노드 앞에 노드가 없다면, 즉, 삭제할 노드가 맨 앞의 노드라면, 첫번째 노드를 삭제하는 상황이므로, 헤드를 새로 초기화해준다.
      head = cursor.next; // 삭제할 노드의 다음 노드를 시작 노드로 설정한다.
      head.prev = null; // 시작 노드가 앞노드를 가르키지 않게 한다.
    }

    if(cursor.next!=null) {
      // 마지막 노드가 아니라면,
      cursor.next.prev = cursor.prev; // 현재 노드의 이전 노드주소를 현재 노드의 다음 노드의 prev에 저장하여 연결한다.
    }else {
      // 마지막 노드라면,
      tail = cursor.prev; // 현재 커서의 이전 노드를 마지막 노드로 설정한다.
      tail.next = null; // 마지막 노드이기 때문에 다음 노드를 가르키지 않게 한다. 
    }

    // 삭제할 노드를 초기화 시켜준다.
    // 삭제할 노드, garbage객체가 살아있는 객체를 가르키지, 참조하지 않게 한다.
    //garbage객체가 다른 garbage객체를 참조하지 않게 한다.
    cursor.value = null;
    cursor.prev = null;
    cursor.next = null;

  }

  public int size() {
    return size;
  }
  //LinkedList 끝부분
}