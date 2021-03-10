package org.niks.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    @Value("${spring.datasource.url}")
    private String JdbcUrl;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(JdbcUrl);
        ds.setUsername(userName);
        ds.setPassword(password);
        return ds;
    }
}
