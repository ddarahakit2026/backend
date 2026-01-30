package com.be24.api.common;

import com.be24.api.board.BoardController;
import com.be24.api.board.BoardRepository;
import com.be24.api.board.BoardService;
import com.be24.api.user.UserController;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private final Map<String, Controller> controllerMap = new HashMap<>();

    private final HikariDataSource ds = new HikariDataSource();

    private final BoardRepository boardRepository = new BoardCpRepositoryImpl(ds);
    private final BoardService boardService = new BoardService(boardRepository);
    private final BoardController boardController = new BoardController(boardService);

    private final UserController userController = new UserController();
    // 처음 객체가 생성될 때 controllerMap에 uri를 키로 컨트롤러 객체를 값으로 저장
    public AppConfig() {
        ds.setJdbcUrl("jdbc:mariadb://10.10.10.30:3306/test");
        ds.setUsername("root");
        ds.setPassword("qwer1234");

        controllerMap.put("/board/register", boardController);
        controllerMap.put("/board/read", boardController);
        controllerMap.put("/user/signup", userController);
        controllerMap.put("/user/login", userController);

        controllerMap.put("/ex01/read", ex01Controller);
        controllerMap.put("/ex01/list", ex01Controller);
        controllerMap.put("/ex01/register", ex01Controller);

    }

    // 특정 uri를 이용해서 특정 컨트롤러 객체를 반환하는 메소드
    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}
