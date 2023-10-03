package com.hakim.datauploder.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static void writeNumberList(List<? extends Number> numberList, String key, JsonGenerator jsonGenerator) throws IOException {

        jsonGenerator.writeArrayFieldStart(key);

        for (Number number : numberList) {
            jsonGenerator.writeNumber((Double) number);
        }

        jsonGenerator.writeEndArray();
    }

    public static void writeStringNumberMap(Map<String, ? extends Number> map, String key, JsonGenerator jsonGenerator) throws IOException {

        jsonGenerator.writeObjectFieldStart(key);

        for (Map.Entry<String, ? extends Number> stringEntry : map.entrySet()) {
            jsonGenerator.writeNumberField(stringEntry.getKey(), (double) stringEntry.getValue());
        }

        jsonGenerator.writeEndObject();
    }

    public static Map<String,Double> readStringNumberMap(JsonNode jsonNode, String key, Map<String, Double> defaultVal) throws IOException {

        try {
            return jsonNode.has(key) && !jsonNode.get(key).isNull()
                    ? mapper.convertValue(jsonNode.get(key), new TypeReference<>() {
            })
                    : defaultVal;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return defaultVal;
    }
}
