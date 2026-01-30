package com.be24.api.ex01;

public class Ex01ReadRes {
    private Integer idx;
    private String data01;
    private Integer data02;
    private String data03;

    public Ex01ReadRes() {
    }

    public Ex01ReadRes(Integer idx, String data01, Integer data02, String data03) {
        this.idx = idx;
        this.data01 = data01;
        this.data02 = data02;
        this.data03 = data03;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getData01() {
        return data01;
    }

    public void setData01(String data01) {
        this.data01 = data01;
    }

    public Integer getData02() {
        return data02;
    }

    public void setData02(Integer data02) {
        this.data02 = data02;
    }

    public String getData03() {
        return data03;
    }

    public void setData03(String data03) {
        this.data03 = data03;
    }
}
