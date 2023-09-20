package com.hakim.datauploder.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;

import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>,String>{
    
    private static final ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return mapper.convertValue(dbData, new TypeReference<List<String>>(){});
    }
}
