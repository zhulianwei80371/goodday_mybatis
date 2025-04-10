package org.goodday.mybatis.service;

import org.goodday.mybatis.entity.User;
import org.goodday.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User selectUserById(Long id) {
        return userMapper.selectByPrimaryKey(id );

    }

    public String insertUser(User user) {
        userMapper.insert(user);
        return "用户添加成功";
    }
}
