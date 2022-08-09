package com.bitcamp.study;

import java.io.FileInputStream;

public class DataInputStream {
  FileInputStream in;
  public DataInputStream(FileInputStream in) {
    this.in = in;
  }

  public int readByte() throws Exception {
    return (byte) (in.read());
  }

  public short readShort() throws Exception{
    int result = (in.read() << 8 ) + in.read();
    return (short)result;
  }

  public int readInt() throws Exception{
    return (in.read() << 24) + (in.read() << 16) + (in.read() << 8) + in.read();
  }

  public long readLong() throws Exception {
    return ((long)in.read() << 56 ) 
        + ((long)in.read() << 48)
        + ((long)in.read() << 40)
        + ((long)in.read() << 32)
        + ((long)in.read() << 24)
        + ((long)in.read() << 16)
        + ((long)in.read() << 8)
        + (in.read());
  }
}
