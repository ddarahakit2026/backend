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
        // JSON 형식으로 클라이언트가 전달한 요청을 처리
        BoardDto dto = JsonParser.from(req, BoardDto.class);

        BoardService boardService = new BoardService();
        // 서비스의 결과를 반환받게 변경
        BoardDto returnDto = boardService.register(dto);

        // JSON 형식으로 응답을 처리
        BaseResponse res = BaseResponse.success(returnDto);
        resp.getWriter().write(JsonParser.from(res));
    }
}
