package org.niks.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    private static final HikariDataSource dataSource;
    private static final HikariConfig config = new HikariConfig();

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/task_manager_DB");
        config.setUsername("postgres");
        config.setPassword("niks");
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
