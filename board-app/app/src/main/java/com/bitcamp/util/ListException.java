package com.bitcamp.util;

public class ListException extends RuntimeException{//Exception 얘를 상속 받으면 이 클레스 상속 받을 때 마다 throw 햅줘야함 귀찮아서 런타임으로!

  private static final long serialVersionUID = 1L ; // 나중에 설명...

  public ListException() {
    super();
    // TODO Auto-generated constructor stub
  }

  public ListException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    // TODO Auto-generated constructor stub
  }

  public ListException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  public ListException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  public ListException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  } 

}
