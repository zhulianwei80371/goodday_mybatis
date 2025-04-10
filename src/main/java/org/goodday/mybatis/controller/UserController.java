package org.goodday.mybatis.controller;
import org.goodday.mybatis.entity.User;
import org.goodday.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userMapper.insertUser(user);
        return "用户添加成功";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userMapper.selectUserById(id);
    }
}