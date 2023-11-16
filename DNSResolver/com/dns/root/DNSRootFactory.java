package com.dns.root;

import com.handler.client.ClientHandler;
import com.handler.client.ClientHandlerFactory;
import com.handler.client.PrintLn;

public class DNSRootFactory implements ClientHandlerFactory {
    @Override
    public ClientHandler getClientHandler(PrintLn printLn) {
        return new RootClientHandler(printLn);
    }
}
