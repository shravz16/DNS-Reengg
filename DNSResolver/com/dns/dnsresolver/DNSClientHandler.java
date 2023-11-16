package com.dns.dnsresolver;

import com.handler.client.ClientHandler;
import com.handler.client.PrintLn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DNSClientHandler implements ClientHandler {

    int rootServerPort = 8011;
    PrintLn printLn;
    byte[] currentBytes;
    int currentIdx;
    private static final int ROOT_SERVER = 8011;
    Socket client = null;

    public DNSClientHandler(PrintLn printLn) {
        this.printLn = printLn;
        this.currentBytes = new byte[512];
        this.currentIdx = 0;
    }

    @Override
    public void processMessage(byte[] bytes, int length) {
        System.arraycopy(bytes, 0, currentBytes, currentIdx, length);
        currentIdx += length;
        boolean found = false;
        for (int i = 0; i < currentIdx; i++) {
            if (currentBytes[i] == 13) {
                found = true;
            }
            if (currentBytes[i] == 10 && found) {
                System.out.println(Arrays.toString(currentBytes));
                processDomainName(createDNSQuestion(new String(currentBytes)));
                currentBytes = new byte[512];
                currentIdx = 0;
            }
        }

    }

    private DNSQuery createDNSQuestion(String s) {


        DNSQuery query = new DNSQuery(s);

        return query;
    }

    private void processDomainName(DNSQuery query) {
        try {
            if (client == null) {
                client = new Socket("localhost", ROOT_SERVER);
            }
            OutputStream stream = client.getOutputStream();
            if (query.getDomainName().contains("com")) {
                stream.write("com".getBytes(StandardCharsets.UTF_8));
            } else if (query.getDomainName().contains("org")) {
                stream.write("org".getBytes(StandardCharsets.UTF_8));
            }

            InputStream ipstream = client.getInputStream();
            byte[] bytes = new byte[1024];
            ipstream.read(bytes);

            System.out.println(new String(bytes).trim());

            Integer tldServerPort = Integer.parseInt(new String(bytes).trim());
            Socket tldClient = new Socket("localhost", tldServerPort);

            stream = tldClient.getOutputStream();
            System.out.println(query.getDomainName());

            String[] domains= query.getDomainName().split("\\.");
            String mainDomain=domains[domains.length-2];
            stream.write(mainDomain.getBytes(StandardCharsets.UTF_8));

            ipstream = tldClient.getInputStream();
            bytes = new byte[1024];
            ipstream.read(bytes);

            String response=new String(bytes).trim();

            String ip=response.split(",")[0];
            String port=response.split(",")[1];
            Socket authServer=new Socket(ip.trim(),Integer.parseInt(port.trim()));

            OutputStream authOp=authServer.getOutputStream();
            InputStream authIp=authServer.getInputStream();

            authOp.write(query.toString().getBytes());
            bytes = new byte[1024];
            int k=authIp.read(bytes);
            System.out.println(new String(bytes));
            printLn.write((new String(bytes).trim()+" is the IP for "+query.getDomainName()).getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
