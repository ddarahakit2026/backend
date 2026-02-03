package com.be24.api.utils;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HikariCpUtil {
    private static final HikariDataSource ds = new HikariDataSource();

    public static DataSource getConnection() {
        ds.setJdbcUrl("jdbc:mariadb://10.10.10.30:3306/test");
        ds.setUsername("root");
        ds.setPassword("qwer1234");

        try {
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
