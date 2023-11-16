package com.handler.client;

import java.io.IOException;

public interface PrintLn {
    void write(byte[] buff) throws IOException;
    void write(byte[] buff, int start, int len) throws IOException;
    void write(int b) throws IOException;

}
