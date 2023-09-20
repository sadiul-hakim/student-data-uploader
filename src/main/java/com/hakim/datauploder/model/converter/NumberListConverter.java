package com.hakim.datauploder.model.converter;

import jakarta.persistence.AttributeConverter;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NumberListConverter implements AttributeConverter<List<? extends Number>,String>{

    private static final ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(List<? extends Number> attribute) {
        
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<? extends Number> convertToEntityAttribute(String dbData) {
        
        return mapper.convertValue(dbData, new TypeReference<List<Long>>(){});
    }
    
}
