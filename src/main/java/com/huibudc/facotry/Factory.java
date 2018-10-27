package com.huibudc.facotry;

import redis.clients.jedis.Jedis;

public class Factory {
    private final static Jedis jedis = new Jedis("127.0.0.1", 6379);

    public static Jedis jedis() {
        return jedis;
    }
}
