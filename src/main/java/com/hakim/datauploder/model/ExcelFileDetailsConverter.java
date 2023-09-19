package com.hakim.datauploder.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hakim.datauploder.model.deserializer.ExcelFileDetailsDeserializer;
import com.hakim.datauploder.model.serializer.ExcelFileDetailsSerializer;

import jakarta.persistence.AttributeConverter;

public class ExcelFileDetailsConverter implements AttributeConverter<ExcelFileDetails,String>{

    private static final ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(ExcelFileDetails.class,new ExcelFileDetailsDeserializer());
        simpleModule.addSerializer(ExcelFileDetails.class,new ExcelFileDetailsSerializer());

        mapper.registerModule(simpleModule);
    }


    @Override
    public String convertToDatabaseColumn(ExcelFileDetails attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ExcelFileDetails convertToEntityAttribute(String dbData) {
        return mapper.convertValue(dbData, ExcelFileDetails.class);
    }
    
}
