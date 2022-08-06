package com.eomcs.design_pattern.iterator.after1;

public class ArrayListIterator<E> implements Iterator<E>{
  int index=0;
  ArrayList<E> list;
  public ArrayListIterator(ArrayList<E> list) {
    // TODO Auto-generated constructor stub
    this.list = list;
  }
  @Override
  public boolean hasNext() {
    // TODO Auto-generated method stub
    return list.size == index;
  }

  @Override
  public E next() {
    // TODO Auto-generated method stub
    return list.get(index++);
  }

}
