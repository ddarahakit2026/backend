package com.be24.api.board.model;

import jakarta.servlet.http.HttpServletRequest;

public class BoardDto {
    private Integer boardIdx;
    private String title;
    private String contents;

    public BoardDto() {
    }

    public BoardDto(Integer boardIdx, String title, String contents) {
        this.contents = contents;
        this.title = title;
        this.boardIdx = boardIdx;
    }

    public static BoardDto toDto(HttpServletRequest req) {
        BoardDto dto = new BoardDto(
                null,
                req.getParameter("title"),
                req.getParameter("contents")
        );

        return dto;
    }

    public Integer getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(Integer boardIdx) {
        this.boardIdx = boardIdx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
