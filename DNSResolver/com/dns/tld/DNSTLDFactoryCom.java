package com.dns.tld;

import com.handler.client.ClientHandler;
import com.handler.client.ClientHandlerFactory;
import com.handler.client.PrintLn;

public class DNSTLDFactoryCom implements ClientHandlerFactory {
    @Override
    public ClientHandler getClientHandler(PrintLn printLn) {
        return new DNSTLDClientHandlerCom(printLn);
    }
}
