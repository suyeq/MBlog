package com.cyc.common;

import org.junit.Assert;
import org.junit.Test;

public class RedisPoolTest
{
    @Test
    public void set()
    {
        String k = "hello";
        String v = "world";
        RedisPool.set(k, v);
        Assert.assertEquals(RedisPool.get(k), v);
    }
}