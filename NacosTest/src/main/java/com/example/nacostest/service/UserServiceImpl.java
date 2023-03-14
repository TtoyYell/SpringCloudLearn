package com.example.nacostest.service;

import com.example.nacostest.entity.User;
import com.example.nacostest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2023/3/14 20:32
 */
@Service
public class UserServiceImpl {
    @Autowired
    UserMapper userMapper;

    public User getUserInfoByName(String name) {
        User user = userMapper.getUserInfoByName(name);
        return user;
    }

}
