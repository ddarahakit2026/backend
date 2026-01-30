package com.be24.api.ex01;

import com.be24.api.board.model.BoardDto;
import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import com.be24.api.utils.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ex01Controller implements Controller {
    private final DataSource ds;

    public Ex01Controller(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {

        if (req.getRequestURI().contains("read") && req.getMethod().equals("GET")) {
            System.out.println("DB에서 1개만 조회하는 코드");
        } else if (req.getRequestURI().contains("list") && req.getMethod().equals("GET")) {
            System.out.println("DB에서 목록을 조회하는 코드");
        } else if (req.getRequestURI().contains("register") && req.getMethod().equals("POST")) {
            Ex01RegisterDto dto = JsonParser.from(req, Ex01RegisterDto.class);

            try {
                Class.forName("org.mariadb.jdbc.Driver");

                // DB 연결 객체를 다 사용하고 나면 반납할 수 있도록 수정
                try (Connection conn = ds.getConnection()) {
                    PreparedStatement pstmt = conn.prepareStatement(
                            "INSERT INTO ex01 (data01, data02, data03) VALUES (?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, dto.getData01());
                    pstmt.setInt(2, dto.getData02());
                    pstmt.setString(3, dto.getData03());


                    pstmt.executeUpdate();
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if(rs.next()) {
                        Ex01RegisterDtoRes returnDto = new Ex01RegisterDtoRes(
                                rs.getInt(1),
                                dto.getData01(),
                                dto.getData02(),
                                dto.getData03()
                        );
                        return BaseResponse.success(returnDto);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }



        }
        return null;
    }
}
