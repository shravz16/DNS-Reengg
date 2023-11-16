package com.redis.utils;


import redis.clients.jedis.JedisPooled;

public class RedisServer {
    private final static RedisServer redisServer=new RedisServer();

    JedisPooled jedis;
    private RedisServer(){
        connectToRedis();
    }

    public static RedisServer getInstance(){
        return redisServer;
    }

    private void connectToRedis() {
        this.jedis = new JedisPooled("localhost", 6379);

    }

    public void setKey(String key,String value){
        System.out.println("Redis set");
        jedis.set(key,value);
    }

    public String getKey(String key){
        System.out.println("Redis get");
       return jedis.get(key);
    }

    public boolean hasKey(String key){
        System.out.println("Redis check");
        return jedis.exists(key);
    }


}
