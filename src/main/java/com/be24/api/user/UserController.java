package com.be24.api.user;

import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import com.be24.api.ex01.Ex01ReadRes;
import com.be24.api.ex01.Ex01RegisterDtoRes;
import com.be24.api.utils.JsonParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKey;
import javax.sql.DataSource;
import java.net.http.HttpHeaders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class UserController implements Controller {
    DataSource ds;


    public UserController(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserController 실행");

        if (req.getRequestURI().contains("signup") && req.getMethod().equals("POST")) {
            UserSignupDto dto = JsonParser.from(req, UserSignupDto.class);

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
                        UserSignupDroRes returnDto = new UserSignupDroRes(
                                rs.getInt(1),
                                dto.getEmail(),
                                dto.getName()
                        );
                        return BaseResponse.success(returnDto);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }




        } else if (req.getRequestURI().contains("login") && req.getMethod().equals("POST")) {
            UserLoginDto dto = JsonParser.from(req, UserLoginDto.class);

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

                        String key = "sdfkhgsdkglnhoiurjdfoihgh397478thgwr390289gyrfhp90823uoevbdo823uvh4tf";
                        SecretKey encodedKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
                        String jwt = Jwts.builder()
                                .claim("idx",rs.getInt("idx"))
                                .claim("email",rs.getString("email"))
                                .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 300000)).signWith(encodedKey).compact();

                        resp.setHeader("Set-Cookie", "ATOKEN="+jwt + "; Path=/");

                        return BaseResponse.success("로그인 성공");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }
}
