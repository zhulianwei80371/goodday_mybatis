package org.goodday.mybatis.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getCitiesTest() throws Exception {
		mockMvc.perform(get("/cities/list").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[0]").value("上海")); // 你可以做更具体的断言
	}

	@Test
	public void getCities2() throws Exception {

		String responseContent = this.mockMvc.perform(get("/cities/list"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8); // 直接获取 JSON 字符串
		System.out.println("返回的城市列表: " + responseContent);

		// 使用 ObjectMapper 将 JSON 转换为 List
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> cityList = objectMapper.readValue(responseContent, new TypeReference<List<String>>() {});

		// 打印城市列表
		System.out.println("返回的城市列表: " + cityList);

		// 断言第一个城市
		Assertions.assertEquals("上海", cityList.get(0));
	}
}
