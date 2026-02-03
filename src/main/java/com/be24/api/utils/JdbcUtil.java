package com.be24.api.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    public static Connection getConnection() {

        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://10.10.10.30:3306/test", "root", "qwer1234");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
