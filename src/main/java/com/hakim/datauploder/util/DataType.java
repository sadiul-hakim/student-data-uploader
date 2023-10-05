package com.hakim.datauploder.util;

public enum DataType {
    FEE("fee"),
    PRESENCE("presence"),
    EXAM_RESULT("exam_result");

    private final String name;

    DataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
