package org.goodday.mybatis.service;

import org.goodday.mybatis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MultiDataSourceServiceTest {
    @Autowired MultiDataSourceService multiDataSourceService;
    @Test
    public void testMultiDataSource() {
        List<User> all = multiDataSourceService.queryAll();
        // 循环遍历打印
        for (User user : all) {
            System.out.println("user = " + user);
        }
    }
}