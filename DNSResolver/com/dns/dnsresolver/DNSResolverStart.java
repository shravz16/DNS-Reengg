package com.dns.dnsresolver;

import com.dns.dnsclient.DNSClientFactory;
import com.server.socket.Server;

public class DNSResolverStart {
    public static void main(String[] args) {
        Server s=new Server(8086, new DNSClientFactory());
    }
}