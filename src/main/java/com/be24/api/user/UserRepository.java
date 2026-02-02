package com.be24.api.user;

import com.be24.api.common.BaseResponse;
import com.be24.api.user.model.UserLoginDto;
import com.be24.api.user.model.UserLoginDtoRes;
import com.be24.api.user.model.UserSignupDto;
import com.be24.api.user.model.UserSignupDtoRes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class UserRepository {
    private final DataSource ds;

    public UserRepository(DataSource ds) {
        this.ds = ds;
    }

    public UserSignupDtoRes save(UserSignupDto dto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            // DB 연결 객체를 다 사용하고 나면 반납할 수 있도록 수정
            try (Connection conn = ds.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO user (email, name, password) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, dto.getEmail());
                pstmt.setString(2, dto.getName());
                pstmt.setString(3, dto.getPassword());


                pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()) {
                    UserSignupDtoRes returnDto = new UserSignupDtoRes(
                            rs.getInt(1),
                            dto.getEmail(),
                            dto.getName()
                    );
                    return returnDto;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public UserLoginDtoRes findByEmailAndPasssword(UserLoginDto dto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conn = ds.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * FROM user WHERE email=? AND password=?");
                pstmt.setString(1, dto.getEmail());
                pstmt.setString(2, dto.getPassword());

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // 토큰 생성해서 응답에 토큰을 넣어줘야 한다.
                    UserLoginDtoRes returnDto = new UserLoginDtoRes(
                            rs.getInt("idx"),
                            rs.getString("email"),
                            rs.getString("name")
                    );

                    return returnDto;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
