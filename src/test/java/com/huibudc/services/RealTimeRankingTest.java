package com.huibudc.services;

import org.junit.Test;

public class RealTimeRankingTest {
    private final RealTimeRanking realTimeRanking = new RealTimeRanking("lb");

    @Test
    public void name() {
        realTimeRanking.getScore("user1");
    }
}