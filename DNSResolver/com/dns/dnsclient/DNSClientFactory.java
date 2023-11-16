package com.dns.dnsclient;

import com.dns.dnsresolver.DNSClientHandler;
import com.handler.client.ClientHandler;
import com.handler.client.ClientHandlerFactory;
import com.handler.client.PrintLn;

public class DNSClientFactory implements ClientHandlerFactory {
    @Override
    public ClientHandler getClientHandler(PrintLn printLn) {
        return new DNSClientHandler(printLn);
    }
}
