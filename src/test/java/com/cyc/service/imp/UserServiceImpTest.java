package com.cyc.service.imp;

import com.cyc.BaseTest;
import com.cyc.dao.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
public class UserServiceImpTest extends BaseTest
{
    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    UserMapper userMapper;

    private String username = "root";
    private String password = "123";
    private int headpic = 1;

    @Test
    @Rollback()
    public void addUer()
    {
        userServiceImp.addUser(username, password, headpic);
    }

    @Test
    @Rollback()
    public void checkPassword()
    {
        Assert.assertTrue(userServiceImp.checkPassword(username, password));
        Assert.assertFalse(userServiceImp.checkPassword(username, password + "xx"));
        Assert.assertFalse(userServiceImp.checkPassword(username + "xx", password + "xx"));
    }
}