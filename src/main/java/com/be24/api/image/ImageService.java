package com.be24.api.image;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;

public interface ImageService {
    public String upload(HttpServletRequest req) throws IOException, ServletException;
}
