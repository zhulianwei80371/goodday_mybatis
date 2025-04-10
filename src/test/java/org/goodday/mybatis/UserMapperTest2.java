package org.goodday.mybatis;

import org.flywaydb.core.Flyway;
import org.goodday.mybatis.entity.User;
import org.goodday.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest2 {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertUser() {
        User mockUser1 = new User();
        mockUser1.setId(891111L);
        mockUser1.setName("张三");
        mockUser1.setCountry("333");
        User mockUser2 = new User();
        mockUser2.setId(892111l);
        mockUser2.setName("张四");
        mockUser2.setCountry("444");

        userMapper.insertUser(mockUser1); // 插入到 ds1.t_user_1
        userMapper.insertUser(mockUser2); // 插入到 ds0.t_user_0
        System.out.println("用户插入成功！");
    }

    public static void main(String[] args) {
        {
            Flyway flyway = Flyway.configure()
                    .dataSource("jdbc:mysql://192.168.253.129:3306/insound2?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "insound", "123456")
                    .locations("classpath:db/migration/insound2") // 确保路径正确
                    .baselineOnMigrate(true) // 自动初始化 schema 历史表
                    .load();
            try {
                flyway.migrate();
                System.out.println("数据库迁移成功！");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("数据库迁移失败：" + e.getMessage());
            }
        }
    }
}