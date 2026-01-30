package com.be24.api.ex01;

import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Ex01Controller implements Controller {
    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Ex01Controller executed!!");
        return null;
    }
}
