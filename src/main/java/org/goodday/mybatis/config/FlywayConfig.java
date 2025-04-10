package org.goodday.mybatis.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FlywayConfig {

    @Primary // 标记为默认使用的 Bean
    @Bean(name = "flywayPrimary")
    public Flyway flywayPrimary() {
        return Flyway.configure()
                .dataSource("jdbc:mysql://192.168.1.48:3306/insound1?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "insound", "123456")
                .locations("classpath:db/migration/insound1")
                .baselineOnMigrate(true)
                .load();
    }

    @Bean(name = "flywaySecondary")
    public Flyway flywaySecondary() {
        return Flyway.configure()
                .dataSource("jdbc:mysql://192.168.1.48:3306/insound2?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "insound", "123456")
                .locations("classpath:db/migration/insound2")
                .baselineOnMigrate(true)
                .load();
    }
}