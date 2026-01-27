package com.be24.api;


import com.be24.api.model.BoardDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet(urlPatterns = {"/board/register"}) // /board/read 주소랑, /board/register 주소로 클라이어트가 요청을 보내면 실행되는 코드
public class BoardController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 데이터 전달받아서 DTO에 담기
        // DTO로 역할 분리
        BoardDto dto = BoardDto.toDto(req);

        // 서비스의 메소드에 복잡한 로직을 처리하는 부분의 역할을 분리하고 호출해서 사용
        BoardService boardService = new BoardService();
        boardService.register(dto); // 데이터를 처리할 수 있도록 매개변수로 DTO 전달

        // 응답하는 부분
        resp.getWriter().write("잘 저장됨");

    }
}
