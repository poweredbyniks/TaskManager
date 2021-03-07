package org.niks.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig("application.properties");
        return new HikariDataSource(config);
    }
}
