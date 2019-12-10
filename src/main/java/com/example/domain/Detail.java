package com.example.domain;

public class Detail {

    private Integer id;
    private String name;

    public Detail(String name) {
        this.name = name;
    }

    public Detail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
