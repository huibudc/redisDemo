package com.huibudc.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class RealTimeRankingTest {
    private final RealTimeRanking realTimeRanking = new RealTimeRanking("lb");

    @Test
    public void name() {
        realTimeRanking.getScore("user1");
    }
}