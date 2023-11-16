package com.dns.root;

import com.handler.client.ClientHandler;
import com.handler.client.PrintLn;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RootClientHandler implements ClientHandler {
    PrintLn printLn;
    public RootClientHandler(PrintLn printLn){
        this.printLn=printLn;
    }
    @Override
    public void processMessage(byte[] bytes, int length) {
        try {
            String root=new String(bytes);
            if(root.trim().equals("com")){
                printLn.write("8099".getBytes(StandardCharsets.UTF_8));
            }
            else if(root.trim().equals("org")){
                printLn.write("8100".getBytes(StandardCharsets.UTF_8));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
