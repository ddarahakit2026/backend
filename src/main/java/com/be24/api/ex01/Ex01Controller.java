package com.be24.api.ex01;

import com.be24.api.board.model.BoardDto;
import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import com.be24.api.utils.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

public class Ex01Controller implements Controller {
    private final DataSource ds;

    public Ex01Controller(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {

        if (req.getRequestURI().contains("read") && req.getMethod().equals("GET")) {
            System.out.println("DB에서 1개만 조회하는 코드");
        } else if (req.getRequestURI().contains("list") && req.getMethod().equals("GET")) {
            System.out.println("DB에서 목록을 조회하는 코드");
        } else if (req.getRequestURI().contains("register") && req.getMethod().equals("POST")) {
            Ex01RegisterDto dto = JsonParser.from(req, Ex01RegisterDto.class);
            System.out.println("DB에 등록하는 코드");


        }
        return null;
    }
}
