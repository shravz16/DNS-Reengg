package com.dns.root;

import com.server.socket.Server;

public class DNSRootMainStart {
    public static void main(String[] args){
        Server s= new Server(8011,new DNSRootFactory());


    }
}
