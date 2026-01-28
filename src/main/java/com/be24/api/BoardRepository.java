package com.be24.api;

import com.be24.api.model.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BoardRepository {

    public BoardDto read(String boardIdx) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://10.10.10.30:3306/test", "root", "qwer1234");
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM board LEFT JOIN reply ON board.idx=reply.boardIdx WHERE board.idx=" + boardIdx);

                if (rs.next()) {
                    return new BoardDto(
                            rs.getInt("board.idx"),
                            rs.getString("board.title"),
                            rs.getString("board.contents"));
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

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
