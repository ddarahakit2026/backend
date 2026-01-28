package com.be24.api.user;

import com.be24.api.common.Controller;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserController implements Controller {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserController 실행");
        return "";
    }
}
