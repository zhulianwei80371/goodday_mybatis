//package org.goodday.mybatis;
//
//import org.flywaydb.core.Flyway;
//import org.goodday.mybatis.entity.User;
//import org.goodday.mybatis.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UserMapperTest2 {
//
//    @Mock
//    private UserMapper userMapper;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Test
//    public void testInsertUser() {
//        User mockUser1 = new User();
//        mockUser1.setId(12891111L);
//        mockUser1.setName("张三");
//        mockUser1.setCountry("333");
//        User mockUser2 = new User();
//        mockUser2.setId(13892111l);
//        mockUser2.setName("张四");
//        mockUser2.setCountry("444");
//
//        when(userMapper.insert(mockUser1)).thenReturn(1);
//        when(userMapper.insert(mockUser2)).thenReturn(1);
//
//        userService.insertUser(mockUser1); // 插入到 ds1.t_user_1
//        userService.insertUser(mockUser2); // 插入到 ds0.t_user
//        System.out.println("用户插入成功！");
//    }
//
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
//}