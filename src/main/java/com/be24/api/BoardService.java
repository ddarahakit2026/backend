package com.be24.api;

import com.be24.api.model.BoardDto;

public class BoardService {
    public void register(BoardDto dto) {
        // 레포지토리의 메소드에 DB에 CRUD하는 부분의 역할을 분리하고 호출해서 사용
        BoardRepository boardRepository = new BoardRepository();
        boardRepository.create(dto);
    }
}
