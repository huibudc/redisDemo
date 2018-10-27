package com.huibudc;

import com.huibudc.facotry.Factory;
import org.junit.After;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisListTest {
    private final static Jedis jedis = Factory.jedis();

    @After
    public void tearDown() throws Exception {
        jedis.close();
    }

    @Test
    public void canAddDataToSet() {
        jedis.sadd("mySet", "hello", "redis");
        System.out.println(jedis.keys("myset"));
    }
}
