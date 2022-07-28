package com.bitcamp.util;

public  class ObjectList {
  private static final int DEFUALT_CAPACITY = 10;
  private int size;
  protected Object[] elementData;

  public ObjectList() {
    elementData = new Object[DEFUALT_CAPACITY]; // this 생략
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

  // 예외를 보고하는 메서드인 경우
  // 메서드 선언부에 어떤 예외를 보고하는지 표시해야한다.
  // => 오류가 발생했을 떄 예외 정보를 던지는 메서드인 경우,
  // 메서드 선언부에 던지는 예외 정보의 타입을 표시해야한다.
  public Object get(int index) throws Throwable{  // 단, 메서드 선언부에 어떤 예외를 던지는지 표시해야 한다!!
    if(index <0 || index >= size) {
      // 인덱스가 무효하면 예외를 발생시킨다.
      // 즉, 예외 정보를 객체에 담아서 호출한 쪽으로 던진다.
      // 예외 정보는 던질 수 있는 객체에 담아야 한다!
      // 던질 수 있는 객체? -> jaba.lang.Throwable 객체! => 그래서 Throwable 이다.
      // 단, 메서드 선언부에 어떤 예외를 던지는지 표시해야 한다.
      throw new Throwable("인덱스가 무효합니다!");
    }
    return elementData[index];
  }

  // 예외를 보고하는 메서드인 경우
  // 메서드 선언부에 어떤 예외를 보고하는지 표시해야한다.
  public boolean remove(int index)throws Throwable {
    if(index <0 || index >= size) {
      // 인덱스가 무효할 떄 false 를 리턴하는대신,
      // 예외정보를 호출자에게 던진다.
      // 예외 상황을 호출자에게 보고한다.
      throw new Throwable("인덱스가 무효합니다!");
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


  // private method는 밑에 두자
  private void grow() {
    int newSize = elementData.length + (elementData.length >> 1);
    Object[] newArray = new Object[newSize];
    for (int i = 0; i < elementData.length; i++) {
      newArray[i] = elementData[i];
    }
    elementData = newArray;
  }
}
