package com.be24.api.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {
    String process(HttpServletRequest req, HttpServletResponse resp);
}
