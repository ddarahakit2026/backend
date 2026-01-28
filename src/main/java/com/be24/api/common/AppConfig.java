package com.be24.api.common;

import com.be24.api.board.*;
import com.be24.api.image.ImageController;
import com.be24.api.image.ImageRepository;
import com.be24.api.image.ImageService;
import com.be24.api.user.UserController;
import com.zaxxer.hikari.HikariDataSource;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private final Map<String, Controller> controllerMap = new HashMap<>();

    private final HikariDataSource ds = new HikariDataSource();

    private final BoardRepository boardRepository = new BoardCpRepositoryImpl(ds);
    private final BoardService boardService = new BoardService(boardRepository);
    private final BoardController boardController = new BoardController(boardService);

    private final UserController userController = new UserController();


    // 이미지 처리 기능
    private final ImageRepository imageRepository = new ImageRepository();
    private final ImageService imageService = new ImageService(imageRepository);
    private final ImageController imageController = new ImageController(imageService);


    public AppConfig() {
        ds.setJdbcUrl("jdbc:mariadb://10.10.10.30:3306/test");
        ds.setUsername("root");
        ds.setPassword("qwer1234");

        controllerMap.put("/board/register", boardController);
        controllerMap.put("/board/read", boardController);
        controllerMap.put("/user/signup", userController);
        controllerMap.put("/user/login", userController);

        // URI 맵핑
        controllerMap.put("/image/upload", imageController);
    }

    // 특정 uri를 이용해서 특정 컨트롤러 객체를 반환하는 메소드
    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}
