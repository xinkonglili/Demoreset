package com.nk.demoreset.service;

import com.nk.demoreset.mapper.UserMapper;
import com.nk.demoreset.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        return userMapper.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
