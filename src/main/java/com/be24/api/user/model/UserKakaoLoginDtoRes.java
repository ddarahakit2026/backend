package com.be24.api.user.model;

public class UserKakaoLoginDtoRes {
    private Long id;
    private String nickname;

    public UserKakaoLoginDtoRes() {
    }

    public UserKakaoLoginDtoRes(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
