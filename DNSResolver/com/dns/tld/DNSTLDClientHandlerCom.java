package com.dns.tld;

import com.handler.client.ClientHandler;
import com.handler.client.PrintLn;
import com.utils.file.CommonFileReader;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class DNSTLDClientHandlerCom implements ClientHandler {
    PrintLn printLn;
    public DNSTLDClientHandlerCom(PrintLn printLn){
        this.printLn=printLn;
    }
    @Override
    public void processMessage(byte[] bytes, int length) {
        try {
            String domain=new String(bytes).trim();
            String ipAndPort=getAuthoritativeIpAndPort(domain);
            printLn.write(ipAndPort.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getAuthoritativeIpAndPort(String domain) {
         Properties p= CommonFileReader.getInstance().getProperty("com.properties");

         String ipPort=p.getProperty(domain);
        System.out.println(ipPort);
         return ipPort;
    }
}
