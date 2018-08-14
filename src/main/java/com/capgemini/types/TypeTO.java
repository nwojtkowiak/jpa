package com.capgemini.types;

public class TypeTO {

    private long id;
    private String name;

    public TypeTO(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
