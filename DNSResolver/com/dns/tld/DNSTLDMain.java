package com.dns.tld;

import com.server.socket.Server;

public class DNSTLDMain {
    public static void main(String args[]){

        Server s=new Server(8099,new DNSTLDFactoryCom());
        Server s1=new Server(8100,new DNSTLDFactoryOrg());
    }
}
