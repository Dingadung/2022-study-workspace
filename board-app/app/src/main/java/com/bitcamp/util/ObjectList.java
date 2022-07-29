package com.bitcamp.util;

public  class ObjectList {
  private static final int DEFUALT_CAPACITY = 10;
  private int size;
  protected Object[] elementData;

  public ObjectList() {
    elementData = new Object[DEFUALT_CAPACITY]; 
  }
  public ObjectList(int initialCapacity) {
    elementData = new Object[initialCapacity];
  }

  public void add(Object e) {
    if(size == elementData.length) {
      grow();
    }
    elementData[size++] = e;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = elementData[i];
    }
    return arr;
  }

  // 개발자가 예외 클래스 이름만 보고도  어떤 작업을 하다가 예외를 발생했는지 직관적으로 알 수 있도록, 사용자 정의 예외를 던진다!!!!!!
  // => RuntimeException계열의 예외는 메서드선언부에 표시할 필요가 없다.
  public Object get(int index) throws ListException{  
    if(index <0 || index >= size) {

      throw new ListException("인덱스가 무효합니다!");
    }
    return elementData[index];
  }

  //=> RuntimeException계열의 예외는 메서드선언부에 표시할 필요가 없다. get() 처럼 throws해줘도 되고, remove처럼 throws 안 해줘도 된다.
  public boolean remove(int index) {
    if(index <0 || index >= size) {
      throw new ListException("인덱스가 무효합니다!");
    }
    for(int i=index+1;i<size;i++) {
      elementData[i-1]=elementData[i];
    }
    elementData[--size] = null;
    return true;
  }

  public int size() {
    return size;
  }

  private void grow() {
    int newSize = elementData.length + (elementData.length >> 1);
    Object[] newArray = new Object[newSize];
    for (int i = 0; i < elementData.length; i++) {
      newArray[i] = elementData[i];
    }
    elementData = newArray;
  }
}
