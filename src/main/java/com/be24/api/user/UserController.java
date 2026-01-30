package com.be24.api.user;

import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import com.be24.api.user.model.UserLoginDto;
import com.be24.api.user.model.UserLoginDtoRes;
import com.be24.api.user.model.UserSignupDtoRes;
import com.be24.api.user.model.UserSignupDto;
import com.be24.api.utils.JsonParser;
import com.be24.api.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class UserController implements Controller {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserController 실행");

        if (req.getRequestURI().contains("signup") && req.getMethod().equals("POST")) {
            UserSignupDto dto = JsonParser.from(req, UserSignupDto.class);
            UserSignupDtoRes returnDto = userService.signup(dto);
            return BaseResponse.success(returnDto);
        } else if (req.getRequestURI().contains("login") && req.getMethod().equals("POST")) {
            UserLoginDto dto = JsonParser.from(req, UserLoginDto.class);
            UserLoginDtoRes returnDto = userService.login(dto);

            // 서비스로 부터 정상적으로 Dto를 반환 받으면(로그인 성공하면)
            if(returnDto != null) {
                // JwtUtil에서 토큰 생성 및 확인하도록 리팩토링
                String token = JwtUtil.createToken(returnDto.getIdx(), returnDto.getEmail());
                resp.setHeader("Set-Cookie", "ATOKEN="+token + "; Path=/");
                return BaseResponse.success(returnDto);
            } else {
                return BaseResponse.fail("로그인 실패");
            }

        }
        return null;
    }
}
