package com.medical.rendezvousservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.flywaydb.core.Flyway;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .baselineOnMigrate(true)
                .locations("classpath:db/migration")
                .dataSource(
                        "jdbc:h2:mem:rendezvousdb",
                        "sa",
                        ""
                )
                .load();

        flyway.migrate();
        return flyway;
    }
}
