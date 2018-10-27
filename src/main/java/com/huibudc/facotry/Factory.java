package com.huibudc.facotry;

import redis.clients.jedis.Jedis;

public class Factory {
    private final static Jedis jedis = new Jedis("localhost");

    public static Jedis jedis(){
        return jedis;
    }
}
