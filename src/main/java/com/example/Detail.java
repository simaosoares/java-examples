package com.example;

class Detail {

    private Integer id;
    private String name;

    Detail(String name) {
        this.name = name;
    }

    Detail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    Integer getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
