package com.be24.api.abc;

public class Abc {
    private Integer idx;
    private String name;

    public Abc() {
    }

    public Abc(Integer idx, String name) {
        this.idx = idx;
        this.name = name;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
