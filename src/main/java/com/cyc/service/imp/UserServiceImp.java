package com.cyc.service.imp;

import com.cyc.dao.UserMapper;
import com.cyc.pojo.User;
import com.cyc.service.UserService;
import com.cyc.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void addUser(String username, String password, int headpic) {
        User user = new User();
        user.setUsername(username);
        user.setMd5password(MD5.getMD5(password));
        user.setHeadpic(headpic);
        userMapper.insert(user);
    }

    @Override
    public boolean checkPassword(String username, String password) {
        String md5password = MD5.getMD5(password);
        User user = userMapper.selectByUserName(username);
        return user != null && md5password.equals(user.getMd5password());
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public User getUserByUserId(Integer userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

    @Override
    public boolean checkUserNameIllegal(String username) {
        return userMapper.selectByUserName(username) == null;
    }

    @Override
    public void loginSuccess(String username) {
    }
}
