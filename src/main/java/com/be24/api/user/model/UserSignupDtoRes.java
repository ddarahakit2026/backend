package com.be24.api.user.model;

public class UserSignupDtoRes {
    Integer idx;
    String email;
    String name;

    public UserSignupDtoRes() {
    }

    public UserSignupDtoRes(Integer idx, String email, String name) {
        this.idx = idx;
        this.email = email;
        this.name = name;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
