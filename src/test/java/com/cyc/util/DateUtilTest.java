package com.cyc.util;

import org.junit.Test;

import java.util.Date;

public class DateUtilTest
{
    @Test
    public void formatDate()
    {
        System.out.println(DateUtil.formatDate(new Date()));
    }
}