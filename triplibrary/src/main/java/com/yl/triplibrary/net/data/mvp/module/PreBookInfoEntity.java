package com.yl.triplibrary.net.data.mvp.module;

public class PreBookInfoEntity {
    private String name;
    private String values;

    public PreBookInfoEntity(String name, String values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
