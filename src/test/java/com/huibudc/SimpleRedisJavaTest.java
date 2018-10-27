package com.huibudc;

import com.huibudc.facotry.Factory;
import org.junit.After;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

public class SimpleRedisJavaTest {
    private final static Jedis jedis = Factory.jedis();

    @After
    public void tearDown() throws Exception {
        jedis.del("name");
    }

    @Test
    public void connectToServerSuccessfully() {
        assertEquals("PONG", jedis.ping());
    }

    @Test
    public void canSetStringData() {
        assertEquals("OK", jedis.set("name", "leon"));
    }

    @Test
    public void canGetStringData() {
        jedis.set("name", "leon");
        assertEquals("leon", jedis.get("name"));
    }

    @Test
    public void canSetKeyExpire() throws InterruptedException {
        jedis.set("name", "leon");
        assertEquals("leon", jedis.get("name"));
        jedis.expire("name", 3);
        Thread.sleep(3001);
        assertNull(jedis.get("name"));
    }

    @Test
    public void canCheckIsKeyExists() {
        assertFalse(jedis.exists("name"));
        jedis.set("name", "leon");
        assertTrue(jedis.exists("name"));
    }

}