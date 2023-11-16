package com.dns.tld;

import com.handler.client.ClientHandler;
import com.handler.client.PrintLn;
import com.utils.file.CommonFileReader;

import java.io.IOException;
import java.util.Properties;


public class DNSTLDClientHandlerOrg implements ClientHandler {
    PrintLn printLn;
    public DNSTLDClientHandlerOrg(PrintLn printLn){
        this.printLn=printLn;
    }
    @Override
    public void processMessage(byte[] bytes, int length) {
        try {
            String domain=new String(bytes);
            String ipAndPort=getAuthoritativeIpAndPort(domain);
            printLn.write(ipAndPort.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAuthoritativeIpAndPort(String domain) {
        Properties p= CommonFileReader.getInstance().getProperty("org.properties");
        String ipPort=p.getProperty(domain);
        return ipPort;
    }
}
