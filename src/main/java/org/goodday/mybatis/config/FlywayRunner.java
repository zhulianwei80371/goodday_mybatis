package org.goodday.mybatis.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FlywayRunner implements CommandLineRunner {

    @Autowired
    @Qualifier("flywayPrimary")
    private Flyway flywayPrimary;
//
    @Autowired
    @Qualifier("flywaySecondary")
    private Flyway flywaySecondary;
//
    @Override
    public void run(String... args) throws Exception {
        System.out.println("🔄 Flyway migration started...");

        flywayPrimary.migrate();
        System.out.println("✅ Primary DB migrated");

        flywaySecondary.migrate();
        System.out.println("✅ Secondary DB migrated");

        System.out.println("🚀 Flyway migration complete!");
    }
}