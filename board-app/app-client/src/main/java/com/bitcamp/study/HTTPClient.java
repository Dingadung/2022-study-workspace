package com.bitcamp.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HTTPClient {
  public static void main(String[] args) throws Exception {
    try(
        Socket socket = new Socket("www.etnews.co.kr", 80);
        BufferedReader in2 = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        PrintStream out2 = new PrintStream(socket.getOutputStream());){


    }
  }
}