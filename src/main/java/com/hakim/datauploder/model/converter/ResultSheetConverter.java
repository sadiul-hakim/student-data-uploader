package com.hakim.datauploder.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hakim.datauploder.model.deserializer.ResultSheetDeserializer;
import com.hakim.datauploder.model.serializer.ResultSheetSerializer;
import com.hakim.datauploder.pojo.ResultSheet;
import jakarta.persistence.AttributeConverter;

public class ResultSheetConverter implements AttributeConverter<ResultSheet,String> {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(ResultSheet.class, new ResultSheetDeserializer());
        simpleModule.addSerializer(ResultSheet.class, new ResultSheetSerializer());

        mapper.registerModule(simpleModule);
    }
    @Override
    public String convertToDatabaseColumn(ResultSheet attribute) {

        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ResultSheet convertToEntityAttribute(String dbData) {

        try {
            return mapper.readValue(dbData, ResultSheet.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
