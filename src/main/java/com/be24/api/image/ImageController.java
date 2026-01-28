package com.be24.api.image;

import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

public class ImageController  implements Controller {
    // 서비스 클래스의 객체를 의존성 주입
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("ImageController 실행");
        Part part = null;
        try {
            String result = imageService.upload(req);
            return BaseResponse.success(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
