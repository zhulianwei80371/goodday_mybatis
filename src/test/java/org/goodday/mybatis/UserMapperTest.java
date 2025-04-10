package org.goodday.mybatis;

//import org.flywaydb.core.Flyway;
//import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.goodday.mybatis.entity.User;
import org.goodday.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

//    @Test
//    public void testInsertUser() {
//        userMapper.insertUser(8911l, "张三1","美国"); // 插入到 ds1.t_user_1
//        userMapper.insertUser(9011l,  "张四2","中国"); // 插入到 ds0.t_user_0
//        System.out.println("用户插入成功！");
//    }

//    @Test
//    public void testSelectUserById() {
//        User user1 = userMapper.selectUserById(8911l); // 查询 ds1.t_user_1
//        User user2 = userMapper.selectUserById(9011l); // 查询 ds0.t_user_0
//        System.out.println("查询到的用户1：" + user1.toString());
//        System.out.println("查询到的用户2：" + user2.toString());
//    }

//    public static void main(String[] args) {
//        {
//            Flyway flyway = Flyway.configure()
//                    .dataSource("jdbc:mysql://192.168.253.129:3306/insound2?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "insound", "123456")
//                    .locations("classpath:db/migration/insound2") // 确保路径正确
//                    .baselineOnMigrate(true) // 自动初始化 schema 历史表
//                    .load();
//            try {
//                flyway.migrate();
//                System.out.println("数据库迁移成功！");
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.err.println("数据库迁移失败：" + e.getMessage());
//            }
//        }
//    }

}