//package org.goodday.mybatis.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.goodday.mybatis.entity.User;
//import org.goodday.mybatis.service.UserService;
//import org.junit.jupiter.api.*;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerTest {
//	@Autowired
//	private MockMvc mockMvc;
//	@MockBean
//	private  UserService userService;
//	@MockBean
//	private UserMapper userMapper;
//
//
//	@Test
//	public void addUser() throws Exception {
//		User mockUser1 = new User();
//		mockUser1.setId(82222911L);
//		mockUser1.setName("张三");
//		mockUser1.setCountry("333");
//
//		// 使用 Mockito.any() 匹配任意 User 参数
////		when(userMapper.insert(Mockito.any(User.class))).thenReturn(1);
////		when(userService.insertUser(Mockito.any(User.class))).thenReturn("用户添加成功");
//
//
////		thenAnswer 方法：允许根据传入的参数动态返回结果。
////		invocation.getArgument(0)：获取传入的第一个参数。
////		条件判断：根据 User 对象的属性值（如 name），返回不同的结果。
////		通过这种方式，你可以实现根据输入动态返回不同的数据。
//		when(userService.insertUser(Mockito.any(User.class))).thenAnswer(invocation -> {
//			User user = invocation.getArgument(0); // 获取传入的参数
//			if ("张三".equals(user.getName())) {
//				return "用户张三添加成功"; // 如果名字是张三，返回特定消息
//			} else if ("李四".equals(user.getName())) {
//				return "用户李四添加成功"; // 如果名字是李四，返回特定消息
//			} else {
//				return "用户添加失败"; // 其他情况返回失败消息
//			}
//		});
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = objectMapper.writeValueAsString(mockUser1);
//
//		MvcResult result = this.mockMvc.perform(post("/users/add")
//						.content(json)
//						.contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk())
//				.andReturn();
//
//		System.out.println("Status: " + result.getResponse().getStatus());
//		System.out.println("Content-Type: " + result.getResponse().getContentType());
//		System.out.println("Response Body: " + result.getResponse().getContentAsString());
//	}
//}
