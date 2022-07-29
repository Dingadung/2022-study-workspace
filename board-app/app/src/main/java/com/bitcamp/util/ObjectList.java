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

  public Object get(int index) throws Throwable{  
    if(index <0 || index >= size) {

      throw new Throwable("인덱스가 무효합니다!");
    }
    return elementData[index];
  }

  public boolean remove(int index)throws Throwable {
    if(index <0 || index >= size) {
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

  private void grow() {
    int newSize = elementData.length + (elementData.length >> 1);
    Object[] newArray = new Object[newSize];
    for (int i = 0; i < elementData.length; i++) {
      newArray[i] = elementData[i];
    }
    elementData = newArray;
  }
}
