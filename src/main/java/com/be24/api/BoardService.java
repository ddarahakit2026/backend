package com.be24.api;

import com.be24.api.model.BoardDto;

public class BoardService {
    public BoardDto register(BoardDto dto) {
        BoardRepository boardRepository = new BoardRepository();
        // 레포지토리의 결과를 반환받게 변경
        BoardDto returnDto = boardRepository.create(dto);

        return returnDto;
    }
}
