package com.be24.api;


import com.be24.api.common.Controller;
import com.be24.api.model.BoardDto;
import com.be24.api.common.BaseResponse;
import com.be24.api.utils.JsonParser;
import com.sun.source.tree.BreakTree;
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
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        BoardDto returnDto = null;

        if (req.getRequestURI().contains("register") && req.getMethod().equals("POST")) {
            BoardDto dto = JsonParser.from(req, BoardDto.class);
            returnDto = boardService.register(dto);
        } else if (req.getRequestURI().contains("read") && req.getMethod().equals("GET")) {
            String boardIdx = req.getParameter("idx");
            returnDto = boardService.read(boardIdx);
        }

        return BaseResponse.success(returnDto);
    }
}
