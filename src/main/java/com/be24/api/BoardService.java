package com.be24.api;

import com.be24.api.model.BoardDto;

public class BoardService {

    // -- BoardService 클래스에 싱글톤 디자인 패턴 적용--
    private BoardService() {

    }

    private static class SingletonHolder {
        private static final BoardService instance = new BoardService();
    }

    public static BoardService getInstance() {
        return SingletonHolder.instance;
    }
    // -- BoardService 클래스에 싱글톤 디자인 패턴 적용--

    public BoardDto register(BoardDto dto) {
        // BoardRepository 클래스에 싱글톤 적용, new로 생성 못하니까 메소드로 객체 가져옴
        BoardRepository boardRepository = BoardRepository.getInstance();
        BoardDto returnDto = boardRepository.create(dto);

        return returnDto;
    }
}
