package com.hakim.datauploder.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hakim.datauploder.model.deserializer.FeeSheetDeserializer;
import com.hakim.datauploder.model.serializer.FeeSheetSerializer;
import com.hakim.datauploder.pojo.FeeSheet;
import jakarta.persistence.AttributeConverter;

public class FeeSheetConverter implements AttributeConverter<FeeSheet, String> {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(FeeSheet.class, new FeeSheetDeserializer());
        simpleModule.addSerializer(FeeSheet.class, new FeeSheetSerializer());

        mapper.registerModule(simpleModule);
    }

    @Override
    public String convertToDatabaseColumn(FeeSheet attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public FeeSheet convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, FeeSheet.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
