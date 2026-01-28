package com.be24.api.image;

import jakarta.servlet.http.Part;

import java.io.IOException;

public class ImageService {
    // 레포지토리 클래스의 객체를 의존성 주입
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String upload(Part file) throws IOException {
        file.write("c:\\uploads\\"+file.getSubmittedFileName());

        imageRepository.save(file.getSubmittedFileName());

        return file.getSubmittedFileName();
    }
}
