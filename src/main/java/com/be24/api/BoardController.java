package com.be24.api;


import com.be24.api.common.Controller;
import com.be24.api.model.BoardDto;
import com.be24.api.common.BaseResponse;
import com.be24.api.utils.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


// 라우팅 처리를 AppConfig에서 전부 처리하기 위해서 주석처리
//@WebServlet(urlPatterns = {"/board/register"})
public class BoardController implements Controller {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("BoardController 실행");

        return "";
    }
}
