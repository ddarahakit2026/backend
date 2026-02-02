package com.be24.api.user;

import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import com.be24.api.user.model.UserLoginDtoRes;
import com.be24.api.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class SocialController implements Controller {
    private final UserService userService;

    public SocialController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("code");

        try {
            UserLoginDtoRes returnDto = userService.kakaoLogin(code);

            String token = JwtUtil.createToken(returnDto.getIdx(), returnDto.getEmail());
            resp.setHeader("Set-Cookie", "ATOKEN="+token + "; Path=/");

            return BaseResponse.success(returnDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
