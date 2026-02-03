package com.be24.api.common;

import com.be24.api.abc.AbcController;
import com.be24.api.abc.AbcRepositoryHikariCpImpl;
import com.be24.api.abc.repository.AbcRepository;
import com.be24.api.abc.AbcRepositoryJdbcImpl;
import com.be24.api.abc.AbcService;
import com.be24.api.board.BoardController;
import com.be24.api.board.BoardCpRepositoryImpl;
import com.be24.api.board.BoardRepository;
import com.be24.api.board.BoardService;
import com.be24.api.ex01.Ex01Controller;
import com.be24.api.orders.OrdersController;
import com.be24.api.user.SocialController;
import com.be24.api.user.UserController;
import com.be24.api.user.UserRepository;
import com.be24.api.user.UserService;
import com.be24.api.utils.HikariCpUtil;
import com.be24.api.utils.JdbcUtil;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.



public class AppConfig {

    private final Map<String, Controller> controllerMap = new HashMap<>();



    private final BoardRepository boardRepository = new BoardCpRepositoryImpl(HikariCpUtil.getConnection());
    private final BoardService boardService = new BoardService(boardRepository);
    private final BoardController boardController = new BoardController(boardService);

    private final UserRepository userRepository = new UserRepository(HikariCpUtil.getConnection());
    private final UserService userService = new UserService(userRepository);
    private final UserController userController = new UserController(userService);

    private final Ex01Controller ex01Controller = new Ex01Controller(HikariCpUtil.getConnection());

    private final SocialController socialController = new SocialController(userService);
    private final OrdersController ordersController = new OrdersController();


    // DIP : 클래스를 참조하지 말고 상위 인터페이스를 참조해라
    // OCP : 서비스, 컨트롤러 기존 레포지토리 코드 전혀 건드리지 않고 레포지토리 객체만 바꿔주면 기능이 완전 바뀜
    private final AbcRepository abcRepository = new AbcRepositoryJdbcImpl(JdbcUtil.getConnection());
//    private final AbcRepository abcRepository = new AbcRepositoryHikariCpImpl(HikariCpUtil.getConnection());
    private final AbcService abcService = new AbcService(abcRepository);
    private final AbcController abcController = new AbcController(abcService);


    // 처음 객체가 생성될 때 controllerMap에 uri를 키로 컨트롤러 객체를 값으로 저장
    public AppConfig() {



        controllerMap.put("/board/register", boardController);
        controllerMap.put("/board/read", boardController);
        controllerMap.put("/user/signup", userController);
        controllerMap.put("/user/login", userController);

        controllerMap.put("/ex01/read", ex01Controller);
        controllerMap.put("/ex01/list", ex01Controller);
        controllerMap.put("/ex01/register", ex01Controller);

        controllerMap.put("/social/kakao/redirect", socialController);
        controllerMap.put("/orders/verify", ordersController);

        controllerMap.put("/abc", abcController);
    }

    // 특정 uri를 이용해서 특정 컨트롤러 객체를 반환하는 메소드
    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}
