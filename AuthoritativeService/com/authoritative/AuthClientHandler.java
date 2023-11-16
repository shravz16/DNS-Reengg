package com.authoritative;

import com.dns.dnsresolver.DNSQuery;
import com.handler.client.ClientHandler;
import com.handler.client.PrintLn;
import com.redis.utils.RedisServer;
import com.utils.file.CommonFileReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AuthClientHandler implements ClientHandler{
    PrintLn printLn;
    JSONObject json= CommonFileReader.getInstance().getJsonObject("db.json");
    public AuthClientHandler(PrintLn printLn){
        this.printLn=printLn;
    }
    @Override
    public void processMessage(byte[] bytes, int length) {
        String message=new String(bytes).trim();
        DNSQuery dnsQuery=createDNSQuestion(message);
        searchAndReturn(dnsQuery);
    }

    private void searchAndReturn(DNSQuery dnsQuery) {
        String domainName= dnsQuery.getDomainName().substring(0,dnsQuery.getDomainName().lastIndexOf("."));
        RedisServer rs=RedisServer.getInstance();
        String value=null;
        if(rs.hasKey(domainName)){

            value=rs.getKey(domainName);
        }
        else {
            JSONObject jsonObjectn = json.getJSONObject(domainName);
            JSONArray array = jsonObjectn.getJSONArray(dnsQuery.getType());
            value=array.getString(0);
            rs.setKey(domainName,value);
        }
        try {

            printLn.write(value.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private DNSQuery createDNSQuestion(String s) {
        System.out.println(s);
        DNSQuery query = new DNSQuery(s);
        return query;
    }
}
