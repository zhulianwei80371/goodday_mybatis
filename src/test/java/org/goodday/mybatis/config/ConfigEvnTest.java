package org.goodday.mybatis.config;
//import org.flywaydb.core.Flyway;
//import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.goodday.mybatis.controller.UserController;
import org.goodday.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class ConfigEvnTest {
    @Autowired
    private Environment env;

    @Test
    public void testProfile() {
        System.out.println("当前激活的环境：" + Arrays.toString(env.getActiveProfiles()));
    }
}
