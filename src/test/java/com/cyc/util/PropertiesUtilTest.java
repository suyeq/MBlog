package com.cyc.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesUtilTest
{
    @Test
    public void getProperty()
    {
        Assert.assertEquals(PropertiesUtil.getProperty("redis.port"), "6379");
        Assert.assertEquals(PropertiesUtil.getIntegerProperty("redis.port"), 6379);
    }
}