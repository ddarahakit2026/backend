package com.be24.api.abc;

import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AbcController implements Controller {
    private final AbcService abcService;

    public AbcController(AbcService abcService) {
        this.abcService = abcService;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {

        if(req.getRequestURI().contains("create")) {
            abcService.create();
        } else if(req.getRequestURI().contains("read")) {
            abcService.read();
        } else if(req.getRequestURI().contains("update")) {
            abcService.update();
        } else if(req.getRequestURI().contains("delete")) {
            abcService.delete();
        }


        return null;
    }
}
