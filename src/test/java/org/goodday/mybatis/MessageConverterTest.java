package org.goodday.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MessageConverterTest {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Test
    public void testMappingJackson2HttpMessageConverterLoaded() {
        List<HttpMessageConverter<?>> converters = handlerAdapter.getMessageConverters();
        boolean jacksonConverterPresent = converters.stream()
                .anyMatch(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        System.out.println(jacksonConverterPresent);
        assertTrue(jacksonConverterPresent, "MappingJackson2HttpMessageConverter 未加载");
    }
}
