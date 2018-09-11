package com.cyc.dao;

import com.cyc.BaseTest;
import com.cyc.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
public class UserMapperTest extends BaseTest
{
    @Autowired
    UserMapper userMapper;

    @Test
    public void insert()
    {
        User user = new User();
        user.setUsername("zheng");
        user.setHeadpic(1);
        user.setMd5password("123");
        int affectRows = userMapper.insert(user);
        System.out.println("主键：" + user.getUserid());
        Assert.assertEquals(1, affectRows);
    }

    @Test
    public void selectByPrimaryKey()
    {
        Integer userid = 1;
        User user = userMapper.selectByPrimaryKey(userid);
        Assert.assertEquals(user.getUserid(), userid);
    }
}