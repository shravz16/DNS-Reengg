package com.handler.client;

public interface ClientHandlerFactory {

    ClientHandler getClientHandler(PrintLn printLn);
}
