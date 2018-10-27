package com.huibudc;

import com.huibudc.facotry.Factory;
import redis.clients.jedis.Jedis;


public class SimpleRedisJava {
    private final static Jedis jedis = Factory.jedis();

    public static void main(String[] args) {
        System.out.println("is server running ? " + isServerRunning());
        prepareData();
        //get string data
        System.out.println("the value of name is " + jedis.get("name"));
        System.out.println("the value of job is " + jedis.get("job"));

        // get list data
        jedis.lrange("site-list", 0, 2).forEach(System.out::println);
        //get keyset
        jedis.keys("*").forEach(System.out::println);
    }

    private static boolean isServerRunning() {
        return "PONG".equals(jedis.ping());
    }

    private static void prepareData() {
        //string data
        jedis.set("name", "leon");
        jedis.set("job", "software engineer");
        //list data
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
    }
}
