package com.huibudc.services;

import com.huibudc.facotry.Factory;
import com.sun.istack.internal.NotNull;
import redis.clients.jedis.Jedis;

public class RealTimeRanking {
    private final String zsetName;
    private final Jedis jedis;

    public RealTimeRanking(@NotNull String zsetName) {
        this.zsetName = zsetName;
        this.jedis = Factory.jedis();
    }

    public RealTimeRanking(@NotNull Jedis jedis, @NotNull String zsetName) {
        this.zsetName = zsetName;
        this.jedis = jedis;
    }

    public Double getScore(@NotNull String key){
        return jedis.zscore(zsetName, key);
    }
}
