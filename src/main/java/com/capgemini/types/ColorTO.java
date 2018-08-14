package com.capgemini.types;

public class ColorTO {
    private Long id;
    private String name;

    public ColorTO(String name){
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
