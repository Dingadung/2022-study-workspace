package stackTest.util;

public class Stack extends LinkedList{
  public  void push(Object obj) {
    add(obj);
  }

  public Object pop() {
    return remove(size() -1);
  }

  public boolean empty() {
    return size == 0;
  }

  public Object peek() {
    return get(size() -1);
  }

  @Override
  public String toString() {
    StringBuffer buf = new StringBuffer();
    for(int i=0;i<size();i++) {
      if(buf.length()>0) {
        buf.append(" < ");
      }

      buf.append(get(i));
    }

    return buf.toString();
  }
}
