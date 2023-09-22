package com.hakim.datauploder.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hakim.datauploder.model.deserializer.MonthlySheetDeserializer;
import com.hakim.datauploder.model.serializer.MonthlySheetSerializer;
import com.hakim.datauploder.pojo.MonthlySheet;
import jakarta.persistence.AttributeConverter;

public class MonthlySheetConverter implements AttributeConverter<MonthlySheet,String> {
    private static final ObjectMapper mapper;

    static{
        mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(MonthlySheet.class,new MonthlySheetDeserializer());
        simpleModule.addSerializer(MonthlySheet.class,new MonthlySheetSerializer());

        mapper.registerModule(simpleModule);
    }
    @Override
    public String convertToDatabaseColumn(MonthlySheet attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MonthlySheet convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, MonthlySheet.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
