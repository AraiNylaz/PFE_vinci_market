package com.example.backend.Enums;

public enum Status {

    SELL("A Vendre"),
    FREE("A Donner");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
