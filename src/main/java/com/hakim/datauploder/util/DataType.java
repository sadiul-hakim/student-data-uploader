package com.hakim.datauploder.util;

public enum DataType {
    DATE("date"),
    DEAL("deal");

    private final String name;

    DataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
