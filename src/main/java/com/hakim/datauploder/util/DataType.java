package com.hakim.datauploder.util;

public enum DataType {
    FEE(2,"fee"),
    PRESENCE(3,"presence"),
    EXAM_RESULT(1,"exam_result");

    private final long id;
    private final String name;

    DataType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
