package com.authoritative;

import com.server.socket.Server;

public class AuthoritativeServerMain {
    public static void main(String[] a){
        Server authServer=new Server(7090,new AuthClientHandlerFactory());
    }
}
