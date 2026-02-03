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

        return null;
    }
}
