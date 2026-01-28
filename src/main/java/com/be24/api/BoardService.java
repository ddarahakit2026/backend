package com.be24.api;

import com.be24.api.model.BoardDto;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardDto read(String boardIdx) {
        BoardDto dto = boardRepository.read(boardIdx);

        return dto;
    }

    public BoardDto register(BoardDto dto) {

        BoardDto returnDto = boardRepository.create(dto);

        return returnDto;
    }
}
