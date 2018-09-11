package com.cyc.dao;

import com.cyc.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
public class UserRelationDaoTest extends BaseTest
{
    @Autowired
    UserRelationDao userRelationDao;

    @Test
    public void getFans()
    {
        int userId = 1;
        userRelationDao.addFans(userId, 2);
        userRelationDao.addFans(userId, 3);
        userRelationDao.addFans(userId, 4);
        System.out.println(userRelationDao.getFollowers(userId));
        Assert.assertTrue(userRelationDao.getFollowers(userId).size() >= 3);
    }
}