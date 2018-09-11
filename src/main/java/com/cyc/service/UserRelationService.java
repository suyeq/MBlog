package com.cyc.service;

import com.cyc.pojo.User;

import java.util.Set;

public interface UserRelationService {

    void follow(Integer userId1, Integer userId2);

    /**
     * 得到关注者集合
     **/
    Set<User> getFollowers(Integer userId);

    /**
     * 得到正在关注集合
     **/
    Set<User> getFollowings(Integer userId);
}
