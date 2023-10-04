package com.hakim.datauploder.util;

public enum DataType {
    FEE("fee"),
    PRESENCE("presence");

    private final String name;

    DataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
