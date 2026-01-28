package com.be24.api.board;

import com.be24.api.board.model.BoardDto;

import javax.sql.DataSource;
import java.sql.*;

public class BoardCpRepositoryImpl implements BoardRepository {
    // DB 연결 객체를 의존성 주입으로 전달 받음
    private final DataSource ds;
    public BoardCpRepositoryImpl(DataSource dataSource) {
        this.ds = dataSource;
    }

    @Override
    public BoardDto read(String boardIdx) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            // DriverManager.getConnection()으로 연결을 새로 생성하는거 대신에 CP에서 연결을 받아와서 사용
            PreparedStatement pstmt = ds.getConnection().prepareStatement(
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
