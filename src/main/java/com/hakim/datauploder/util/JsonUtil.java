package com.hakim.datauploder.util;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.util.List;

public class JsonUtil {
    public static void writeNumberList(List<? extends Number> numberList, String key, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeArrayFieldStart(key);

        for (Number number : numberList) {
            jsonGenerator.writeNumber((Double) number);
        }

        jsonGenerator.writeEndArray();
    }
}
