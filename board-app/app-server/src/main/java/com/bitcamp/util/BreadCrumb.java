package com.bitcamp.util;

import java.util.Stack;

public class BreadCrumb {

  public static Stack<String> menuStack = new Stack<>();

  public void put(String menu) {
    menuStack.push(menu);
  }

  public void pickUp() {
    menuStack.pop(); 
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for(String title: menuStack) {
      if(!builder.isEmpty()) {
        builder.append(" > ");
      }
      builder.append(title);
    }
    return  builder.toString();
  }// toString()
}

