package com.be24.api;

import com.be24.api.model.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BoardRepository {
    // -- BoardRepository 클래스에 싱글톤 디자인 패턴 적용--
    private BoardRepository() {

    }

    private static class SingletonHolder {
        private static final BoardRepository instance = new BoardRepository();
    }

    public static BoardRepository getInstance() {
        return SingletonHolder.instance;
    }
    // -- BoardRepository 클래스에 싱글톤 디자인 패턴 적용--

    public BoardDto create(BoardDto dto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://10.10.10.30:3306/test", "root", "qwer1234");
            try (Statement stmt = conn.createStatement()) {
                Integer affectedRows = stmt.executeUpdate(
                        "INSERT INTO board (title, contents) VALUES ('" + dto.getTitle() + "','" + dto.getContents() + "')",
                        Statement.RETURN_GENERATED_KEYS
                );
                if (affectedRows > 0) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    if(rs.next()) {
                        BoardDto returnDto = new BoardDto(
                                rs.getInt(1),
                                dto.getTitle(),
                                dto.getContents()
                        );
                        return returnDto;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
