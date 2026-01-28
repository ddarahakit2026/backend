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
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        BoardDto dto = JsonParser.from(req, BoardDto.class);

        BoardDto returnDto = boardService.register(dto);

        return "";
    }
}
