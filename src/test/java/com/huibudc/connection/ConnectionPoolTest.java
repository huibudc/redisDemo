package com.huibudc.connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ConnectionPoolTest {
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String SEX = "sex";
    private Jedis jedis;

    @Before
    public void setUp() throws Exception {
        jedis = ConnectionPool.jedis();
        jedis.flushAll(); // clear data
        prepareData();
    }

    @After
    public void tearDown() throws Exception {
        ConnectionPool.destroyJedisPoll();
    }

    @Test
    public void getKeyTest() {
        assertEquals("leon", jedis.get(NAME));
        assertEquals("28", jedis.get(AGE));
        assertEquals("M", jedis.get(SEX));
    }

    @Test
    public void deleteKeyTest() {
        assertEquals("leon", jedis.get(NAME));
        jedis.del(NAME);
        assertNull(jedis.get(NAME));
    }

    @Test
    public void keyExpireTest() throws InterruptedException {
        assertEquals("leon", jedis.get(NAME));
        jedis.expire(NAME, 1);
        Thread.sleep(1000);
        assertNull(jedis.get(NAME));
    }

    @Test
    public void keyTtlTest() throws InterruptedException {
        assertEquals("leon", jedis.get(NAME));
        jedis.expire(NAME, 2);
        Thread.sleep(1000);
        assertEquals(1, (long) jedis.ttl(NAME));
        assertEquals("leon", jedis.get(NAME));
        Thread.sleep(1000);
        assertEquals(0, (long) jedis.ttl(NAME));
        assertNull(jedis.get(NAME));
    }

    private void prepareData() {
        jedis.exists(NAME);
        jedis.exists(AGE);
        jedis.exists(SEX);

        jedis.set(NAME, "leon");
        jedis.set(AGE, "28");
        jedis.set(SEX, "M");
    }
}