package com.be24.api.board;

import com.be24.api.board.model.BoardDto;

import java.sql.*;

public class BoardCpRepositoryImpl implements BoardRepository {
    @Override
    public BoardDto read(String boardIdx) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://10.10.10.30:3306/test", "root", "qwer1234");
            // SQL 인젝션 공격에 안전할 수 있게 Statement 대신에 PreparedStatement를 사용하게 변경
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM board LEFT JOIN reply ON board.idx=reply.boardIdx WHERE board.idx=?");
            pstmt.setInt(1, Integer.parseInt(boardIdx));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new BoardDto(
                        rs.getInt("board.idx"),
                        rs.getString("board.title"),
                        rs.getString("board.contents"));
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public BoardDto create(BoardDto dto) {
        return null;
    }
}
