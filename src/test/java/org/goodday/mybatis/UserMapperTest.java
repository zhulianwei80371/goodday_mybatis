//package org.goodday.mybatis;
//
////import org.flywaydb.core.Flyway;
////import org.flywaydb.core.api.configuration.FluentConfiguration;
//import org.goodday.mybatis.config.CountryMapConfig;
//import org.goodday.mybatis.controller.UserController;
//import org.goodday.mybatis.entity.User;
//import org.goodday.mybatis.mapper.TargetMapper;
//import org.goodday.mybatis.mapper.UserMapper;
//import org.goodday.mybatis.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//@Import({UserService.class, CountryMapConfig.class})// 加上真实 Service
//public class UserMapperTest {
//
//    @MockBean
//    UserMapper userMapper; // Repository 层依然 mock
//    @MockBean
//    TargetMapper targetMapper;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testSelectUserById() throws Exception {
//
////        🧠 核心思路：
////        1.	@WebMvcTest(MyController.class) ➜ 只加载这个控制器的上下文。
////        2.	@MockBean ➜ 注入假的 Service，避免调用真正的业务逻辑。
////        3.	MockMvc ➜ 发起虚拟 HTTP 请求。
////        4.	mockMvc.perform(...) ➜ 模拟浏览器发请求，并断言返回值。
//                User mockUser1 = new User();
//        mockUser1.setId(8911L);
//        mockUser1.setName("张三");
//        mockUser1.setCountry("333");
//        User mockUser2 = new User();
//        mockUser2.setId(8921l);
//        mockUser2.setName("张四");
//        mockUser2.setCountry("444");
//        when(userMapper.selectByPrimaryKey(8911l)).thenReturn(mockUser1);
//        when(userMapper.selectByPrimaryKey(8921l)).thenReturn(mockUser2);
//        User user1 = userMapper.selectByPrimaryKey(8911l); // 查询 ds1.t_user_1
//        User user2 = userMapper.selectByPrimaryKey(8921l); // 查询 ds0.t_user
//        System.out.println("查询到的用户1：" + user1.toString());
//        System.out.println("查询到的用户2：" + user2.toString());
//
//        mockMvc.perform(get("/users/8911")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(8911))
//                .andExpect(jsonPath("$.name").value("张三"))
//                .andExpect(jsonPath("$.country").value("333"));
//
//    }
//}
