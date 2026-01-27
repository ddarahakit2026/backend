package com.be24.api;


import com.be24.api.model.BoardDto;
import com.be24.api.common.BaseResponse;
import com.be24.api.utils.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet(urlPatterns = {"/board/register"})
public class BoardController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDto dto = JsonParser.from(req, BoardDto.class);

        // BoardService 클래스에 싱글톤 적용, new로 생성 못하니까 메소드로 객체 가져옴
        BoardService boardService = BoardService.getInstance();
        BoardDto returnDto = boardService.register(dto);

        BaseResponse res = BaseResponse.success(returnDto);
        resp.getWriter().write(JsonParser.from(res));
    }
}
