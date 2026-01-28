package com.be24.api.image;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ImageRepository {
    public void save(String submittedFileName) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://10.10.10.30:3306/test", "root", "qwer1234");
            try (Statement stmt = conn.createStatement()) {
                Integer affectedRows = stmt.executeUpdate(
                        "INSERT INTO image (filename) VALUES ('" + submittedFileName + "')");

                if(affectedRows > 0) {
                    System.out.println("업로드 성공");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
