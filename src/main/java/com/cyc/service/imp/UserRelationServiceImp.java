package com.cyc.service.imp;

import com.cyc.dao.UserMapper;
import com.cyc.dao.UserRelationDao;
import com.cyc.pojo.User;
import com.cyc.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserRelationServiceImp implements UserRelationService {

    @Autowired
    private UserRelationDao userRelationDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void follow(Integer userId1, Integer userId2) {
        userRelationDao.addFocus(userId1, userId2);
        userRelationDao.addFans(userId2, userId1);
    }

    @Override
    public Set<User> getFollowers(Integer userId) {
        return getUserSetByIdSet(userRelationDao.getFollowers(userId));
    }

    @Override
    public Set<User> getFollowings(Integer userId) {
        return getUserSetByIdSet(userRelationDao.getFollowings(userId));
    }

    private Set<User> getUserSetByIdSet(Set<Integer> ids) {
        Set<User> users = new HashSet<>();
        for (int id : ids) {
            users.add(userMapper.selectByPrimaryKey(id));
        }
        return users;
    }
}
