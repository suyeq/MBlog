package com.cyc.util;

import org.junit.Assert;
import org.junit.Test;

public class MD5Test {

    @Test
    public void checkMD5() {
        String s = "123lajfdaljfdsa";
        String md5 = MD5.getMD5(s);
        Assert.assertTrue(MD5.checkMD5(s, md5));
    }
}