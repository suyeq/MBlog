package com.cyc.service.imp;

import com.cyc.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
public class UserRelationServiceImpTest extends BaseTest
{
    @Autowired
    UserRelationServiceImp userRelationServiceImp;

    @Test
    public void getFollowers()
    {
        userRelationServiceImp.follow(1, 2);
        userRelationServiceImp.follow(1, 3);
        System.out.println(userRelationServiceImp.getFollowers(1));
        System.out.println(userRelationServiceImp.getFollowings(1));
        Assert.assertTrue(userRelationServiceImp.getFollowings(1).size() >= 2);
    }
}