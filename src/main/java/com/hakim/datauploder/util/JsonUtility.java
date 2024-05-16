package com.hakim.datauploder.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static JsonNode getJsonObject(String json) throws JsonProcessingException {
        return mapper.readTree(json);
    }
}
