package com.be24.api.common;

import com.be24.api.BoardController;
import com.be24.api.BoardRepository;
import com.be24.api.BoardRepositoryImpl;
import com.be24.api.BoardService;
import com.be24.api.user.UserController;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private final Map<String, Controller> controllerMap = new HashMap<>();
    private final BoardRepository boardRepository = new BoardRepositoryImpl();
    private final BoardService boardService = new BoardService(boardRepository);


    // 처음 객체가 생성될 때 controllerMap에 uri를 키로 컨트롤러 객체를 값으로 저장
    public AppConfig() {
        controllerMap.put("/board/register", new BoardController(boardService));
        controllerMap.put("/user/signup", new UserController());
    }

    // 특정 uri를 이용해서 특정 컨트롤러 객체를 반환하는 메소드
    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}
