package com.capgemini.types;

public class PositionTO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public PositionTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
