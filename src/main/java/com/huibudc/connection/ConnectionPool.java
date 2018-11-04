package com.huibudc.connection;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ConnectionPool {

    private ConnectionPool() {

    }

    private static Jedis jedis = null;
    private static JedisPool pool;

    private static JedisPoolConfig config() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(2);
        config.setMaxTotal(10);
        config.setMaxWaitMillis(1000 * 2);
        config.setTestOnBorrow(true); // 设置是否测试连接池畅通;
        return config;
    }

    private static JedisPool jedisPool() {
        if (pool == null) {
            pool = new JedisPool(config(), "127.0.0.1", 6379, 1000 * 2);
        }
        return pool;
    }

    public static Jedis jedis() {
        if (jedis == null) {
            jedis = jedisPool().getResource();
            jedis.set("poolTest", "poolTest");
        }
        return jedis;
    }

    public static void destroyJedisPoll() {
        if (jedis != null) {
            jedis.close();
        }
        if (pool != null) {
            pool.destroy();
        }
    }
}

