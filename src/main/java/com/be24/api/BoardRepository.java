package com.be24.api;

import com.be24.api.model.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BoardRepository {
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
                        // DB 실행결과를 새로운 DTO에 담아서 반환 (요청DTO와 응답DTO는 다른 경우가 많기 때문에 굳이 따로 만들어봤음)
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
