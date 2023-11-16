package com.authoritative;

import com.handler.client.ClientHandler;
import com.handler.client.ClientHandlerFactory;
import com.handler.client.PrintLn;

public class AuthClientHandlerFactory implements ClientHandlerFactory {
    @Override
    public ClientHandler getClientHandler(PrintLn printLn) {
        return new AuthClientHandler(printLn);
    }
}
